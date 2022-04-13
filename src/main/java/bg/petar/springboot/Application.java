package bg.petar.springboot;

import bg.petar.springboot.controllers.LoginControllers;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;


@EnableAutoConfiguration
@ComponentScan(basePackages = "bg.petar.springboot")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
