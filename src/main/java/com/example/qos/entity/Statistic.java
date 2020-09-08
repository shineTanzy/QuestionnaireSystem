package com.example.qos.entity;


public class Statistic {

  private int sId;
  private String qtsName;
  private String answer;
  private int  counts;
  private String classification;
  private int qnId;

  public String getClassification() {
    return classification;
  }

  public void setClassification(String classification) {
    this.classification = classification;
  }

  public int getQnId() {
    return qnId;
  }

  public void setQnId(int qnId) {
    this.qnId = qnId;
  }

  public int getsId() {
    return sId;
  }

  public void setsId(int sId) {
    this.sId = sId;
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

    public int getCounts() {
        return counts;
    }

    public void setCounts(int counts) {
        this.counts = counts;
    }
}
