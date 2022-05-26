package bssm.localhost;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class LocalhostApplication {

	public static void main(String[] args) {
		SpringApplication.run(LocalhostApplication.class, args);
	}

}
