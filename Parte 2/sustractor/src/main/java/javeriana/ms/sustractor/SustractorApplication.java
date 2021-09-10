package javeriana.ms.sustractor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SustractorApplication {

	public static void main(String[] args) {
		SpringApplication.run(SustractorApplication.class, args);
	}

}
