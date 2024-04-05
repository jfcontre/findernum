package com.finder.adapters.in.http;

import com.finder.application.ports.in.FindMaxNumberUseCase;
import com.finder.application.service.FindMaxNumberService;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class SpringAppConfig {
    @Bean
    FindMaxNumberUseCase findMaxNumberUseCase() {
        return new FindMaxNumberService();
    }
}
