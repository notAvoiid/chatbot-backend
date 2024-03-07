package com.museum.chatbot.controllers;

import com.museum.chatbot.dto.MessageRequest;
import com.museum.chatbot.utils.FaqAnswers;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chat")
public class FaqController {

    @PostMapping
    public ResponseEntity<String> answerQuestion(@RequestBody MessageRequest message){
        FaqAnswers faqAnswers = new FaqAnswers();
        System.out.println(faqAnswers.getAnswers().get(0).getAnswer());
        return ResponseEntity.ok().body("Deu certo!");
    }

}
