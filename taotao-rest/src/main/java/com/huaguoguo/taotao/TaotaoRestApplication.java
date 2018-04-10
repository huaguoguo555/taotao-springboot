package com.huaguoguo.taotao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableCaching
@MapperScan("com.huaguoguo.taotao.dao")
@EnableDiscoveryClient
public class TaotaoRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaotaoRestApplication.class, args);
	}
}
