package chapter04;

import chapter04.section04.example01.DemoInterceptor;
import chapter04.section05.example02.MyMessageConverter;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan("chapter04")
public class MyMvcConfig extends WebMvcConfigurerAdapter {

  @Bean
  public InternalResourceViewResolver viewResolver() {
    InternalResourceViewResolver resolver = new InternalResourceViewResolver();
    resolver.setPrefix("/WEB-INF/classes/views/");
    resolver.setSuffix(".jsp");
    resolver.setViewClass(JstlView.class);
    return resolver;
  }

  @Override public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/assets/");
  }

  @Bean
  public DemoInterceptor demoInterceptor() {
    return new DemoInterceptor();
  }

  @Override public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(demoInterceptor());
  }

  // 4.4.4 >>> 1 快捷的 ViewController
  @Override public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/index").setViewName("/index");
    registry.addViewController("/toUpload").setViewName("/upload");
    registry.addViewController("/converter").setViewName("/converter");
  }

  @Bean
  public MultipartResolver multipartResolver() {
    CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
    multipartResolver.setMaxUploadSize(1000000);
    return multipartResolver;
  }

  // 不忽略 URL 路径中小数点后的字符参数
  @Override public void configurePathMatch(PathMatchConfigurer configurer) {
    configurer.setUseSuffixPatternMatch(false);
  }

  @Override public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
    converters.add(converter());
  }

  @Bean
  public MyMessageConverter converter() {
    return new MyMessageConverter();
  }
}
