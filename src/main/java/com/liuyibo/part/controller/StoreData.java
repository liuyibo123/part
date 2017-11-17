package com.liuyibo.part.controller;

import com.alibaba.fastjson.JSONArray;
import com.liuyibo.part.dao.QuestionRepository;
import com.liuyibo.part.entity.Question;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

@Controller
public class StoreData {
    private Logger logger = Logger.getLogger(StoreData.class);
    @Autowired
    Question question;
    @Autowired
    QuestionRepository repository;
    @RequestMapping("/init")
    public @ResponseBody String initData(){

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(new File("D://question.txt")));
            String tempString;
            LinkedList<String> linkString = new LinkedList<String>();
            while ((tempString = reader.readLine()) != null) {
                linkString.add(tempString);
            }
            int countId=1;
            for(int i=0;i<linkString.size();i++){
                String iString= linkString.get(i);
                if (iString.startsWith("A")){
                    question.setSelectionA(iString);
                }else if (iString.startsWith("B")){
                    question.setSelectionB(iString);
                }else if (iString.startsWith("C")){
                    question.setSelectionC(iString);
                    logger.info("question =="+question.toString());
                    repository.save(question);
                }else{
                    question.setId(countId);
                    countId++;
                    question.setDescription(iString.substring(0,iString.length()-3));
                    question.setAnswer(iString.substring(iString.length()-2,iString.length()-1));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "finish";
    }
    @RequestMapping("/getQuestions")
    public @ResponseBody  List<Question> getAll(){
        return repository.findAll();
    }
}
