package com.tc.draw.demo.framework.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.TimeZone;

@Configuration
public class JacksonConfig {


    /**
     *  Jackson全局转化long类型为String，解决jackson序列化时传入前端Long类型缺失精度问题
     * 时区配置
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jacksonObjectMapperCustomization()
    {
        return jacksonObjectMapperBuilder -> jacksonObjectMapperBuilder
                .serializerByType(long.class, LongToStringSerializer.instance)
                .serializerByType(Long.class, LongToStringSerializer.instance).timeZone(TimeZone.getDefault()).build();
    }

}
