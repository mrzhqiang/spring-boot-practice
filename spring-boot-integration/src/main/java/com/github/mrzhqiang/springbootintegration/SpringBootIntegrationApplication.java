package com.github.mrzhqiang.springbootintegration;

import com.rometools.rome.feed.synd.SyndEntry;
import java.io.File;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.feed.inbound.FeedEntryMessageSource;
import org.springframework.integration.file.dsl.Files;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.integration.mail.dsl.Mail;
import org.springframework.integration.scheduling.PollerMetadata;

@SpringBootApplication
public class SpringBootIntegrationApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringBootIntegrationApplication.class, args);
  }

  @Value("https://spring.io/blog.atom") Resource resource;

  @Bean(name = PollerMetadata.DEFAULT_POLLER)
  public PollerMetadata poller() {
    return Pollers.fixedRate(500).get();
  }

  @Bean
  public FeedEntryMessageSource feedEntryMessageSource() throws IOException {
    return new FeedEntryMessageSource(resource.getURL(), "news");
  }

  @Bean
  public IntegrationFlow myFlow() throws IOException {
    return IntegrationFlows.from(feedEntryMessageSource())
        .<SyndEntry, String>route(syndEntry -> syndEntry.getCategories().get(0).getName(),
            mapping -> mapping.channelMapping("releases", "releasesChannel")
                .channelMapping("engineering", "engineeringChannel")
                .channelMapping("news", "newsChannel"))
        .get();
  }

  @Bean
  public IntegrationFlow releasesFlow() {
    return IntegrationFlows.from(
        MessageChannels.queue("releasesChannel", 10))
        .<SyndEntry, String>transform(syndEntry -> " 《"
            + syndEntry.getTitle()
            + "》 "
            + syndEntry.getLink()
            + System.getProperty("line.separator"))
        .handle(Files.outboundAdapter(new File("e:/springblog"))
            .fileExistsMode(FileExistsMode.APPEND)
            .charset("UTF-8")
            .fileNameGenerator(message -> "release.txt")
            .get())
        .get();
  }

  @Bean
  public IntegrationFlow engineeringFlow() {
    return IntegrationFlows.from(MessageChannels.queue("engineeringChannel", 10))
        .<SyndEntry, String>transform(syndEntry ->
            " 《" + syndEntry.getTitle() + "》 " + syndEntry.getLink() + System.getProperty(
                "line.separator"))
        .handle(Files.outboundAdapter(new File("e:/springblog"))
            .fileExistsMode(FileExistsMode.APPEND)
            .charset("UTF-8")
            .fileNameGenerator(message -> "engineering.txt")
            .get())
        .get();
  }

  @Bean
  public IntegrationFlow newsFlow() {
    return IntegrationFlows.from(MessageChannels.queue("newsChannel", 10))
        .<SyndEntry, String>transform(syndEntry -> " 《"
            + syndEntry.getTitle()
            + "》 "
            + syndEntry.getLink()
            + System.getProperty("line.separator"))
        .enrichHeaders(Mail.headers().subject("来自 Spring 的新闻")
            .to("287431404@qq.com")
            .from("287431404@qq.com"))
        .handle(Mail.outboundAdapter("smtp.qq.com")
                .port(25).protocol("smtp")
                .credentials("287431404@qq.com", "********")
                .javaMailProperties(propertiesBuilder -> propertiesBuilder.put("mail.debug", "false")),
            e -> e.id("smtpOut"))
        .get();
  }
}
