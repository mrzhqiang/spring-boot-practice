package chapter01.section03.example03;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("chapter01.section03.example03")
@EnableAspectJAutoProxy
public class AopConfig {
}
