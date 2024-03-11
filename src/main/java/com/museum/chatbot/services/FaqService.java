package com.museum.chatbot.services;

import com.museum.chatbot.domain.FaqAnswer;
import com.museum.chatbot.utils.FaqAnswers;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class FaqService {

    private final FaqAnswers faqAnswers = new FaqAnswers();

    public String getAnswers(String message){
        String[] words = message.toLowerCase().split("\\s+");
        List<String> wordsList = Arrays.asList(words).stream().map(String::toLowerCase).map(this::replaceForbiddenChar).toList();

        for (FaqAnswer entry : faqAnswers.getAnswers()) {
            for (String keyword : entry.getKeywords()) {
                if (wordsList.contains(keyword.toLowerCase())) {
                    return entry.getAnswer();
                }
            }
        }

        return faqAnswers.getDefaultAnswer();
    }

    public String replaceForbiddenChar(String question){
        return question.replace
                ("?", "").replace(":", "").replace("/", "");
    }
}
