package com.ros.accounting;

//import com.azure.storage.blob.BlobClientBuilder;
import com.ros.accounting.cashup.mapper.RestDtoMapper;
import com.ros.accounting.cashup.mapper.RestDtoMapperImpl;
import com.ros.accounting.cashup.service.DocumentUploadService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableEurekaClient
//@EnableResourceServer
@OpenAPIDefinition(info = @Info(title = "AccountingServiceAPI", version = "1.0", description = "API for Accounting Service"))
public class AccountingServiceApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(AccountingServiceApplication.class, args);
    }

    @Bean
    public RestDtoMapper mapper() {
        return new RestDtoMapperImpl();
    }

    @Resource
    DocumentUploadService documentUploadService;

    @Override
    public void run(String... arg) throws Exception {
//        storageService.deleteAll();
        documentUploadService.init();
    }
}
