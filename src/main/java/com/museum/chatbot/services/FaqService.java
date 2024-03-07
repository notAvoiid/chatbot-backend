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
        List<String> wordsList = Arrays.asList(words);
        for (FaqAnswer entry : faqAnswers.getAnswers()) {
            for (String keyword : entry.getKeywords()) {
                if (wordsList.contains(keyword)) {
                    return entry.getAnswer();
                }
            }
        }

        return faqAnswers.getDefaultAnswer();
    }
}
