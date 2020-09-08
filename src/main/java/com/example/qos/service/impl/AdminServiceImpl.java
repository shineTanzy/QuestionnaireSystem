package com.example.qos.service.impl;

import com.example.qos.entity.*;
import com.example.qos.mapper.AdminMapper;
import com.example.qos.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public List<Users> selectUsersLimit(int pages, int limits) {
        List<Users> re = adminMapper.selectUsersLimit(pages,limits);
        return re;
    }

    @Override
    public List<Users> selectUsers() {
        List<Users> re = adminMapper.selectUsers();
        return re;
    }

    @Override
    public List<Users> selectUsersLike(String searchVal,String searchVal2) {
        List<Users> re = adminMapper.selectUsersLike(searchVal,searchVal2);
        return re;
    }

    @Override
    public int delUser(int uid) {
        int re = adminMapper.delUser(uid);
        return re;
    }

    @Override
    public int updateUserInfo(int uid, String username, String sex) {
        int re = adminMapper.updateUserInfo(uid,username,sex);
        return re;
    }

    @Override
    public int addUser(Users users) {
        int re = adminMapper.addUser(users);
        return re;
    }

    @Override
    public boolean selectUserByAccount(Users users) {
        try {
            Users users1= adminMapper.selectUserByAccount(users);
            if(Integer.parseInt(users1.getUId())>0  ){
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public List<Questionstype> getQuestionsType(int pages, int limits) {
        List<Questionstype> questionstypeList = adminMapper.getQuestionsType(pages,limits);
        return questionstypeList;
    }

    @Override
    public List<Questionstype> getAllQuestionsType() {
        List<Questionstype> questionstypeList = adminMapper.getAllQuestionsType();
        return questionstypeList;
    }

    @Override
    public boolean addQuestionType(String qtName) {
        int re = adminMapper.addQuestionType(qtName);
        if(re>0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean delQuestionsType(int qtid) {
        int re = adminMapper.delQuestionsType(qtid);
        if(re>0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean updateQuestionsType(int qtid, String qtName) {
        int re = adminMapper.updateQuestionsType(qtid,qtName);
        if(re>0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean selectQuestionTypeByQtName(String qtName) {
        try{
            Questionstype r = adminMapper.selectQuestionTypeByQtName(qtName);
            if(Integer.parseInt(r.getQtid())>0){
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public List<Questionstype> selectAllQuestionsType() {
        List<Questionstype> questionstypeList = adminMapper.selectAllQuestionsType();
        return questionstypeList;
    }

    @Override
    public boolean addQuestions(String qtid, String qtsName, String classification) {
         int re = adminMapper.addQuestions(qtid,qtsName,classification);
         if(re>0){
             return true;}
         else {
             return false;
         }
    }


    @Override
    public boolean selectQuSame(String qtid, String qtsName, String classification) {
        try{
            String res = adminMapper.selectQuSame(qtid,qtsName, classification);
            if(Integer.parseInt(res)>0){
                return false;}
            else {
                return true;
            }
        }catch (Exception e){
            return true;
        }
    }

    @Override
    public String selectQuId(String qtid, String qtsName, String classification) {
        String q = adminMapper.selectQuId(qtid,qtsName,classification);
        return q;
    }

    @Override
    public boolean addMuchRAnswers(List<Answers> answersList) {
        int re =  adminMapper.addMuchRAnswers(answersList);
        if(re>0){
            return true;}
        else {
            return false;
        }
    }

    @Override
    public List<Questions> getQuestions(int pages, int limits,String qtid,String classification) {
        List<Questions> questionsList = adminMapper.getQuestions(pages,limits,qtid,classification);
        return questionsList;
    }

    @Override
    public List<Questions> getAllQuestions(String qtid,String classification) {
        List<Questions> questionsList = adminMapper.getAllQuestions(qtid,classification);
        return questionsList;
    }

    @Override
    public boolean deleteQuestionsById(int quId) {
        int re = adminMapper.deleteQuestionsById(quId);
        if(re>0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Questions getQuestionsByQuid(int quId) {
        Questions questions = adminMapper.getQuestionsByQuid(quId);
        return questions;
    }

    @Override
    public List<Answers> getAnswersByQuid(String quId) {
        try{
            List<Answers> answersList = adminMapper.getAnswersByQuid(quId);
            return answersList;
        }catch (Exception e){
            List<Answers> answersList = new ArrayList<>();
            return answersList;
        }


    }

    @Override
    public boolean updateQuestionsByQuid(int quId, String qtsName) {
        int re = adminMapper.updateQuestionsByQuid(quId,qtsName);
        if(re>0){ return true;}else { return false;}
    }

    @Override
    public boolean deleteAnswersByQuid(String quId) {
        int re = adminMapper.deleteAnswersByQuid(quId);
        if(re>0){ return true;}else { return false;}
    }

    @Override
    public List<Helpinfo> getHelpInfo(int pages, int limits) {
        try{
            List<Helpinfo> helpinfoList = adminMapper.getHelpInfo(pages,limits);
            return helpinfoList;
        }catch (Exception e){
            List<Helpinfo> helpinfoList = new ArrayList<>();
            return helpinfoList;
        }
    }

    @Override
    public List<Helpinfo> getHelpInfos() {
        List<Helpinfo> helpinfoList = adminMapper.getHelpInfos();
        return helpinfoList;
    }

    @Override
    public boolean addHelpInfo(Helpinfo helpinfo) {
        int re = adminMapper.addHelpInfo(helpinfo);
        if(re>0){ return true;}else { return false;}
    }

    @Override
    public boolean delHelpInfo(int hId) {
        int re = adminMapper.delHelpInfo(hId);
        if(re>0){ return true;}else { return false;}
    }

    @Override
    public boolean updateHelpInfo(int hId, String infoTitle, String infoContent) {
        int re = adminMapper.updateHelpInfo(hId,infoTitle,infoContent);
        if(re>0){ return true;}else { return false;}
    }

    @Override
    public boolean addQuestionnaire(Questionnaire questionnaire) {
        int re = adminMapper.addQuestionnaire(questionnaire);
        if(re>0){ return true;}else { return false;}
    }

    @Override
    public List<Questionnaire> getQuestionnaire(int pages, int limits) {
        try{
            List<Questionnaire> questionnaireList = adminMapper.getQuestionnaire(pages,limits);
            return questionnaireList;
        }catch (Exception e){
            List<Questionnaire> questionnaireList = new ArrayList<>();
            return questionnaireList;
        }
    }

    @Override
    public List<Questionnaire> getQuestionnaires() {
        List<Questionnaire> questionnaireList = adminMapper.getQuestionnaires();
        return questionnaireList;
    }

    @Override
    public boolean delQuestionnair(int qnId) {
        int re = adminMapper.delQuestionnair(qnId);
        if(re>0){ return true;}else { return false;}
    }

    @Override
    public String getStateByQnId(int qnId) {
        String re = adminMapper.getStateByQnId(qnId);
        return re;
    }

    @Override
    public boolean closeQuestionnair(int qnId) {
        int re = adminMapper.closeQuestionnair(qnId);
        if(re>0){ return true;}else { return false;}
    }

    @Override
    public boolean issueQuestionnair(int qnId) {
        int re = adminMapper.issueQuestionnair(qnId);
        if(re>0){ return true;}else { return false;}
    }

    @Override
    public List<Questionnaire> getQuestionnaireLike(int pages, int limits, String searchVal1,String searchVal2,String searchVal3) {
        try{
            List<Questionnaire> questionnaireList = adminMapper.getQuestionnaireLike(pages,limits,searchVal1,searchVal2,searchVal3);
            return questionnaireList;
       }catch (Exception e){
            List<Questionnaire> questionnaireList = new ArrayList<>();
            return questionnaireList;
        }

    }

    @Override
    public List<Questions> getQuestionsByidcal(String qtid, String classification) {
        List<Questions> questionsList = adminMapper.getQuestionsByidcal(qtid,classification);
        return questionsList;
    }

    @Override
    public boolean addQusToNaire(int qnId, int quId) {
        int re = adminMapper.addQusToNaire(qnId,quId);
        if(re>0){ return true;}else { return false;}
    }

    @Override
    public List<QuestionnaireQuestions> getQuIdes(int qnId) {
        List<QuestionnaireQuestions>questionnaireQuestions = adminMapper.getQuIdes(qnId);
        return questionnaireQuestions;
    }

    @Override
    public List<Questions> getQuestionsByQuId(List<QuestionnaireQuestions> questionnaireQuestions) {
        List<Questions> questionsList = adminMapper.getQuestionsByQuId(questionnaireQuestions);
        return questionsList;
    }

    @Override
    public List<Questions> getQuestionsByQuIdLimit(List<QuestionnaireQuestions> questionnaireQuestions, int pages, int limits) {
        List<Questions> questionsList = adminMapper.getQuestionsByQuIdLimit(questionnaireQuestions,pages,limits);
        return questionsList;
    }

    @Override
    public boolean deleteQQByQuId(int quId) {
        int re = adminMapper.deleteQQByQuId(quId);
        if(re>0){ return true;}else { return false;}
    }

    @Override
    public List<Questionnaire> getQuestionnaireByStateLimit(int pages, int limits) {
        try{
            List<Questionnaire> questionnaireList = adminMapper.getQuestionnaireByStateLimit(pages,limits);
            return questionnaireList;
        }catch (Exception e){
            List<Questionnaire> questionnaireList = new ArrayList<>();
            return questionnaireList;
        }
    }

    @Override
    public List<Questionnaire> getQuestionnairesState() {
        List<Questionnaire> questionnaireList = adminMapper.getQuestionnairesState();
        return questionnaireList;
    }

    @Override
    public int getMyanswerCut(Myanswers myanswers) {
        try{
            int re = adminMapper.getMyanswerCut(myanswers);
            return re;
        }catch (Exception e){
            return 0;
        }
    }

    @Override
    public String getMyanswerAnswer(Myanswers myanswers) {
        String answer = adminMapper.getMyanswerAnswer(myanswers);
        return answer;
    }

    @Override
    public int insertStatistices(List<Statistic> statisticList) {
        int re = adminMapper.insertStatistices(statisticList);
        return re;
    }

    @Override
    public List<Statistic> getXuZeStatistic(int qnId) {
        try{
            List<Statistic> statisticList = adminMapper.getXuZeStatistic(qnId);
            return statisticList;
        }catch (Exception e){
            List<Statistic> statisticList = new ArrayList<>();
            return  statisticList;
        }
    }

    @Override
    public List<Statistic> getXuZeStatisticLimit(int qnId, int pages, int limits) {
        try{
            List<Statistic> statisticList = adminMapper.getXuZeStatisticLimit(qnId,pages,limits);
            return statisticList;
        }catch (Exception e){
            List<Statistic> statisticList = new ArrayList<>();
            return  statisticList;
        }
    }

    @Override
    public List<Myanswers> getJiDaStatistic(int qnId) {
        try{
            List<Myanswers> myanswersList = adminMapper.getJiDaStatistic(qnId);
            return myanswersList;
        }catch (Exception e){
            List<Myanswers> myanswersList = new ArrayList<>();
            return  myanswersList;
        }
    }

    @Override
    public List<Myanswers> getJiDaStatisticLimit(int qnId, int pages, int limits) {
        try{
            List<Myanswers> myanswersList = adminMapper.getJiDaStatisticLimit(qnId,pages,limits);
            return myanswersList;
        }catch (Exception e){
            List<Myanswers> myanswersList = new ArrayList<>();
            return  myanswersList;
        }
    }
}
