package com.example.qos.service;

import com.example.qos.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UsersService {
    boolean regAccount(String account);
    boolean  regUser(Users users);
    boolean getLogin(Users users);
    Users getUser(Users users);
    List<Questionnaire> getQuestionnaireState();
    List<Questionnaire> getQuestionnaireStateLimit(int pages,int limits);
    boolean regMyAnswers(int qnId,int uId);
    List<Answers> getAnswers(List<Questions> questionsList);
    Questionnaire getQuestionnaire(int qnId);
    boolean addMyAnswers( List<Myanswers>  myanswersList);
    boolean regPassword(String userpassword,int uId);
    boolean updatePassword(String userpassword, int uId);
    List<Helpinfo> getHelpInfos();



}
