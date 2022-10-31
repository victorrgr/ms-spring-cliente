package br.localizacao.msendereco;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MsEnderecoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsEnderecoApplication.class, args);
	}

}
