package com.example.qos.controller;

import com.alibaba.fastjson.JSONArray;
import com.example.qos.entity.*;
import com.example.qos.service.AdminService;
import com.example.qos.service.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/user")
public class UserController {
    @Autowired
    UsersService usersService;
    @Autowired
    AdminService adminService;

    @RequestMapping("/toUserLogin")
    public String toUserLogin(){ return "userLogin"; }
    @RequestMapping("/toUserIndex")
    public String toUserIndex(){ return "views/userIndex"; }
    @RequestMapping("/toUpdatePwd")
    public String toUpdatePwd(){ return "views/updatePwd"; }
    @RequestMapping("/toHelpInfo")
    public String toHelpInfo(){ return "views/showHelpInfo"; }
    @RequestMapping("/toBeginQuestionnaire")
    public String toBeginQuestionnaire(){ return "views/beginQuestionnaire"; }

    @ResponseBody
    @RequestMapping("/regAccount")
    public boolean  regAccount(String account){
            boolean re = usersService.regAccount(account);
            return re;
    }
    @ResponseBody
    @RequestMapping("/regUser")
    public boolean  regUser(Users users){
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String sub_date = format.format(date);
        users.setRegTime(sub_date);
        boolean re = usersService.regUser(users);
        return re;
    }
    @ResponseBody
    @RequestMapping("/getLogin")
    public Map  getLogin(Users users, HttpServletRequest request){
        boolean re = usersService.getLogin(users);
        Users users1 = usersService.getUser(users);
        request.getSession().setAttribute("uId",users1.getUId());
        Map map = new HashMap();
        if(re){
            if(users1.getUserType().equals("1")){
                map.put("reTip","/admin/toAdminManage");
            }else {
                map.put("reTip","/user/toUserIndex");
            }
        }else {
            map.put("reTip","账号或密码错误!");
        }
        return map;
    }
    @ResponseBody
    @RequestMapping("/removeLogin")
    public boolean  removeLogin( HttpServletRequest request){
        request.getSession().removeAttribute("uId");
        return true;
    }
    @ResponseBody
    @RequestMapping("/regPassword")
    public Map  regPassword(String pwd,HttpServletRequest request){
        String uId = (String)request.getSession().getAttribute("uId");
        boolean re = usersService.regPassword(pwd,Integer.parseInt(uId));
        Map map = new HashMap();
        if(re){
            map.put("reTip","原密码正确！");
        }else {
            map.put("reTip","原密码错误！");
        }
        return map;
    }
    @ResponseBody
    @RequestMapping("/updatePassword")
    public Map  updatePassword(String userpassword,HttpServletRequest request){
        String uId = (String)request.getSession().getAttribute("uId");
        boolean re = usersService.updatePassword(userpassword,Integer.parseInt(uId));
        Map map = new HashMap();
        if(re){
            map.put("reTip","修改成功！");
        }else {
            map.put("reTip","修改失败！");
        }
        return map;
    }
    @ResponseBody
    @RequestMapping("/getSessionUid")
    public String getSessionQnId(HttpServletRequest request){
        String uId = (String)request.getSession().getAttribute("uId");
        return uId;
    }

    @ResponseBody
    @RequestMapping("/getQuestionnaireState")
    public Map getQuestionnaireState(String page, String limit){
        int pages = Integer.parseInt(page);
        int limits = Integer.parseInt(limit);
        pages = (pages-1)*limits;
        List<Questionnaire> questionnaireList = usersService.getQuestionnaireState();
        List<Questionnaire> questionnaireList2 = usersService.getQuestionnaireStateLimit(pages,limits);
        Map<String,Object> param = new HashMap<>();
        param.put("code", 0);
        param.put("msg", "");
        param.put("count",questionnaireList.size());
        param.put("data", questionnaireList2);
        return param;
    }
    @ResponseBody
    @RequestMapping("/setSessionQnId")
    public Map setSessionQnId(String qnId, HttpServletRequest request){
        Map map = new HashMap();
        String uId = (String)request.getSession().getAttribute("uId");
        System.out.println(uId);
        System.out.println(qnId);
        boolean reg = usersService.regMyAnswers(Integer.parseInt(qnId),Integer.parseInt(uId));
        System.out.println(reg);
        if(reg){
            map.put("reTip","此问卷您已做答！");
        }else {
            request.getSession().setAttribute("qnId",qnId);
        }
        return map;
    }
    @ResponseBody
    @RequestMapping("/getNaireQuestions")
    public List<Map> getNaireQuestions(int qnId){
//        System.out.println(qnId);
        Questionnaire questionnaire = usersService.getQuestionnaire(qnId);
        List<QuestionnaireQuestions> questionnaireQuestions = adminService.getQuIdes(qnId);
        List<Questions> questionsList = adminService.getQuestionsByQuId(questionnaireQuestions);
        List<Answers> answersList = usersService.getAnswers(questionsList);
//        System.out.println(questionsList.size());
//        System.out.println(answersList.size());
        List<Map> qus = new ArrayList<>();
        for(int i=0;i<questionsList.size();i++){
            Map map = new HashMap();
            int c = i+1;
            map.put("qtsName",String.valueOf(c)+"."+questionsList.get(i).getQtsName());
            map.put("classification",questionsList.get(i).getClassification());
            map.put("quId",questionsList.get(i).getQuId());
            map.put("paperName",questionnaire.getPaperName());
            List<String> answers = new ArrayList<>();
            for(int n=0;n<answersList.size();n++){
                if(questionsList.get(i).getQuId().equals(answersList.get(n).getQuId())){
                    answers.add(answersList.get(n).getAnswer());
                }
            }
            map.put("answers",answers);
            qus.add(map);
        }
        return qus;
    }

    @ResponseBody
    @RequestMapping("/addMyAnswers")
    public boolean addMyAnswers(@RequestBody String myAnswers){
        List<Myanswers> myanswersList = (List<Myanswers>) JSONArray.parseArray(myAnswers,Myanswers.class);
        boolean re = usersService.addMyAnswers(myanswersList);
        return  re;
    }
    @ResponseBody
    @RequestMapping("/getHelpInfos")
    public List<Helpinfo> getHelpInfos() {
            List<Helpinfo> helpinfoList = usersService.getHelpInfos();
            return  helpinfoList;
    }



}
