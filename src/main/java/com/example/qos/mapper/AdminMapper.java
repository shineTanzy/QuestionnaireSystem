package com.example.qos.mapper;



import com.example.qos.entity.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author 谭舟耀
 */
@Repository
public interface AdminMapper {
    List<Users> selectUsersLimit(int pages, int limits);
    List<Users> selectUsers();
    List<Users> selectUsersLike(String searchVal,String searchVal2);
    int delUser(int uid);
    int updateUserInfo(@Param("uid")int uid,@Param("username")String username,@Param("sex")String sex);
    int addUser(Users users);
    Users selectUserByAccount(Users users);

    List<Questionstype> getQuestionsType(int pages,int limits);
    List<Questionstype> getAllQuestionsType();
    int addQuestionType(String qtName);
    int delQuestionsType(int qtid);
    int updateQuestionsType(@Param("qtid")int qtid,@Param("qtName")String qtName);
    Questionstype selectQuestionTypeByQtName(String qtName);

    List<Questionstype> selectAllQuestionsType();
    int addQuestions(String qtid, String qtsName, String classification);
    String selectQuSame(String qtid,String qtsName, String classification);
    String selectQuId(String qtid, String qtsName, String classification);
    int addMuchRAnswers(List<Answers> answersList);
    // 问题管理
    List<Questions> getQuestions(@Param("pages") int pages,@Param("limits") int limits,@Param("qtid") String qtid,@Param("classification") String classification);
    List<Questions> getAllQuestions(String qtid,String classification);
    int deleteQuestionsById(int quId);
    Questions getQuestionsByQuid(int quId);
    List<Answers> getAnswersByQuid(String quId);
    int  updateQuestionsByQuid(@Param("quId") int quId,@Param("qtsName") String qtsName);
    int deleteAnswersByQuid(String quId);

    List<Helpinfo> getHelpInfo(int pages,int limits);
    List<Helpinfo> getHelpInfos();
    int addHelpInfo(Helpinfo helpinfo);
    int delHelpInfo(int hId);
    int updateHelpInfo(@Param("hId")int hId,@Param("infoTitle")String infoTitle,@Param("infoContent")String infoContent);

    int addQuestionnaire(Questionnaire questionnaire);
    List<Questionnaire> getQuestionnaire(int pages,int limits);
    List<Questionnaire> getQuestionnaires();
    int delQuestionnair(int qnId);
    String getStateByQnId(int qnId);
    int closeQuestionnair(int qnId);
    int issueQuestionnair(int qnId);
    List<Questionnaire> getQuestionnaireLike(@Param("pages") int pages,@Param("limits") int limits,
     @Param("searchVal1") String searchVal1,@Param("searchVal2") String searchVal2,@Param("searchVal3") String searchVal3);
    List<Questions> getQuestionsByidcal(String qtid,String classification);
    int addQusToNaire(int qnId,int quId);
    List<QuestionnaireQuestions> getQuIdes(int qnId);
    List<Questions> getQuestionsByQuId(List<QuestionnaireQuestions> questionnaireQuestions);
    List<Questions> getQuestionsByQuIdLimit(@Param("questionnaireQuestions") List<QuestionnaireQuestions> questionnaireQuestions,@Param("pages") int pages,@Param("limits") int limits);
    int deleteQQByQuId(int quId);
    //统计
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
