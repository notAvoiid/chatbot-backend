package com.museum.chatbot.controllers;

import com.museum.chatbot.dto.MessageRequest;
import com.museum.chatbot.dto.MessageResponse;
import com.museum.chatbot.services.FaqService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
public class FaqController {

    private final FaqService service;

    public FaqController(FaqService service) {
        this.service = service;
    }

    @PostMapping
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<MessageResponse> answerQuestion(@RequestBody MessageRequest message){
        String answer = service.getAnswers(message.message());
        MessageResponse response = new MessageResponse(answer);
        return ResponseEntity.ok().body(response);
    }

}
