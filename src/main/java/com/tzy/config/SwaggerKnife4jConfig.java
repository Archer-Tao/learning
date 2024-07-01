package com.tzy.config;



import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerKnife4jConfig  {
  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI()
            .info(new Info()
                    .title("calicat ai 接口文档")
                    .description("ai 相关接口文档信息")
                    .version("V1.0")
                    .contact(new Contact().name("Tao"))
            );
  }

}




