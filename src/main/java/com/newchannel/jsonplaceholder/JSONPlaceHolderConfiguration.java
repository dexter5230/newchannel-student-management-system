package com.newchannel.jsonplaceholder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JSONPlaceHolderConfiguration {
    @Value("${info.app.company}")
    String companyName;
    @Bean
    CommandLineRunner runner(JSONPlaceHolderClient jSONPlaceHolderClient) {
        return args -> {
            System.out.println(companyName);
            System.out.println(jSONPlaceHolderClient.getPosts().size());
            System.out.println();
            System.out.println(jSONPlaceHolderClient.getPostByUserId(1).toString());
        };
    }
}
