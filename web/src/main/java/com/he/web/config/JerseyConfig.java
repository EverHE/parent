package com.he.web.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.jaxrs.FastJsonProvider;
import com.he.web.jersey.UserResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.codec.Utf8;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;

@Component
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig(){
        //注册类的方式
        register(UserResource.class);
        //注册包的方式
        //packages("com.demo.web");


        // 注册数据转换器
        register(FastJsonProvider.class);
        // 注册日志
        //register(LoggingFilter.class);
    }

    @Bean
    public FastJsonProvider fastJsonProvider (){
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        fastJsonConfig.setCharset(Charset.forName("utf-8"));
        FastJsonProvider jsonProvider = new FastJsonProvider();
        //jsonProvider.setCharset(Charset.forName(charset));
        jsonProvider.setFastJsonConfig(fastJsonConfig);
        return jsonProvider;
    }
}
