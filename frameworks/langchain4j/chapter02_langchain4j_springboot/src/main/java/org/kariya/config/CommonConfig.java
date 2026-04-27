package org.kariya.config;

import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;
import org.kariya.service.ConsultantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConfig {
    @Autowired
    private OpenAiChatModel model;

    @Bean
    public ConsultantService consultantService() {
        return AiServices.builder(ConsultantService.class).chatModel(model).build();
    }
}
