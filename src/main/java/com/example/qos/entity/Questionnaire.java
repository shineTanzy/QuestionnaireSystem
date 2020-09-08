package com.example.qos.entity;


public class Questionnaire {

  private String qnId;
  private String sId;
  private String createTime;
  private String paperName;
  private String startTime;
  private String endTime;
  private String paperState;


  public String getQnId() {
    return qnId;
  }

  public void setQnId(String qnId) {
    this.qnId = qnId;
  }

  public String getsId() {
    return sId;
  }

  public void setsId(String sId) {
    this.sId = sId;
  }

  public String getCreateTime() {
    return createTime;
  }

  public void setCreateTime(String createTime) {
    this.createTime = createTime;
  }


  public String getPaperName() {
    return paperName;
  }

  public void setPaperName(String paperName) {
    this.paperName = paperName;
  }


  public String getStartTime() {
    return startTime;
  }

  public void setStartTime(String startTime) {
    this.startTime = startTime;
  }


  public String getEndTime() {
    return endTime;
  }

  public void setEndTime(String endTime) {
    this.endTime = endTime;
  }


  public String getPaperState() {
    return paperState;
  }

  public void setPaperState(String paperState) {
    this.paperState = paperState;
  }

}
