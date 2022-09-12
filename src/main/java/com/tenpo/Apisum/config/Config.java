package com.tenpo.Apisum.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.time.format.DateTimeFormatter;

@Configuration
public class Config {
    @Bean
    public RestTemplate getRestTemplate() { return new RestTemplate();}
//    @Bean
//    @Primary
//    public MappingJackson2HttpMessageConverter jackson2ObjectMapperBuilder() {
//        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder()
//                .serializers(new LocalDateTimeSerializer(
//                        DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")))
//                .serializationInclusion(JsonInclude.Include.NON_NULL);
//        return new MappingJackson2HttpMessageConverter(builder.build());
//    }
}