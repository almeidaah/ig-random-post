package almeida.fernando.instagram.randomizedpost;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
public class RandomizedPosterApplication {

	public static void main(String[] args) {
		SpringApplication.run(RandomizedPosterApplication.class, args);
	}

}
