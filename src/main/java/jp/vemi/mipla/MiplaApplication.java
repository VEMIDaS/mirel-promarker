package jp.vemi.mipla;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@EntityScan
@EnableJpaRepositories
public class MiplaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiplaApplication.class, args);
	}

}
