package com.hisami.hisami;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.hisami.hisami.config.OAuthClientProps;

@SpringBootApplication
@EnableConfigurationProperties(OAuthClientProps.class)
public class HisamiApplication {

	public static void main(String[] args) {
		SpringApplication.run(HisamiApplication.class, args);
	}

}
