/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2020/3/20 11:44:07                           */
/*==============================================================*/


drop table if exists answers;

drop table if exists helpinfo;

drop table if exists myanswers;

drop table if exists questionnaire;

drop table if exists questionnaire_questions;

drop table if exists questions;

drop table if exists questionsType;

drop table if exists statistic;

drop table if exists users;

/*==============================================================*/
/* Table: answers                                               */
/*==============================================================*/
create table answers
(
   caId                 int not null auto_increment,
   quId                 int comment '问题编号',
   answer               varchar(256),
   primary key (caId)
);

alter table answers comment '问题答案选项表';

/*==============================================================*/
/* Table: helpinfo                                              */
/*==============================================================*/
create table helpinfo
(
   hId                  int not null auto_increment,
   infoTitle            varchar(128) comment '帮助信息标题',
   infoContent          varchar(256) comment '帮助信息内容',
   primary key (hId)
);

alter table helpinfo comment '帮助信息
';

/*==============================================================*/
/* Table: myanswers                                             */
/*==============================================================*/
create table myanswers
(
   maId                 int not null auto_increment,
   uId                  int,
   qnId                 int,
   qtsName              varchar(64) comment '问题名称',
   answer               varchar(256),
   primary key (maId)
);

alter table myanswers comment '问卷调查回答记录表';

/*==============================================================*/
/* Table: questionnaire                                         */
/*==============================================================*/
create table questionnaire
(
   qnId                 int not null auto_increment,
   sId                  int,
   createTime           varchar(64) comment '创建时间',
   paperName            varchar(128) comment '问卷名',
   startTime            varchar(64) comment '开始问卷时间',
   endTime              varchar(64) comment '结束问卷时间',
   paperState           varchar(16) comment '1：未开启问卷，2：开启问卷，3：问卷已结束',
   primary key (qnId)
);

alter table questionnaire comment '问卷表';

/*==============================================================*/
/* Table: questionnaire_questions                               */
/*==============================================================*/
create table questionnaire_questions
(
   qnqId                int not null auto_increment,
   quId                 int comment '问题编号',
   qnId                 int,
   primary key (qnqId)
);

/*==============================================================*/
/* Table: questions                                             */
/*==============================================================*/
create table questions
(
   quId                 int not null auto_increment comment '问题编号',
   qtid                 int comment '问题类别编号',
   qtsName              varchar(64) comment '问题名称',
   primary key (quId)
);

alter table questions comment '问题
';

/*==============================================================*/
/* Table: questionsType                                         */
/*==============================================================*/
create table questionsType
(
   qtid                 int not null auto_increment comment '问题类别编号',
   qtName               char(256) comment '问题类别名称',
   primary key (qtid)
);

alter table questionsType comment '问题类别';

/*==============================================================*/
/* Table: statistic                                             */
/*==============================================================*/
create table statistic
(
   sId                  int not null auto_increment,
   qtsName              varchar(64) comment '问题名称',
   answer               varchar(256),
   count                int comment '次数',
   primary key (sId)
);

alter table statistic comment '统计表
';

/*==============================================================*/
/* Table: users                                                 */
/*==============================================================*/
create table users
(
   uId                  int not null auto_increment,
   username             varchar(64) comment '用户名',
   account              varchar(32) comment '账号',
   userpassword         varchar(32) comment '密码',
   regTime              varchar(32) comment '注册时间',
   sex                  varchar(4) comment '性别',
   primary key (uId)
);

alter table users comment '用户';

alter table answers add constraint FK_qcid foreign key (quId)
      references questions (quId) on delete restrict on update restrict;

alter table myanswers add constraint FK_拥有 foreign key (qnId)
      references questionnaire (qnId) on delete restrict on update restrict;

alter table myanswers add constraint FK_记录 foreign key (uId)
      references users (uId) on delete restrict on update restrict;

alter table questionnaire add constraint FK_统计 foreign key (sId)
      references statistic (sId) on delete restrict on update restrict;

alter table questionnaire_questions add constraint FK_qu_re foreign key (quId)
      references questions (quId) on delete restrict on update restrict;

alter table questionnaire_questions add constraint FK_re_qu foreign key (qnId)
      references questionnaire (qnId) on delete restrict on update restrict;

alter table questions add constraint FK_qid foreign key (qtid)
      references questionsType (qtid) on delete restrict on update restrict;

