package com.example.spring_boot_file_transfer.com.example.config;

import com.example.spring_boot_file_transfer.com.example.web_handler.FileTransferWebSocketHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class AppConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketHandler(), "/web-socket");
    }

    @Bean
    public FileTransferWebSocketHandler webSocketHandler() {
        return new FileTransferWebSocketHandler();
    }
}
