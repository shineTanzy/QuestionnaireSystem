package com.example.qos.entity;


public class Users {

  private String uId;
  private String username;
  private String account;
  private String userpassword;
  private String regTime;
  private String sex;
  private String userType;

  public String getuId() {
    return uId;
  }

  public void setuId(String uId) {
    this.uId = uId;
  }

  public String getUserType() {
    return userType;
  }

  public void setUserType(String userType) {
    this.userType = userType;
  }

  public String getUId() {
    return uId;
  }

  public void setUId(String uId) {
    this.uId = uId;
  }


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }


  public String getAccount() {
    return account;
  }

  public void setAccount(String account) {
    this.account = account;
  }


  public String getUserpassword() {
    return userpassword;
  }

  public void setUserpassword(String userpassword) {
    this.userpassword = userpassword;
  }


  public String getRegTime() {
    return regTime;
  }

  public void setRegTime(String regTime) {
    this.regTime = regTime;
  }


  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

}
