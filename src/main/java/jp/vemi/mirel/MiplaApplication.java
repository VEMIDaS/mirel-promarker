package jp.vemi.mirel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@EntityScan
@EnableJpaRepositories
@ComponentScan(basePackages = {"jp.vemi.framework.util", "jp.vemi.mirel"})
public class MiplaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiplaApplication.class, args);
	}
}
