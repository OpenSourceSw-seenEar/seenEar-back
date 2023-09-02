package com.youT.seenEar.global.config;

import com.youT.seenEar.global.config.model.OpenAIConfigModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class OpenAIConfig implements InitializingBean {
    @Bean
    @ConfigurationProperties("open-ai")
    public OpenAIConfigModel openAIConfigModel(){
        return new OpenAIConfigModel();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        OpenAIConfigModel openAIConfigModel = openAIConfigModel();

    }
}
