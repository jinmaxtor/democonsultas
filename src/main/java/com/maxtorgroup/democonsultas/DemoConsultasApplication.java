package com.maxtorgroup.democonsultas;

import com.maxtorgroup.democonsultas.api.configuration.ConvertersConfigurer;
import com.maxtorgroup.democonsultas.domain.contract.FileService;
import com.maxtorgroup.democonsultas.infrastructure.configuration.CorsProperties;
import com.maxtorgroup.democonsultas.infrastructure.configuration.StorageProperties;
import jakarta.annotation.Resource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({CorsProperties.class, StorageProperties.class})
public class DemoConsultasApplication implements CommandLineRunner {

	@Resource
	FileService fileService;

	public static void main(String[] args) {
		SpringApplication.run(DemoConsultasApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		fileService.init();
	}
}
