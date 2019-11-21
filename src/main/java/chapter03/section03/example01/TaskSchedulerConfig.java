package chapter03.section03.example01;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@ComponentScan("chapter03.section03.example01")
@EnableScheduling
public class TaskSchedulerConfig {
}
