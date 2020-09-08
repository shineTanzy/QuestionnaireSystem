package com.example.qos.service;

import com.example.qos.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminService {
    List<Users> selectUsersLimit(int pages, int limits);
    List<Users> selectUsers();
    List<Users> selectUsersLike(String searchVal,String searchVal2);
    int delUser(int uid);
    int updateUserInfo(int uid, String username,String sex);
    int addUser(Users users);
    boolean selectUserByAccount(Users users);
    List<Questionstype> getQuestionsType(int pages,int limits);
    List<Questionstype> getAllQuestionsType();
    boolean addQuestionType(String qtName);
    boolean delQuestionsType(int qtid);
    boolean updateQuestionsType(int qtid,String qtName);
    boolean selectQuestionTypeByQtName(String qtName);

    List<Questionstype> selectAllQuestionsType();
    boolean addQuestions(String qtid, String qtsName, String classification);
    boolean selectQuSame(String qtid,String qtsName, String classification);
    String selectQuId(String qtid, String qtsName, String classification);
    boolean addMuchRAnswers(List<Answers> answersList);
    List<Questions> getQuestions(int pages,int limits ,String qtid,String classification);
    List<Questions> getAllQuestions(String qtid,String classification);
    boolean deleteQuestionsById(int quId);
    Questions getQuestionsByQuid(int quId);
    List<Answers> getAnswersByQuid(String quId);
    boolean updateQuestionsByQuid(int quId,String qtsName);
    boolean deleteAnswersByQuid(String quId);

    List<Helpinfo> getHelpInfo(int pages,int limits);
    List<Helpinfo> getHelpInfos();
    boolean addHelpInfo(Helpinfo helpinfo);
    boolean delHelpInfo(int hId);
    boolean updateHelpInfo(int hId,String infoTitle,String infoContent);

    boolean addQuestionnaire(Questionnaire questionnaire);
    List<Questionnaire> getQuestionnaire(int pages,int limits);
    List<Questionnaire> getQuestionnaires();
    boolean delQuestionnair(int qnId);
    String getStateByQnId(int qnId);
    boolean closeQuestionnair(int qnId);
    boolean issueQuestionnair(int qnId);
    List<Questionnaire> getQuestionnaireLike(int pages,int limits,
         String searchVal1,String searchVal2,String searchVal3);
    List<Questions> getQuestionsByidcal(String qtid,String classification);
    boolean addQusToNaire(int qnId,int quId);
    List<QuestionnaireQuestions> getQuIdes(int qnId);
    List<Questions> getQuestionsByQuId(List<QuestionnaireQuestions> questionnaireQuestions);
    List<Questions> getQuestionsByQuIdLimit(List<QuestionnaireQuestions> questionnaireQuestions,int pages,int limits);
    boolean deleteQQByQuId(int quId);

    List<Questionnaire> getQuestionnaireByStateLimit(int pages,int limits);
    List<Questionnaire> getQuestionnairesState();
    int getMyanswerCut(Myanswers myanswers);
    String getMyanswerAnswer(Myanswers myanswers);
    int insertStatistices(List<Statistic> statisticList);
    List<Statistic> getXuZeStatistic(int qnId);
    List<Statistic> getXuZeStatisticLimit(int qnId,int pages,int limits);
    List<Myanswers> getJiDaStatistic(int qnId);
    List<Myanswers> getJiDaStatisticLimit(int qnId,int pages,int limits);
}
