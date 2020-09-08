package com.example.qos.mapper;


import com.example.qos.entity.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UsersMapper {
   Users regAccount(String account);
   int  regUser(Users users);
   Users getLogin(Users users);
   Users getUser(Users users);
   List<Questionnaire> getQuestionnaireState();
   List<Questionnaire> getQuestionnaireStateLimit(int pages,int limits);
   List<Myanswers> regMyAnswers(int qnId, int uId);
   List<Answers> getAnswers(List<Questions> questionsList);
   Questionnaire getQuestionnaire(int qnId);
   int addMyAnswers( List<Myanswers>  myanswersList);
   Users regPassword(@Param("userpassword") String userpassword,@Param("uId") int uId);
   int updatePassword(@Param("userpassword") String userpassword,@Param("uId") int uId);
   List<Helpinfo> getHelpInfos();
}
