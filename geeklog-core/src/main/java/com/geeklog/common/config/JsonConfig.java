package com.geeklog.common.config;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

/**
 * @author 潘浩然
 * 创建时间 2018/09/11
 * 功能：pojo 转 json 配置，驼峰转小写下划线
 */
@Configuration
public class JsonConfig {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        return objectMapper;
    }

    @Bean
    public MappingJackson2HttpMessageConverter jsonConverter(ObjectMapper objectMapper) {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(objectMapper);
        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(MediaType.parseMediaType("application/json; charset=UTF-8"));
        converter.setSupportedMediaTypes(mediaTypes);

        return converter;
    }
}
