package com.backend.systemdesign.ai.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.systemdesign.ai.service.ai.OpenAITestService;

@RestController
public class TestController {

    private final OpenAITestService openAITestService;

    public TestController(OpenAITestService openAITestService) {
        this.openAITestService = openAITestService;
    }

    @GetMapping("/test-ai")
    public String testAI() {
        return openAITestService.test();
    }
}