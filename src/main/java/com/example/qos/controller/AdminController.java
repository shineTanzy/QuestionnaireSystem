package com.example.qos.controller;

import com.alibaba.fastjson.JSONArray;
import com.example.qos.entity.*;
import com.example.qos.service.AdminService;
import com.example.qos.service.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * @author 谭舟耀
 */
@Slf4j
@SuppressWarnings("error")
@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminService adminService;
    @Autowired
    UsersService usersService;
    @RequestMapping("/toAdminManage")
    public String toAdminManage(){
        return "adminManage";
    }

    @RequestMapping("/toAddUser")
    public String toAddUser(){
        return "adViews/addUser";
    }

    @RequestMapping("/toUserManage")
    public String toUserManage(){
        return "adViews/usersManage";
    }

    @RequestMapping("/toQuestionsTypeManage")
    public String toQuestionsTypeManage(){
        return "adViews/questionsTypeManage";
    }

    @RequestMapping("/toAddQuestions")
    public String toAddQuetions(){
        return "adViews/addQuestions";
    }


    @RequestMapping("/toQuestionsManage")
    public String toQuetionsManage(){
        return "adViews/questionsManage";
    }

    @RequestMapping("/toHelpInfoManage")
    public String toHelpInfoManage(){
        return "adViews/helpInfoManage";
    }

    @RequestMapping("/toQuestionnaireManage")
    public String toQuestionnaireManage(){ return "adViews/questionnaireManage"; }

    @RequestMapping("/toStatisticManage")
    public String toStatisticManage(){
        return "adViews/statisticManage";
    }

    @RequestMapping("/toUpdatePst")
    public String toUpdatePst(){
        return "adViews/updatePst";
    }

    @RequestMapping("/toGetQuestionsDetail")
    public String toGetQuestionsDetail(){
        return "adViews/getQuestionsDetail";
    }

    @RequestMapping("/toAddQuestionsToNaire")
    public String toAddQuestionsToNaire(){
        return "adViews/addQuestionsToNaire";
    }

    @RequestMapping("/toGetStatisticResult")
    public String toGetStatisticResult(){
        return "adViews/getStatisticResult";
    }

    //得到所有的用户json LayUI 分页
    @ResponseBody
    @RequestMapping("/getUsersJson")
    public Map getUsersJson(String page, String limit){
        int pages = Integer.parseInt(page);
        int limits = Integer.parseInt(limit);
        pages = (pages-1)*limits;
        List<Users> users = adminService.selectUsersLimit(pages,limits);
        List<Users> users2 = adminService.selectUsers();
        Map<String,Object> param = new HashMap<>();
        param.put("code", 0);
        param.put("msg", "");
        param.put("count",users2.size());
        param.put("data", users);
        return param;
    }
    //模糊查询，用户名注册时间返回用户
    @ResponseBody
    @RequestMapping("/getUsersLike")
    public Map getUsersLike(String searchVal){
        String searchVal2 =  searchVal;
        List<Users> users = adminService.selectUsersLike(searchVal,searchVal2);
        Map<String,Object> param = new HashMap<>();
        param.put("code", 0);
        param.put("msg", "");
        param.put("count",users.size());
        param.put("data", users);
        return param;
    }
    //删除用户
    @ResponseBody
    @RequestMapping("/delUser")
    public boolean delUser(String uid){
        int re = adminService.delUser(Integer.parseInt(uid));
        if(re>0){
            return true;
        }else {return false;}
    }
    //修改用户
    @ResponseBody
    @RequestMapping("/updateUserInfo")
    public boolean updateUserInfo(String uid,String username,String sex){
        int re = adminService.updateUserInfo(Integer.parseInt(uid),username,sex);
        if(re>0){
            return true;
        }else {return false;}
    }
    //新增用户
    @ResponseBody
    @RequestMapping("/addUser")
    public boolean addUser(Users users){
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String sub_date = format.format(date);
        users.setRegTime(sub_date);
        int re = adminService.addUser(users);
        if(re>0){
            return true;
        }else {return false;}
    }
    //查询用户 uid
    @ResponseBody
    @RequestMapping("/selectUserByAccount")
    public boolean selectUserByAccount(Users users){
        boolean re = adminService.selectUserByAccount(users);
       return re;
    }

    //查询所有的问题类别 分页
    @ResponseBody
    @RequestMapping("/getQuestionsType")
    public Map getQuestionsType(String page, String limit){
        int pages = Integer.parseInt(page);
        int limits = Integer.parseInt(limit);
        pages = (pages-1)*limits;
        List<Questionstype> questionstypes = adminService.getQuestionsType(pages,limits);
        List<Questionstype> questionstypes2 = adminService.getAllQuestionsType();
        Map<String,Object> param = new HashMap<>();
        param.put("code", 0);
        param.put("msg", "");
        param.put("count",questionstypes2.size());
        param.put("data", questionstypes);
        return param;
    }
    //添加问题类别
    @ResponseBody
    @RequestMapping("/addQuestionType")
    public int addQuestionType(String qtName){
            boolean re = adminService.selectQuestionTypeByQtName(qtName);
            if(re){
                return 0;
            }else {
                boolean res = adminService.addQuestionType(qtName);
                if(res){
                    return 1;
                }else {
                    return 2;
                }
            }
    }
    //删除问题类别
    @ResponseBody
    @RequestMapping("/delQuestionsType")
    public boolean delQuestionsType(String qtid){
        return adminService.delQuestionsType(Integer.parseInt(qtid));
    }
    //修改问题类别
    @ResponseBody
    @RequestMapping("/updateQuestionsType")
    public boolean updateQuestionsType(String qtid,String qtName){
        return adminService.updateQuestionsType(Integer.parseInt(qtid),qtName);
    }

    //问题
    //查询所有的问题类别
    @ResponseBody
    @RequestMapping("/selectAllQuestionsType")
    public List<Questionstype> selectAllQuestionsType(){
        return adminService.selectAllQuestionsType();
    }
    //添加问题及其答案
    @ResponseBody
    @RequestMapping("/addQusAwr")
    public Map addQusAwr(@RequestBody String ques_detail){
        List<QusAwrs> qusAwrs = (List<QusAwrs>)JSONArray.parseArray(ques_detail,QusAwrs.class);
        HashMap<String,String> map = new HashMap<>();
        //限制同一个类型的题 其题目只能唯一
        boolean re1 = adminService.selectQuSame(qusAwrs.get(0).getQtid(),qusAwrs.get(1).getQtsName(),qusAwrs.get(2).getClassification());
        if(re1){
            //插入问题表
            if(qusAwrs.get(2).getClassification().equals("简答题")){
                boolean re2 = adminService.addQuestions(qusAwrs.get(0).getQtid(),qusAwrs.get(1).getQtsName(),qusAwrs.get(2).getClassification());
                if(re2){ map.put("reTip","添加成功！");}else { map.put("reTip","添加失败！");}
            }else {
                boolean re2 = adminService.addQuestions(qusAwrs.get(0).getQtid(),qusAwrs.get(1).getQtsName(),qusAwrs.get(2).getClassification());
                if(re2){  //插入此问题answer选项 简答题不需要
                    //查询问题id
                    String  quId = adminService.selectQuId(qusAwrs.get(0).getQtid(), qusAwrs.get(1).getQtsName(),qusAwrs.get(2).getClassification());
                    //构造list
                    List<Answers> answersList = new ArrayList<>();
                    for(int i=0;i<qusAwrs.get(3).getAnswers().length;i++){
                        Answers answers = new Answers();
                        answers.setQuId(quId);
                        answers.setAnswer(qusAwrs.get(3).getAnswers()[i]);
                        answersList.add(answers);
                    }
                    boolean re3 = adminService.addMuchRAnswers(answersList);
                    if(re3){ map.put("reTip","添加成功！");}else { map.put("reTip","添加失败！");}
                }else { map.put("reTip","添加失败！");}
            }
        }else {
            map.put("reTip","该类型题目已存在！");
        }
        return map;
    }
    //修改问题及其答案
    @ResponseBody
    @RequestMapping("/updateQusAwr")
    public Map updateQusAwr(@RequestBody String ques_detail){
        List<QusAwrs> qusAwrs = (List<QusAwrs>)JSONArray.parseArray(ques_detail,QusAwrs.class);
        HashMap<String,String> map = new HashMap<>();
            if(qusAwrs.get(2).getClassification().equals("简答题")){
                boolean re1  = adminService.updateQuestionsByQuid(Integer.parseInt(qusAwrs.get(0).getQuId()),qusAwrs.get(1).getQtsName());
                if(re1){
                    map.put("reTip","修改成功");
                }else {
                    map.put("reTip","修改失败");
                }
            }else {
                boolean re1  = adminService.updateQuestionsByQuid(Integer.parseInt(qusAwrs.get(0).getQuId()),qusAwrs.get(1).getQtsName());
                boolean re2 = adminService.deleteAnswersByQuid(qusAwrs.get(0).getQuId());
                List<Answers> answersList = new ArrayList<>();
                for(int i=0;i<qusAwrs.get(3).getAnswers().length;i++){
                    Answers answers = new Answers();
                    answers.setQuId(qusAwrs.get(0).getQuId());
                    answers.setAnswer(qusAwrs.get(3).getAnswers()[i]);
                    answersList.add(answers);
                }
                boolean re3 = adminService.addMuchRAnswers(answersList);
                if(re1 && re2 && re3){
                    map.put("reTip","修改成功");
                }else {
                    map.put("reTip","修改失败");
                }
            }
        return map;
    }
    //问题管理 查询问题 分页
    @ResponseBody
    @RequestMapping("/getQuestions")
    public Map getQuestions(String page, String limit, String qtid,String classification){
        int pages = Integer.parseInt(page);
        int limits = Integer.parseInt(limit);
        pages = (pages-1)*limits;
        List<Questions> questionsList = adminService.getQuestions(pages,limits,qtid,classification);
        List<Questions> questionsList2 = adminService.getAllQuestions(qtid,classification);
        Map<String,Object> param = new HashMap<>();
        param.put("code", 0);
        param.put("msg", "");
        param.put("count",questionsList2.size());
        param.put("data", questionsList);
        return param;
    }
    //删除此问题
    @ResponseBody
    @RequestMapping("/deleteQuestionsById")
    public boolean deleteQuestionsById(int quId){
        boolean re = adminService.deleteQuestionsById(quId);
        return re;
    }
    //查看此问题
    @ResponseBody
    @RequestMapping("/setQuestionsDetail")
    public boolean setQuestionsDetail(int quId, HttpServletRequest request){
        Questions questions = adminService.getQuestionsByQuid(quId);
        List<Answers> answersList = adminService.getAnswersByQuid(String.valueOf(quId));
        Map<String,Object> map = new HashMap();
        map.put("questions",questions);
        map.put("answersList",answersList);
        request.getSession().setAttribute("qutMap",map);
        return true;
    }
    @ResponseBody
    @RequestMapping("/getQuestionsDetail")
    public Map getQuestionsDetail(HttpServletRequest request){
        Map map  =  (Map) request.getSession().getAttribute("qutMap");
        return map;
    }

    /**帮助信息
     * */
    @ResponseBody
    @RequestMapping("/addHelpInfo")
    public boolean addHelpInfo(Helpinfo helpinfo){
        boolean re = adminService.addHelpInfo(helpinfo);
        return re;
    }
    @ResponseBody
    @RequestMapping("/getHelpInfo")
    public Map getHelpInfo(String page, String limit){
        int pages = Integer.parseInt(page);
        int limits = Integer.parseInt(limit);
        pages = (pages-1)*limits;
        List<Helpinfo> helpinfoList = adminService.getHelpInfo(pages,limits);
        List<Helpinfo> helpinfoList2 = adminService.getHelpInfos();
        Map<String,Object> param = new HashMap<>();
        param.put("code", 0);
        param.put("msg", "");
        param.put("count",helpinfoList2.size());
        param.put("data", helpinfoList);
        return param;
    }
    @ResponseBody
    @RequestMapping("/delHelpInfo")
    public boolean delHelpInfo(String hId){
        boolean re = adminService.delHelpInfo(Integer.parseInt(hId));
        return re;
    }
    @ResponseBody
    @RequestMapping("/updateHelpInfo")
    public boolean updateHelpInfo(Helpinfo helpinfo){
        boolean re = adminService.updateHelpInfo(Integer.parseInt(helpinfo.gethId()),helpinfo.getInfoTitle(),helpinfo.getInfoContent());
        return re;
    }

    /**问卷
     * */
    @ResponseBody
    @RequestMapping("/addQuestionnair")
    public boolean addQuestionnair(Questionnaire questionnaire){
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String createTime = format.format(date);
        String paperStste = "1";
        questionnaire.setCreateTime(createTime);
        questionnaire.setPaperState(paperStste);
        boolean re = adminService.addQuestionnaire(questionnaire);
        return re;
    }
    @ResponseBody
    @RequestMapping("/getQuestionnaire")
    public Map getQuestionnaire(String page, String limit){
        int pages = Integer.parseInt(page);
        int limits = Integer.parseInt(limit);
        pages = (pages-1)*limits;
        List<Questionnaire> questionnaireList = adminService.getQuestionnaire(pages,limits);
        List<Questionnaire> questionnaireList2 = adminService.getQuestionnaires();
        List<Questionnaire> questionnaireList1 = new ArrayList<>();
        for(int i=0;i<questionnaireList.size();i++){
            Questionnaire questionnaire = new Questionnaire();
            if(questionnaireList.get(i).getPaperState().equals("1")){
                questionnaire.setPaperState("未开启");
            }else if(questionnaireList.get(i).getPaperState().equals("2")){
                questionnaire.setPaperState("开启中");
            }else {
                questionnaire.setPaperState("已结束");
            }
            questionnaire.setCreateTime(questionnaireList.get(i).getCreateTime());
            questionnaire.setEndTime(questionnaireList.get(i).getEndTime());
            questionnaire.setPaperName(questionnaireList.get(i).getPaperName());
            questionnaire.setStartTime(questionnaireList.get(i).getStartTime());
            questionnaire.setQnId(questionnaireList.get(i).getQnId());
            questionnaireList1.add(questionnaire);
        }
        Map<String,Object> param = new HashMap<>();
        param.put("code", 0);
        param.put("msg", "");
        param.put("count",questionnaireList2.size());
        param.put("data", questionnaireList1);
        return param;
    }
    @ResponseBody
    @RequestMapping("/delQuestionnair")
    public boolean delQuestionnair(String qnId){
        boolean re = adminService.delQuestionnair(Integer.parseInt(qnId));
        return re;
    }
    @ResponseBody
    @RequestMapping("/closeQuestionnair")
    public boolean closeQuestionnair(String qnId){
        String re1= adminService.getStateByQnId(Integer.parseInt(qnId));
        if(re1.equals("2")){
            //关闭 添加统计表
            List<QuestionnaireQuestions> questionnaireQuestions = adminService.getQuIdes(Integer.parseInt(qnId));
            List<Questions> questionsList = adminService.getQuestionsByQuId(questionnaireQuestions);
            List<Answers> answersList = usersService.getAnswers(questionsList);
            List<Map<String,Object>> qus = new ArrayList<>();
            for(int i=0;i<questionsList.size();i++){
                for(int n=0;n<answersList.size();n++){
                    if(questionsList.get(i).getQuId().equals(answersList.get(n).getQuId())){
                            Map map = new HashMap();
                            map.put("qnId",Integer.parseInt(qnId));
                            map.put("qtsName",questionsList.get(i).getQtsName());
                            map.put("classification",questionsList.get(i).getClassification());
                            map.put("answer",answersList.get(n).getAnswer());
                            qus.add(map);
                    }
                }
                if(questionsList.get(i).getClassification().equals("简答题")){
                    Map map = new HashMap();
                    map.put("qnId",Integer.parseInt(qnId));
                    map.put("qtsName",questionsList.get(i).getQtsName());
                    map.put("classification",questionsList.get(i).getClassification());
                    qus.add(map);
                }
            }
            //tianjia
            List<Statistic> statisticList = new ArrayList<>();
            for(Map<String, Object> map : qus){
                if(map.get("classification").equals("简答题")){
                    continue;
                  /*  Myanswers myanswers = new Myanswers();
                    myanswers.setQnId((Integer) map.get("qnId"));
                    myanswers.setQtsName((String) map.get("qtsName"));
                    myanswers.setClassification((String)map.get("classification"));
                    String answer = adminService.getMyanswerAnswer(myanswers);
                    System.out.println(answer);
                    Statistic statistic = new Statistic();
                    statistic.setAnswer(answer);
                    statistic.setCount(0);
                    statistic.setQtsName((String) map.get("qtsName"));
                    statistic.setClassification((String)map.get("classification"));
                    statistic.setQnId((Integer) map.get("qnId"));
                    statisticList.add(statistic);*/
                }else{
                    Myanswers myanswers = new Myanswers();
                    myanswers.setQnId((Integer) map.get("qnId"));
                    myanswers.setQtsName((String) map.get("qtsName"));
                    myanswers.setAnswer((String)map.get("answer"));
                    myanswers.setClassification((String)map.get("classification"));
                    int counts = adminService.getMyanswerCut(myanswers);

                    Statistic statistic = new Statistic();
                    statistic.setAnswer((String)map.get("answer"));
                    statistic.setCounts(counts);
                    statistic.setQtsName((String) map.get("qtsName"));
                    statistic.setClassification((String)map.get("classification"));
                    statistic.setQnId((Integer) map.get("qnId"));
                    statisticList.add(statistic);
                }
            }
            int re = adminService.insertStatistices(statisticList);
            if(re>0){
                boolean re2 = adminService.closeQuestionnair(Integer.parseInt(qnId));
                return re2;
            }else{
                return false;
            }
        }else {
            return false;
        }
    }
    @ResponseBody
    @RequestMapping("/issueQuestionnair")
    public boolean issueQuestionnair(String qnId){
        String re1= adminService.getStateByQnId(Integer.parseInt(qnId));
        if(re1.equals("1")){
            boolean re2 = adminService.issueQuestionnair(Integer.parseInt(qnId));
            return re2;
        }else {
            return false;
        }
    }
    @ResponseBody
    @RequestMapping("/getQuestionnaireLike")
    public Map getQuestionnaireLike(String page, String limit,String searchVal){
        String searchVal1 = searchVal;
        String searchVal2 = searchVal;
        String searchVal3 = searchVal;
        int pages = Integer.parseInt(page);
        int limits = Integer.parseInt(limit);
        pages = (pages-1)*limits;
        List<Questionnaire> questionnaireList = adminService.getQuestionnaireLike(pages,limits,
                searchVal1,searchVal2,searchVal3);
        List<Questionnaire> questionnaireList1 = new ArrayList<>();
        for(int i=0;i<questionnaireList.size();i++){
            Questionnaire questionnaire = new Questionnaire();
            if(questionnaireList.get(i).getPaperState().equals("1")){
                questionnaire.setPaperState("未开启");
            }else if(questionnaireList.get(i).getPaperState().equals("2")){
                questionnaire.setPaperState("开启中");
            }else {
                questionnaire.setPaperState("已结束");
            }
            questionnaire.setCreateTime(questionnaireList.get(i).getCreateTime());
            questionnaire.setEndTime(questionnaireList.get(i).getEndTime());
            questionnaire.setPaperName(questionnaireList.get(i).getPaperName());
            questionnaire.setStartTime(questionnaireList.get(i).getStartTime());
            questionnaire.setQnId(questionnaireList.get(i).getQnId());
            questionnaireList1.add(questionnaire);
        }
        Map<String,Object> param = new HashMap<>();
        param.put("code", 0);
        param.put("msg", "");
        param.put("count",questionnaireList1.size());
        param.put("data", questionnaireList1);
        return param;
    }
    //设置qnId Session
    @ResponseBody
    @RequestMapping("/setSessionQnId")
    public boolean setSessionQnId(String qnId, HttpServletRequest request){
        request.getSession().setAttribute("qnId",qnId);
        String re = adminService.getStateByQnId(Integer.parseInt(qnId));
        if(re.equals("3") ||re.equals("2")){
            return false;
        }else {
            return true;
        }
    }
    @ResponseBody
    @RequestMapping("/getSessionQnId")
    public String getSessionQnId(HttpServletRequest request){
        String map  =   (String)request.getSession().getAttribute("qnId");
        return map;
    }
    @ResponseBody
    @RequestMapping("/getQuestionsByidcal")
    public List<Questions> getQuestionsByidcal(String qtid,String classification){
        List<Questions> questionsList = adminService.getQuestionsByidcal(qtid,classification);
        return  questionsList;
    }
    //tianjia
    @ResponseBody
    @RequestMapping("/addQusToNaire")
    public boolean addQusToNaire(int qnId,int quId){
        boolean re = adminService.addQusToNaire(qnId,quId);
        return re;
    }
    @ResponseBody
    @RequestMapping("/getQuestionsByQnId")
    public Map getQuestionsByQnId(String page, String limit,int qnId){
        int pages = Integer.parseInt(page);
        int limits = Integer.parseInt(limit);
        pages = (pages-1)*limits;
        List<QuestionnaireQuestions> questionnaireQuestions = adminService.getQuIdes(qnId);
        List<Questions> questionsList = adminService.getQuestionsByQuId(questionnaireQuestions);
        List<Questions> questionsList2 = adminService.getQuestionsByQuIdLimit(questionnaireQuestions,pages,limits);
        Map<String,Object> param = new HashMap<>();
        param.put("code", 0);
        param.put("msg", "");
        param.put("count",questionsList.size());
        param.put("data", questionsList2);
        return param;
    }
    @ResponseBody
    @RequestMapping("/deleteQQByQuId")
    public boolean deleteQQByQuId(int quId){
        boolean re = adminService.deleteQQByQuId(quId);
        return re;
    }


    @ResponseBody
    @RequestMapping("/getQuestionnaireState")
    public Map getQuestionnaireState(String page, String limit){
        int pages = Integer.parseInt(page);
        int limits = Integer.parseInt(limit);
        pages = (pages-1)*limits;
        List<Questionnaire> questionnaireList1 = adminService.getQuestionnairesState();
        List<Questionnaire> questionnaireList2 = adminService.getQuestionnaireByStateLimit(pages,limits);
        Map<String,Object> param = new HashMap<>();
        param.put("code", 0);
        param.put("msg", "");
        param.put("count",questionnaireList1.size());
        param.put("data", questionnaireList2);
        return param;
    }


    @ResponseBody
    @RequestMapping("/getXuZeStatistic")
    public Map getXuZeStatistic(String page, String limit,HttpServletRequest request){
        int pages = Integer.parseInt(page);
        int limits = Integer.parseInt(limit);
        pages = (pages-1)*limits;
        String qnId = (String)request.getSession().getAttribute("qnId");
        List<Statistic> statisticList1 = adminService.getXuZeStatistic(Integer.parseInt(qnId));
        List<Statistic> statisticList2 = adminService.getXuZeStatisticLimit(Integer.parseInt(qnId),pages,limits);
        Map<String,Object> param = new HashMap<>();
        param.put("code", 0);
        param.put("msg", "");
        param.put("count",statisticList1.size());
        param.put("data", statisticList2);
        return param;
    }

    @ResponseBody
    @RequestMapping("/getJiDaStatistic")
    public Map getJiDaStatistic(String page, String limit,HttpServletRequest request){
        int pages = Integer.parseInt(page);
        int limits = Integer.parseInt(limit);
        pages = (pages-1)*limits;
        String qnId = (String)request.getSession().getAttribute("qnId");
        List<Myanswers> myanswersList1 = adminService.getJiDaStatistic(Integer.parseInt(qnId));
        List<Myanswers> myanswersList2 = adminService.getJiDaStatisticLimit(Integer.parseInt(qnId),pages,limits);
        Map<String,Object> param = new HashMap<>();
        param.put("code", 0);
        param.put("msg", "");
        param.put("count",myanswersList1.size());
        param.put("data", myanswersList2);
        return param;
    }



}
