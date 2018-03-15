package ftt.unitforum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"ftt.common", "ftt.unitforum"})
@EnableAutoConfiguration // (exclude={DataSourceAutoConfiguration.class})
public class FttUnitForumApplication {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(FttUnitForumApplication.class, args);
	}
}
