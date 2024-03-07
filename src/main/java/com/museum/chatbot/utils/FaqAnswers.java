package com.museum.chatbot.utils;

import com.museum.chatbot.domain.FaqAnswer;
import lombok.Getter;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Getter
public class FaqAnswers {

    private String defaultAnswer;
    private ArrayList<FaqAnswer> answers;

    public FaqAnswers(){
        try {
            JSONTokener tokener = new JSONTokener(new FileInputStream("src/main/resources/static/answers.json"));//Carregando arquivo
            JSONObject faqData = new JSONObject(tokener);//Cria objeto
            JSONArray faqArray = faqData.getJSONArray("faq");//Pega o array faq
            this.answers = new ArrayList<>();//Transforma em um arrayliost

            for (int i = 0; i < faqArray.length(); i++) {
                JSONObject faqEntry = faqArray.getJSONObject(i);
                JSONArray keywordsArray = faqEntry.getJSONArray("keywords");
                List<String> keywords = new ArrayList<>();
                for (int j = 0; j < keywordsArray.length(); j++) {
                    keywords.add(keywordsArray.getString(j));
                }
                String answer = faqEntry.getString("answer");
                this.answers.add(new FaqAnswer(keywords, answer));
            }

            this.defaultAnswer = faqData.getString("default");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
