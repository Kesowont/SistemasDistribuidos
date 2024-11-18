package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final ClimateDataService climateDataService;

    public WebSocketConfig(ClimateDataService climateDataService) {
        this.climateDataService = climateDataService;
    }

    @Bean
    public ClimateWebSocketHandler climateWebSocketHandler() {
        return new ClimateWebSocketHandler(climateDataService);
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(climateWebSocketHandler(), "/climate-updates").setAllowedOrigins("*");
    }
}
