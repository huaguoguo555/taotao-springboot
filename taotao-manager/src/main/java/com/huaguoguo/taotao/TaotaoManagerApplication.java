package com.huaguoguo.taotao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.huaguoguo.taotao.dao")
public class TaotaoManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaotaoManagerApplication.class, args);
	}
}
