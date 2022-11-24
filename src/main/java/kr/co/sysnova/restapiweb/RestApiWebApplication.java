package kr.co.sysnova.restapiweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // JPA Auditing 애노테이션을 모두 활성화 하기 위해 Application 클래스에 활성화 애노테이션을 추가
@SpringBootApplication
public class RestApiWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApiWebApplication.class, args);
	}

}
