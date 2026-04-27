package org.kariya.controller;

import dev.langchain4j.model.openai.OpenAiChatModel;
import jakarta.servlet.http.HttpServletResponse;
import org.kariya.service.AssistantService;
import org.kariya.service.ConsultantService;
import org.kariya.service.StreamingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class ChatController {

    @Autowired
    private OpenAiChatModel model;

    @Autowired
    private ConsultantService consultantService;

    @Autowired
    private AssistantService assistantService;

    @Autowired
    private StreamingService streamingService;

    @RequestMapping("/chat")
    public String chat(String messages) {
        return model.chat(messages);
    }

    @RequestMapping("/chatByService")
    public String chatByService(String messages) {
        return consultantService.chat(messages);
    }

    @RequestMapping("/chatByAssistant")
    public String chatByAssistant(String messages) {
        return assistantService.chat(messages);
    }

    /*
     * 流式调用
     * */
    @RequestMapping("/chatStreaming")
    public Flux<String> chatStreaming(String messages, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        return streamingService.chats(messages);
    }
}
