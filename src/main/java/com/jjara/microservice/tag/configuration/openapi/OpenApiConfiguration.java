package com.jjara.microservice.tag.configuration.openapi;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RefreshScope
@Configuration
public class OpenApiConfiguration {

    @Value("${openapi.contact.name}") private String contactName;
    @Value("${openapi.contact.email}") private String contactEmail;
    @Value("${openapi.info.description}") private String infoDescription;
    @Value("${openapi.info.title}") private String infoTitle;

    @Bean
    OpenAPI customOpenAPI() {
        var contact = new Contact();
        contact.setName(contactName);
        contact.setEmail(contactEmail);

        var info = new Info();
        info.setDescription(infoDescription);
        info.setTitle(infoTitle);
        info.setContact(contact);
        info.setVersion("3.0");

        var openApi = new OpenAPI();
        openApi.setInfo(info);

        return openApi;
    }

}