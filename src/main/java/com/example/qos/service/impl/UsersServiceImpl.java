package com.example.qos.service.impl;

import com.example.qos.entity.*;
import com.example.qos.mapper.UsersMapper;
import com.example.qos.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UsersMapper usersMapper;

    @Override
    public boolean regAccount(String account){
        Users users = usersMapper.regAccount(account);
        try{
            if(Integer.parseInt(users.getUId())>0){
                return false;
            }else {
                return true;
            }
        }catch (Exception e){
            return true;
        }
    }

    @Override
    public boolean regUser(Users users) {
        int re = usersMapper.regUser(users);
        if(re>0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean getLogin(Users users) {
        Users users1 = usersMapper.getLogin(users);
        try{
            if(Integer.parseInt(users1.getUId())>0){
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public Users getUser(Users users) {
        Users users1 = usersMapper.getUser(users);
        return users1;
    }

    @Override
    public List<Questionnaire> getQuestionnaireState() {
        try{
            List<Questionnaire> questionnaireList = usersMapper.getQuestionnaireState();
            return questionnaireList;
        }catch (Exception e){
            List<Questionnaire> questionnaireList = new ArrayList<>();
            return questionnaireList;
        }
    }

    @Override
    public List<Questionnaire> getQuestionnaireStateLimit(int pages, int limits) {
        try{
            List<Questionnaire> questionnaireList = usersMapper.getQuestionnaireStateLimit(pages,limits);
            return questionnaireList;
        }catch (Exception e){
            List<Questionnaire> questionnaireList = new ArrayList<>();
            return questionnaireList;
        }
    }

    @Override
    public boolean regMyAnswers(int qnId, int uId) {
        try{
            List<Myanswers> myanswersList = usersMapper.regMyAnswers(qnId,uId);
            if(myanswersList.get(0).getMaId()>0){return true;}else {return false;}
        }catch (Exception e){
          return false;
        }
    }

    @Override
    public List<Answers> getAnswers(List<Questions> questionsList) {
        List<Answers> answersList = usersMapper.getAnswers(questionsList);
        return answersList;
    }


    @Override
    public Questionnaire getQuestionnaire(int qnId) {
        Questionnaire questionnaire = usersMapper.getQuestionnaire(qnId);
        return questionnaire;
    }

    @Override
    public boolean addMyAnswers(List<Myanswers> myanswersList) {
        int re = usersMapper.addMyAnswers(myanswersList);
        if(re>0){return true;}else {return false;}
    }

    @Override
    public boolean regPassword(String userpassword, int uId) {
        try{
            Users users = usersMapper.regPassword(userpassword,uId);
            if(Integer.parseInt(users.getUId())>0){return true;}else {return false;}
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean updatePassword(String userpassword, int uId) {
        int re = usersMapper.updatePassword(userpassword,uId);
        if(re>0){return true;}else {return false;}
    }

    @Override
    public List<Helpinfo> getHelpInfos() {
        try{
            List<Helpinfo> helpinfoList = usersMapper.getHelpInfos();
            return  helpinfoList;
        }catch (Exception e){
            List<Helpinfo> helpinfoList = new ArrayList<>();
            return helpinfoList;
        }
    }
}