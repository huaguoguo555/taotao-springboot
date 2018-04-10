package com.huaguoguo.taotao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class TaotaoEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaotaoEurekaServerApplication.class, args);
	}
}
