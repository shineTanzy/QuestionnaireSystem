package com.example.qos.entity;

public class QusAwrs {
    private String quId;
    private String qtid;//问题类别id
    private String qtsName;//问题题目
    private String classification;//问题题型
    private String [] answers;//问题回答选项

    public String getQuId() {
        return quId;
    }

    public void setQuId(String quId) {
        this.quId = quId;
    }

    public String getQtid() {
        return qtid;
    }

    public void setQtid(String qtid) {
        this.qtid = qtid;
    }

    public String getQtsName() {
        return qtsName;
    }

    public void setQtsName(String qtsName) {
        this.qtsName = qtsName;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String[] getAnswers() {
        return answers;
    }

    public void setAnswers(String[] answers) {
        this.answers = answers;
    }
}
