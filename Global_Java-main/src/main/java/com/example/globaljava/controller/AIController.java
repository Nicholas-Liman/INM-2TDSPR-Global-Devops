package com.example.globaljava.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@RestController
public class AIController {

    private final ChatClient chatClient;

    public AIController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @PostMapping("/deepseek-r1-query")
    public String chatWithDeepseek(@RequestBody QueryRequest request) {
        String encodedQuery;
        try {
            encodedQuery = URLEncoder.encode(request.getQuery(), "US-ASCII");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Erro ao codificar a query para ASCII", e);
        }

        return chatClient.prompt(encodedQuery).call().content().toString();
    }


    @Setter
    @Getter
    public static class QueryRequest {
        private String query;

    }
}