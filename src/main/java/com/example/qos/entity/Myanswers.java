package com.example.qos.entity;

public class Myanswers {

  private int maId;
  private int uId;
  private int qnId;
  private String qtsName;
  private String answer;
  private String classification;

  public String getClassification() {
    return classification;
  }

  public void setClassification(String classification) {
    this.classification = classification;
  }

  public int getMaId() {
    return maId;
  }

  public void setMaId(int maId) {
    this.maId = maId;
  }

  public int getuId() {
    return uId;
  }

  public void setuId(int uId) {
    this.uId = uId;
  }

  public int getQnId() {
    return qnId;
  }

  public void setQnId(int qnId) {
    this.qnId = qnId;
  }

  public String getQtsName() {
    return qtsName;
  }

  public void setQtsName(String qtsName) {
    this.qtsName = qtsName;
  }

  public String getAnswer() {
    return answer;
  }

  public void setAnswer(String answer) {
    this.answer = answer;
  }
}
