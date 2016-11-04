/*==============================================================*/
/* Database name:  Database_1                                   */
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2016/11/2 9:51:21                            */
/*==============================================================*/


drop database if exists Database_1;

/*==============================================================*/
/* Database: Database_1                                         */
/*==============================================================*/
create database Database_1;

use Database_1;

/*==============================================================*/
/* Table: T_APPLY                                               */
/*==============================================================*/
create table T_APPLY
(
   ID                   varchar(64) not null,
   APP_ID               varchar(32) not null,
   PRODUCT_CODE         varchar(10) not null,
   PERIOD               int not null,
   COMMENT              varchar(256),
   STATUS               varchar(10) not null,
   CREATE_BRANCH_CODE   varchar(64) not null,
   CREATE_ACCOUNT_ID    varchar(64) not null,
   CREATE_TIME          timestamp not null,
   CUST_TYPE            varchar(2),
   TOTAL_FINANCE_AMT    double(15,2),
   TOTAL_LOAN_AMT       double(15,2),
   MONTH_RENT           double(15,2),
   APPROVE_DATE         date,
   PROC_INST_ID         varchar(64),
   CANCEL_DATE          date,
   REFUSE_DATE          date,
   primary key (ID)
);

alter table T_APPLY comment '申请信息';

alter table T_APPLY modify column ID varchar(64)  comment '主键';

alter table T_APPLY modify column APP_ID varchar(32)  comment '申请单号';

alter table T_APPLY modify column PRODUCT_CODE varchar(10)  comment '产品编码';

alter table T_APPLY modify column PERIOD int  comment '融资期限';

alter table T_APPLY modify column COMMENT varchar(256)  comment '申请备注';

alter table T_APPLY modify column STATUS varchar(10)  comment '申请状态';

alter table T_APPLY modify column CREATE_BRANCH_CODE varchar(64)  comment '录入机构';

alter table T_APPLY modify column CREATE_ACCOUNT_ID varchar(64)  comment '录入人员';

alter table T_APPLY modify column CREATE_TIME timestamp  comment '录入时间';

alter table T_APPLY modify column CUST_TYPE varchar(2)  comment '客户类型';

alter table T_APPLY modify column TOTAL_FINANCE_AMT double(15,2)  comment '融资总金额';

alter table T_APPLY modify column TOTAL_LOAN_AMT double(15,2)  comment '放款总金额';

alter table T_APPLY modify column MONTH_RENT double(15,2)  comment '月租金';

alter table T_APPLY modify column APPROVE_DATE date  comment '批核日期';

alter table T_APPLY modify column PROC_INST_ID varchar(64)  comment '任务实例ID';

alter table T_APPLY modify column CANCEL_DATE date  comment '取消日期';

alter table T_APPLY modify column REFUSE_DATE date  comment '拒绝日期';

/*==============================================================*/
/* Index: Index_1                                               */
/*==============================================================*/
create unique index Index_1 on T_APPLY
(
   APP_ID
);

/*==============================================================*/
/* Table: T_APPLY_CHANGE                                        */
/*==============================================================*/
create table T_APPLY_CHANGE
(
   ID                   varchar(64) not null,
   APP_ID               varchar(64) not null,
   FIELD_NAME           varchar(64) not null,
   PRE_VALUE            varchar(128),
   AFTER_VALUE          varchar(128),
   OPER_ACCOUNT_ID      varchar(64) not null,
   CHANGE_TIME          timestamp not null,
   primary key (ID)
);

alter table T_APPLY_CHANGE comment '申请信息变更记录';

alter table T_APPLY_CHANGE modify column ID varchar(64)  comment '主键';

alter table T_APPLY_CHANGE modify column APP_ID varchar(64)  comment '申请单编号';

alter table T_APPLY_CHANGE modify column FIELD_NAME varchar(64)  comment '字段名称';

alter table T_APPLY_CHANGE modify column PRE_VALUE varchar(128)  comment '变更前值';

alter table T_APPLY_CHANGE modify column AFTER_VALUE varchar(128)  comment '变更后值';

alter table T_APPLY_CHANGE modify column OPER_ACCOUNT_ID varchar(64)  comment '变更执行人';

alter table T_APPLY_CHANGE modify column CHANGE_TIME timestamp  comment '变更时间';

/*==============================================================*/
/* Table: T_APPLY_COLESSEE                                      */
/*==============================================================*/
create table T_APPLY_COLESSEE
(
   ID                   varchar(64) not null,
   APP_ID               varchar(32) not null,
   TYPE                 varchar(10),
   NAME                 varchar(32),
   ID_TYPE              varchar(10),
   ID_NO                varchar(32),
   MOBILE               varchar(32),
   QQ                   varchar(32),
   WEIXIN               varchar(32),
   UNIT_NAME            varchar(64),
   UNIT_TYPE            varchar(10),
   UNIT_INDUSTRY        varchar(10),
   UNIT_TEL             varchar(32),
   RANK                 varchar(10),
   RANK_NAME            varchar(32),
   UNIT_ADDR_PROVINCE   varchar(64),
   UNIT_ADDR_CITY       varchar(64),
   UNIT_ADDR_COUNTY     varchar(64),
   UNIT_ADDR_EXT        varchar(128),
   MONTH_INCOME         double(15,2),
   YEAR_INCOME          double(15,2),
   RELATION             varchar(10),
   primary key (ID)
);

alter table T_APPLY_COLESSEE comment '共租人信息';

alter table T_APPLY_COLESSEE modify column ID varchar(64)  comment '主键';

alter table T_APPLY_COLESSEE modify column APP_ID varchar(32)  comment '申请单号';

alter table T_APPLY_COLESSEE modify column TYPE varchar(10)  comment '类型';

alter table T_APPLY_COLESSEE modify column NAME varchar(32)  comment '共租人姓名';

alter table T_APPLY_COLESSEE modify column ID_TYPE varchar(10)  comment '证件类型';

alter table T_APPLY_COLESSEE modify column ID_NO varchar(32)  comment '证件号码';

alter table T_APPLY_COLESSEE modify column MOBILE varchar(32)  comment '手机号码';

alter table T_APPLY_COLESSEE modify column QQ varchar(32)  comment 'QQ号';

alter table T_APPLY_COLESSEE modify column WEIXIN varchar(32)  comment '微信号';

alter table T_APPLY_COLESSEE modify column UNIT_NAME varchar(64)  comment '单位名称';

alter table T_APPLY_COLESSEE modify column UNIT_TYPE varchar(10)  comment '单位类型';

alter table T_APPLY_COLESSEE modify column UNIT_INDUSTRY varchar(10)  comment '所属行业';

alter table T_APPLY_COLESSEE modify column UNIT_TEL varchar(32)  comment '单位电话';

alter table T_APPLY_COLESSEE modify column RANK varchar(10)  comment '职级';

alter table T_APPLY_COLESSEE modify column RANK_NAME varchar(32)  comment '职位名称';

alter table T_APPLY_COLESSEE modify column UNIT_ADDR_PROVINCE varchar(64)  comment '单位地址所属省';

alter table T_APPLY_COLESSEE modify column UNIT_ADDR_CITY varchar(64)  comment '单位地址所属市';

alter table T_APPLY_COLESSEE modify column UNIT_ADDR_COUNTY varchar(64)  comment '单位地址所属区';

alter table T_APPLY_COLESSEE modify column UNIT_ADDR_EXT varchar(128)  comment '单位地址明细';

alter table T_APPLY_COLESSEE modify column MONTH_INCOME double(15,2)  comment '月收入';

alter table T_APPLY_COLESSEE modify column YEAR_INCOME double(15,2)  comment '年收入';

alter table T_APPLY_COLESSEE modify column RELATION varchar(10)  comment '与本人关系';

/*==============================================================*/
/* Index: Index_1                                               */
/*==============================================================*/
create unique index Index_1 on T_APPLY_COLESSEE
(
   APP_ID
);

/*==============================================================*/
/* Table: T_APPLY_FAMILY_DEBT                                   */
/*==============================================================*/
create table T_APPLY_FAMILY_DEBT
(
   ID                   varchar(64) not null,
   APP_ID               varchar(32) not null,
   BANK_LOAN_AMOUNT     double(15,2),
   BANK_LOAN_YHK        double(15,2),
   PETTY_LOAN_AMOUT     double(15,2),
   PETTY_LOAN_YHK       double(15,2),
   DB_LOAN_AMOUNT       double(15,2),
   DB_LOAN_YHK          double(15,2),
   CREDIT_LOAN_AMOUNT   double(15,2),
   CREDIT_LOAN_YHK      double(15,2),
   RELATIVE_LOAN_AMOUNT double(15,2),
   RELATIVE_LOAN_YHK    double(15,2),
   FRIEND_LOAN_AMOUNT   double(15,2),
   FRIEND_LOAN_YHK      double(15,2),
   EASYCAR_LOAN_AMOUNT  double(15,2),
   EASYCAR_LOAN_YHK     double(15,2),
   LOAN_TOTAL           double(15,2),
   LOAN_YHK_TOTAL       double(15,2),
   primary key (ID)
);

alter table T_APPLY_FAMILY_DEBT comment '家庭负债信息';

alter table T_APPLY_FAMILY_DEBT modify column ID varchar(64)  comment '主键';

alter table T_APPLY_FAMILY_DEBT modify column APP_ID varchar(32)  comment '申请编号';

alter table T_APPLY_FAMILY_DEBT modify column BANK_LOAN_AMOUNT double(15,2)  comment '银行贷款欠款金额';

alter table T_APPLY_FAMILY_DEBT modify column BANK_LOAN_YHK double(15,2)  comment '银行贷款月还款';

alter table T_APPLY_FAMILY_DEBT modify column PETTY_LOAN_AMOUT double(15,2)  comment '小贷欠款金额';

alter table T_APPLY_FAMILY_DEBT modify column PETTY_LOAN_YHK double(15,2)  comment '小贷月还款';

alter table T_APPLY_FAMILY_DEBT modify column DB_LOAN_AMOUNT double(15,2)  comment '担保公司欠款金额';

alter table T_APPLY_FAMILY_DEBT modify column DB_LOAN_YHK double(15,2)  comment '担保公司月还款';

alter table T_APPLY_FAMILY_DEBT modify column CREDIT_LOAN_AMOUNT double(15,2)  comment '信用卡欠款金额';

alter table T_APPLY_FAMILY_DEBT modify column CREDIT_LOAN_YHK double(15,2)  comment '信用卡月还款';

alter table T_APPLY_FAMILY_DEBT modify column RELATIVE_LOAN_AMOUNT double(15,2)  comment '亲戚欠款金额';

alter table T_APPLY_FAMILY_DEBT modify column RELATIVE_LOAN_YHK double(15,2)  comment '亲戚月还款';

alter table T_APPLY_FAMILY_DEBT modify column FRIEND_LOAN_AMOUNT double(15,2)  comment '朋友欠款金额';

alter table T_APPLY_FAMILY_DEBT modify column FRIEND_LOAN_YHK double(15,2)  comment '朋友月还款';

alter table T_APPLY_FAMILY_DEBT modify column EASYCAR_LOAN_AMOUNT double(15,2)  comment '租车公司欠款金额';

alter table T_APPLY_FAMILY_DEBT modify column EASYCAR_LOAN_YHK double(15,2)  comment '租车公司月还款';

alter table T_APPLY_FAMILY_DEBT modify column LOAN_TOTAL double(15,2)  comment '欠款合计';

alter table T_APPLY_FAMILY_DEBT modify column LOAN_YHK_TOTAL double(15,2)  comment '月还款合计';

/*==============================================================*/
/* Index: Index_1                                               */
/*==============================================================*/
create unique index Index_1 on T_APPLY_FAMILY_DEBT
(
   APP_ID
);

/*==============================================================*/
/* Table: T_APPLY_FINANCE                                       */
/*==============================================================*/
create table T_APPLY_FINANCE
(
   ID                   varchar(64) not null,
   APP_ID               varchar(32) not null,
   SEQ                  int not null,
   CAR_STYLE_ID         varchar(64),
   SALE_PRICE           double(15,2),
   INIT_PAY_PERCENT     double(15,6),
   IS_FINANCE_GPS       bool,
   GPS_LVL_ID           varchar(64),
   GPS_FEE              double(15,2),
   IS_PURCHASE_TAX      bool,
   PURCHASE_TAX         double(15,2),
   IS_SERVICE_FEE       bool,
   SERVICE_FEE          double(15,2),
   IS_INSURANCE_FEE     bool,
   INSURANCE_FEE        double(15,2),
   IS_DELAY_INSURANCE_FEE bool,
   DELAY_INSURANCE_FEE  double(15,2),
   IS_TRANSFER_FEE      bool,
   TRANSFER_FEE         double(15,2),
   IS_ADDON_FEE         bool,
   ADDON_FEE            double(15,2),
   ASSESS_FEE           double(15,2),
   CAR_COLOR            varchar(32),
   CAR_MANU             varchar(32),
   CAR_VIN              varchar(32),
   CAR_ENGINE_NO        varchar(32),
   ASSESS_PRICE         double(15,2),
   COLLATERAL           double(15,2),
   INIT_PAY_AMOUNT      double(15,2),
   FINANCE_AMOUNT       double(15,2),
   FINANCE_FEE          double(15,2),
   COMMENT              varchar(256),
   primary key (ID)
);

alter table T_APPLY_FINANCE comment '融资信息';

alter table T_APPLY_FINANCE modify column ID varchar(64)  comment '主键';

alter table T_APPLY_FINANCE modify column APP_ID varchar(32)  comment '申请单号';

alter table T_APPLY_FINANCE modify column SEQ int  comment '融资序号';

alter table T_APPLY_FINANCE modify column CAR_STYLE_ID varchar(64)  comment '车辆信息';

alter table T_APPLY_FINANCE modify column SALE_PRICE double(15,2)  comment '裸车价';

alter table T_APPLY_FINANCE modify column INIT_PAY_PERCENT double(15,6)  comment '首付比例';

alter table T_APPLY_FINANCE modify column IS_FINANCE_GPS bool  comment '是否加融GPS';

alter table T_APPLY_FINANCE modify column GPS_LVL_ID varchar(64)  comment '所选GPS档位';

alter table T_APPLY_FINANCE modify column GPS_FEE double(15,2)  comment 'GPS费';

alter table T_APPLY_FINANCE modify column IS_PURCHASE_TAX bool  comment '是否加融购置税';

alter table T_APPLY_FINANCE modify column PURCHASE_TAX double(15,2)  comment '购置税';

alter table T_APPLY_FINANCE modify column IS_SERVICE_FEE bool  comment '是否加融服务费';

alter table T_APPLY_FINANCE modify column SERVICE_FEE double(15,2)  comment '服务费';

alter table T_APPLY_FINANCE modify column IS_INSURANCE_FEE bool  comment '是否加融保险费';

alter table T_APPLY_FINANCE modify column INSURANCE_FEE double(15,2)  comment '保险费';

alter table T_APPLY_FINANCE modify column IS_DELAY_INSURANCE_FEE bool  comment '是否加融延保费';

alter table T_APPLY_FINANCE modify column DELAY_INSURANCE_FEE double(15,2)  comment '延保费';

alter table T_APPLY_FINANCE modify column IS_TRANSFER_FEE bool  comment '是否加融过户费';

alter table T_APPLY_FINANCE modify column TRANSFER_FEE double(15,2)  comment '过户费';

alter table T_APPLY_FINANCE modify column IS_ADDON_FEE bool  comment '是否加融加装费';

alter table T_APPLY_FINANCE modify column ADDON_FEE double(15,2)  comment '加装费';

alter table T_APPLY_FINANCE modify column ASSESS_FEE double(15,2)  comment '评估费';

alter table T_APPLY_FINANCE modify column CAR_COLOR varchar(32)  comment '车辆颜色';

alter table T_APPLY_FINANCE modify column CAR_MANU varchar(32)  comment '生产商';

alter table T_APPLY_FINANCE modify column CAR_VIN varchar(32)  comment '车架号';

alter table T_APPLY_FINANCE modify column CAR_ENGINE_NO varchar(32)  comment '发动机号';

alter table T_APPLY_FINANCE modify column ASSESS_PRICE double(15,2)  comment '评估价';

alter table T_APPLY_FINANCE modify column COLLATERAL double(15,2)  comment '保证金';

alter table T_APPLY_FINANCE modify column INIT_PAY_AMOUNT double(15,2)  comment '首付款';

alter table T_APPLY_FINANCE modify column FINANCE_AMOUNT double(15,2)  comment '融资金额';

alter table T_APPLY_FINANCE modify column FINANCE_FEE double(15,2)  comment '融资手续费';

alter table T_APPLY_FINANCE modify column COMMENT varchar(256)  comment '补充信息';

/*==============================================================*/
/* Index: Index_1                                               */
/*==============================================================*/
create unique index Index_1 on T_APPLY_FINANCE
(
   APP_ID,
   SEQ
);

/*==============================================================*/
/* Table: T_APPLY_LINKMAN                                       */
/*==============================================================*/
create table T_APPLY_LINKMAN
(
   ID                   varchar(64) not null,
   APP_ID               varchar(32) not null,
   SEQ                  int not null,
   NAME                 varchar(32),
   MOBILE               varchar(32),
   TENANT_RELATION      varchar(10),
   ADDR_PROVINCE        varchar(64),
   ADDR_CITY            varchar(64),
   ADDR_COUNTY          varchar(64),
   ADDR_EXT             varchar(128),
   IS_KNOW              varchar(64),
   primary key (ID)
);

alter table T_APPLY_LINKMAN comment '联系人信息';

alter table T_APPLY_LINKMAN modify column ID varchar(64)  comment '主键';

alter table T_APPLY_LINKMAN modify column APP_ID varchar(32)  comment '申请单号';

alter table T_APPLY_LINKMAN modify column SEQ int  comment '序号';

alter table T_APPLY_LINKMAN modify column NAME varchar(32)  comment '姓名';

alter table T_APPLY_LINKMAN modify column MOBILE varchar(32)  comment '手机号码';

alter table T_APPLY_LINKMAN modify column TENANT_RELATION varchar(10)  comment '与承租人关系';

alter table T_APPLY_LINKMAN modify column ADDR_PROVINCE varchar(64)  comment '住址所属省';

alter table T_APPLY_LINKMAN modify column ADDR_CITY varchar(64)  comment '住址所属市';

alter table T_APPLY_LINKMAN modify column ADDR_COUNTY varchar(64)  comment '住址所属区';

alter table T_APPLY_LINKMAN modify column ADDR_EXT varchar(128)  comment '住址明细';

alter table T_APPLY_LINKMAN modify column IS_KNOW varchar(64)  comment '是否知道购车';

/*==============================================================*/
/* Index: Index_1                                               */
/*==============================================================*/
create index Index_1 on T_APPLY_LINKMAN
(
   APP_ID
);

/*==============================================================*/
/* Table: T_APPLY_SPOUSE                                        */
/*==============================================================*/
create table T_APPLY_SPOUSE
(
   ID                   varchar(64) not null,
   APP_ID               varchar(32) not null,
   NAME                 varchar(32),
   ID_TYPE              varchar(10),
   ID_NO                varchar(32),
   MOBILE               varchar(32),
   QQ                   varchar(32),
   WEIXIN               varchar(32),
   UNIT_NAME            varchar(64),
   UNIT_TYPE            varchar(10),
   UNIT_INDUSTRY        varchar(10),
   UNIT_TEL             varchar(32),
   RANK                 varchar(10),
   RANK_NAME            varchar(32),
   UNIT_ADDR_PROVINCE   varchar(64),
   UNIT_ADDR_CITY       varchar(64),
   UNIT_ADDR_COUNTY     varchar(64),
   UNIT_ADDR_EXT        varchar(128),
   MONTH_INCOME         double(15,2),
   YEAR_INCOME          double(15,2),
   primary key (ID)
);

alter table T_APPLY_SPOUSE comment '配偶信息';

alter table T_APPLY_SPOUSE modify column ID varchar(64)  comment '主键';

alter table T_APPLY_SPOUSE modify column APP_ID varchar(32)  comment '申请单号';

alter table T_APPLY_SPOUSE modify column NAME varchar(32)  comment '配偶姓名';

alter table T_APPLY_SPOUSE modify column ID_TYPE varchar(10)  comment '证件类型';

alter table T_APPLY_SPOUSE modify column ID_NO varchar(32)  comment '证件号码';

alter table T_APPLY_SPOUSE modify column MOBILE varchar(32)  comment '手机号码';

alter table T_APPLY_SPOUSE modify column QQ varchar(32)  comment 'QQ号';

alter table T_APPLY_SPOUSE modify column WEIXIN varchar(32)  comment '微信号';

alter table T_APPLY_SPOUSE modify column UNIT_NAME varchar(64)  comment '单位名称';

alter table T_APPLY_SPOUSE modify column UNIT_TYPE varchar(10)  comment '单位类型';

alter table T_APPLY_SPOUSE modify column UNIT_INDUSTRY varchar(10)  comment '所属行业';

alter table T_APPLY_SPOUSE modify column UNIT_TEL varchar(32)  comment '单位电话';

alter table T_APPLY_SPOUSE modify column RANK varchar(10)  comment '职级';

alter table T_APPLY_SPOUSE modify column RANK_NAME varchar(32)  comment '职位名称';

alter table T_APPLY_SPOUSE modify column UNIT_ADDR_PROVINCE varchar(64)  comment '单位地址所属省';

alter table T_APPLY_SPOUSE modify column UNIT_ADDR_CITY varchar(64)  comment '单位地址所属市';

alter table T_APPLY_SPOUSE modify column UNIT_ADDR_COUNTY varchar(64)  comment '单位地址所属区';

alter table T_APPLY_SPOUSE modify column UNIT_ADDR_EXT varchar(128)  comment '单位地址明细';

alter table T_APPLY_SPOUSE modify column MONTH_INCOME double(15,2)  comment '月收入';

alter table T_APPLY_SPOUSE modify column YEAR_INCOME double(15,2)  comment '年收入';

/*==============================================================*/
/* Index: Index_1                                               */
/*==============================================================*/
create unique index Index_1 on T_APPLY_SPOUSE
(
   APP_ID
);

/*==============================================================*/
/* Table: T_APPLY_TENANT                                        */
/*==============================================================*/
create table T_APPLY_TENANT
(
   ID                   varchar(64) not null,
   APP_ID               varchar(32) not null,
   NAME                 varchar(32),
   ID_TYPE              varchar(10),
   ID_NO                varchar(32),
   AGE                  int,
   SEX                  varchar(10),
   MOBILE               varchar(32),
   QQ                   varchar(32),
   WEIXIN               varchar(32),
   EDUCATION            varchar(10),
   MARRY_STATUS         varchar(10),
   ADDR_PROVINCE        varchar(64),
   ADDR_CITY            varchar(64),
   ADDR_COUNTY          varchar(64),
   ADDR_EXT             varchar(128),
   HOUSE_OWNER          varchar(10),
   HOUSE_MATE           varchar(128),
   HOUSE_HOLD           varchar(10),
   FAMILY_MEMBER        varchar(128),
   LIVE_TIME            varchar(10),
   UNIT_NAME            varchar(128),
   UNIT_TYPE            varchar(10),
   UNIT_INDUSTRY        varchar(10),
   UNIT_TEL             varchar(32),
   RANK                 varchar(10),
   RAN_NAME             varchar(32),
   UNIT_ADDR_PROVINCE   varchar(64),
   UNIT_ADDR_CITY       varchar(64),
   UNIT_ADDR_COUNTY     varchar(64),
   UNIT_ADDR_EXT        varchar(128),
   MONTY_INCOME         double(15,2),
   YEAR_INCOME          double(15,2),
   BIRTHDAY             date,
   NATION               varchar(10),
   DRIVER_LICENSE_DATE  date,
   DRIVER_LICENSE_TYPE  varchar(10),
   CHILD_COUNT          int,
   COMMENT              varchar(256),
   MOBILE2              varchar(32),
   primary key (ID)
);

alter table T_APPLY_TENANT comment '承租人信息';

alter table T_APPLY_TENANT modify column ID varchar(64)  comment '主键';

alter table T_APPLY_TENANT modify column APP_ID varchar(32)  comment '申请单号';

alter table T_APPLY_TENANT modify column NAME varchar(32)  comment '姓名';

alter table T_APPLY_TENANT modify column ID_TYPE varchar(10)  comment '证件类型';

alter table T_APPLY_TENANT modify column ID_NO varchar(32)  comment '证件号码';

alter table T_APPLY_TENANT modify column AGE int  comment '年龄';

alter table T_APPLY_TENANT modify column SEX varchar(10)  comment '性别';

alter table T_APPLY_TENANT modify column MOBILE varchar(32)  comment '手机号码';

alter table T_APPLY_TENANT modify column QQ varchar(32)  comment 'QQ号';

alter table T_APPLY_TENANT modify column WEIXIN varchar(32)  comment '微信号';

alter table T_APPLY_TENANT modify column EDUCATION varchar(10)  comment '学历';

alter table T_APPLY_TENANT modify column MARRY_STATUS varchar(10)  comment '婚姻状况';

alter table T_APPLY_TENANT modify column ADDR_PROVINCE varchar(64)  comment '现住址所属省';

alter table T_APPLY_TENANT modify column ADDR_CITY varchar(64)  comment '现住址所属市';

alter table T_APPLY_TENANT modify column ADDR_COUNTY varchar(64)  comment '现住址所属区';

alter table T_APPLY_TENANT modify column ADDR_EXT varchar(128)  comment '现住址明细';

alter table T_APPLY_TENANT modify column HOUSE_OWNER varchar(10)  comment '住所权属';

alter table T_APPLY_TENANT modify column HOUSE_MATE varchar(128)  comment '同住人';

alter table T_APPLY_TENANT modify column HOUSE_HOLD varchar(10)  comment '户籍所属';

alter table T_APPLY_TENANT modify column FAMILY_MEMBER varchar(128)  comment '家庭成员';

alter table T_APPLY_TENANT modify column LIVE_TIME varchar(10)  comment '来本地时长';

alter table T_APPLY_TENANT modify column UNIT_NAME varchar(128)  comment '单位名称';

alter table T_APPLY_TENANT modify column UNIT_TYPE varchar(10)  comment '单位类型';

alter table T_APPLY_TENANT modify column UNIT_INDUSTRY varchar(10)  comment '所属行业';

alter table T_APPLY_TENANT modify column UNIT_TEL varchar(32)  comment '单位电话';

alter table T_APPLY_TENANT modify column RANK varchar(10)  comment '职级';

alter table T_APPLY_TENANT modify column RAN_NAME varchar(32)  comment '职位名称';

alter table T_APPLY_TENANT modify column UNIT_ADDR_PROVINCE varchar(64)  comment '单位地址所属省';

alter table T_APPLY_TENANT modify column UNIT_ADDR_CITY varchar(64)  comment '单位地址所属市';

alter table T_APPLY_TENANT modify column UNIT_ADDR_COUNTY varchar(64)  comment '单位地址所属区';

alter table T_APPLY_TENANT modify column UNIT_ADDR_EXT varchar(128)  comment '单位地址明细';

alter table T_APPLY_TENANT modify column MONTY_INCOME double(15,2)  comment '月收入';

alter table T_APPLY_TENANT modify column YEAR_INCOME double(15,2)  comment '年收入';

alter table T_APPLY_TENANT modify column BIRTHDAY date  comment '生日';

alter table T_APPLY_TENANT modify column NATION varchar(10)  comment '民族';

alter table T_APPLY_TENANT modify column DRIVER_LICENSE_DATE date  comment '驾照发证日期';

alter table T_APPLY_TENANT modify column DRIVER_LICENSE_TYPE varchar(10)  comment '驾照类型';

alter table T_APPLY_TENANT modify column CHILD_COUNT int  comment '子女数量';

alter table T_APPLY_TENANT modify column COMMENT varchar(256)  comment '补充信息';

alter table T_APPLY_TENANT modify column MOBILE2 varchar(32)  comment '手机号码2';

/*==============================================================*/
/* Index: Index_1                                               */
/*==============================================================*/
create unique index Index_1 on T_APPLY_TENANT
(
   APP_ID
);

/*==============================================================*/
/* Table: T_APPLY_TENANT_CAR                                    */
/*==============================================================*/
create table T_APPLY_TENANT_CAR
(
   ID                   varchar(64) not null,
   APP_ID               varchar(32) not null,
   SEQ                  int not null,
   CAR_BRAND            varchar(64),
   CAR_DETAIL           varchar(64),
   CAR_STATUS           varchar(10),
   REGISTER_DATE        date,
   LOAN_AMOUNT          double(15,2),
   LOAN_BALANCE         double(15,2),
   MONTH_RENT           double(15,2),
   COMMENT              varchar(256),
   primary key (ID)
);

alter table T_APPLY_TENANT_CAR comment '承租人车产信息';

alter table T_APPLY_TENANT_CAR modify column ID varchar(64)  comment '主键';

alter table T_APPLY_TENANT_CAR modify column APP_ID varchar(32)  comment '申请单号';

alter table T_APPLY_TENANT_CAR modify column SEQ int  comment '序号';

alter table T_APPLY_TENANT_CAR modify column CAR_BRAND varchar(64)  comment '车产品牌';

alter table T_APPLY_TENANT_CAR modify column CAR_DETAIL varchar(64)  comment '车产详细';

alter table T_APPLY_TENANT_CAR modify column CAR_STATUS varchar(10)  comment '车产状态';

alter table T_APPLY_TENANT_CAR modify column REGISTER_DATE date  comment '新车上牌时间';

alter table T_APPLY_TENANT_CAR modify column LOAN_AMOUNT double(15,2)  comment '贷款金额';

alter table T_APPLY_TENANT_CAR modify column LOAN_BALANCE double(15,2)  comment '余额';

alter table T_APPLY_TENANT_CAR modify column MONTH_RENT double(15,2)  comment '月供';

alter table T_APPLY_TENANT_CAR modify column COMMENT varchar(256)  comment '补充说明';

/*==============================================================*/
/* Table: T_APPLY_TENANT_HOUSE                                  */
/*==============================================================*/
create table T_APPLY_TENANT_HOUSE
(
   ID                   varchar(64) not null,
   APP_ID               varchar(32) not null,
   SEQ                  int not null,
   ADDR_PROVINCE        varchar(64),
   ADDR_CITY            varchar(64),
   ADDR_COUNTY          varchar(64),
   ADDR_EXT             varchar(128),
   STATUS               varchar(10),
   AREA                 double(15,2),
   LOAN_AMOUNT          double(15,2),
   LOAN_BALANCE         double(15,2),
   MONTH_RENT           double(15,2),
   COMMENT              varchar(256),
   primary key (ID)
);

alter table T_APPLY_TENANT_HOUSE comment '承租人房产信息';

alter table T_APPLY_TENANT_HOUSE modify column ID varchar(64)  comment '主键';

alter table T_APPLY_TENANT_HOUSE modify column APP_ID varchar(32)  comment '申请编号';

alter table T_APPLY_TENANT_HOUSE modify column SEQ int  comment '序号';

alter table T_APPLY_TENANT_HOUSE modify column ADDR_PROVINCE varchar(64)  comment '房产地址所属省';

alter table T_APPLY_TENANT_HOUSE modify column ADDR_CITY varchar(64)  comment '房产地址所属市';

alter table T_APPLY_TENANT_HOUSE modify column ADDR_COUNTY varchar(64)  comment '房产地址所属区';

alter table T_APPLY_TENANT_HOUSE modify column ADDR_EXT varchar(128)  comment '房产地址明细';

alter table T_APPLY_TENANT_HOUSE modify column STATUS varchar(10)  comment '房产状态';

alter table T_APPLY_TENANT_HOUSE modify column AREA double(15,2)  comment '面积';

alter table T_APPLY_TENANT_HOUSE modify column LOAN_AMOUNT double(15,2)  comment '贷款金额';

alter table T_APPLY_TENANT_HOUSE modify column LOAN_BALANCE double(15,2)  comment '贷款余额';

alter table T_APPLY_TENANT_HOUSE modify column MONTH_RENT double(15,2)  comment '月供';

alter table T_APPLY_TENANT_HOUSE modify column COMMENT varchar(256)  comment '补充说明';

/*==============================================================*/
/* Table: T_CALLBACK_RESULT                                     */
/*==============================================================*/
create table T_CALLBACK_RESULT
(
   ID                   varchar(64) not null,
   RUN_PATH_ID          varchar(64) not null,
   TASK_BUSINESS_ID     varchar(64) not null,
   IS_SELF_LISTEN       bool not null,
   IS_SELF_SIGN         bool not null,
   CALLBACK_RESULT      varchar(10) not null,
   IS_SELF_AUTH         bool not null,
   IS_KNOW_FINANCE_CAR_INFO bool not null,
   IS_KNOW_FINANCE_AMT_INFO bool not null,
   COMMENT              varchar(256),
   primary key (ID)
);

alter table T_CALLBACK_RESULT comment '回访项目结果';

alter table T_CALLBACK_RESULT modify column ID varchar(64)  comment '主键';

alter table T_CALLBACK_RESULT modify column RUN_PATH_ID varchar(64)  comment '路径ID';

alter table T_CALLBACK_RESULT modify column TASK_BUSINESS_ID varchar(64)  comment '任务节点业务数据ID';

alter table T_CALLBACK_RESULT modify column IS_SELF_LISTEN bool  comment '是否本人接听';

alter table T_CALLBACK_RESULT modify column IS_SELF_SIGN bool  comment '是否本人签约成功';

alter table T_CALLBACK_RESULT modify column CALLBACK_RESULT varchar(10)  comment '回访结果';

alter table T_CALLBACK_RESULT modify column IS_SELF_AUTH bool  comment '是否本人签授权书';

alter table T_CALLBACK_RESULT modify column IS_KNOW_FINANCE_CAR_INFO bool  comment '是否熟知融资车辆信息';

alter table T_CALLBACK_RESULT modify column IS_KNOW_FINANCE_AMT_INFO bool  comment '是否熟知贷款金额、期限';

alter table T_CALLBACK_RESULT modify column COMMENT varchar(256)  comment '备注';

/*==============================================================*/
/* Table: T_CANCEL_APPLY_INFO                                   */
/*==============================================================*/
create table T_CANCEL_APPLY_INFO
(
   ID                   varchar(64) not null,
   RUN_PATH_ID          varchar(64) not null,
   TASK_BUSINESS_ID     varchar(64) not null,
   CANCEL_RESULT        varchar(10) not null,
   COMMENT              varchar(256) not null,
   primary key (ID)
);

alter table T_CANCEL_APPLY_INFO comment '取消申请信息';

alter table T_CANCEL_APPLY_INFO modify column ID varchar(64)  comment '主键';

alter table T_CANCEL_APPLY_INFO modify column RUN_PATH_ID varchar(64)  comment '路径ID';

alter table T_CANCEL_APPLY_INFO modify column TASK_BUSINESS_ID varchar(64)  comment '任务节点业务数据ID';

alter table T_CANCEL_APPLY_INFO modify column CANCEL_RESULT varchar(10)  comment '变更原因';

alter table T_CANCEL_APPLY_INFO modify column COMMENT varchar(256)  comment '变更备注';

/*==============================================================*/
/* Table: T_CHANGE_APPLY_INFO                                   */
/*==============================================================*/
create table T_CHANGE_APPLY_INFO
(
   ID                   varchar(64) not null,
   RUN_PATH_ID          varchar(64) not null,
   TASK_BUSINESS_ID     varchar(64) not null,
   CHANGE_RESULT        varchar(10) not null,
   COMMENT              varchar(256) not null,
   primary key (ID)
);

alter table T_CHANGE_APPLY_INFO comment '变更申请信息';

alter table T_CHANGE_APPLY_INFO modify column ID varchar(64)  comment '主键';

alter table T_CHANGE_APPLY_INFO modify column RUN_PATH_ID varchar(64)  comment '路径ID';

alter table T_CHANGE_APPLY_INFO modify column TASK_BUSINESS_ID varchar(64)  comment '任务节点业务数据ID';

alter table T_CHANGE_APPLY_INFO modify column CHANGE_RESULT varchar(10)  comment '变更原因';

alter table T_CHANGE_APPLY_INFO modify column COMMENT varchar(256)  comment '变更备注';

/*==============================================================*/
/* Table: T_CHECK_RESULT                                        */
/*==============================================================*/
create table T_CHECK_RESULT
(
   ID                   varchar(64) not null,
   APP_ID               varchar(32) not null,
   NET_CHECK_RESULT     varchar(32),
   NET_CHECK_NOT_PASS_REASON varchar(1024),
   NET_CHECK_COMMENT    varchar(1024),
   TEL_CHECK_RESULT     varchar(32),
   TEL_CHECK_NOT_PASS_REASON varchar(1024),
   TEL_CHECK_COMMENT    varchar(1024),
   primary key (ID)
);

alter table T_CHECK_RESULT comment '审核结果信息';

alter table T_CHECK_RESULT modify column ID varchar(64)  comment '主键';

alter table T_CHECK_RESULT modify column APP_ID varchar(32)  comment '申请单编号';

alter table T_CHECK_RESULT modify column NET_CHECK_RESULT varchar(32)  comment '网查结果';

alter table T_CHECK_RESULT modify column NET_CHECK_NOT_PASS_REASON varchar(1024)  comment '网查未通过原因';

alter table T_CHECK_RESULT modify column NET_CHECK_COMMENT varchar(1024)  comment '网查备注';

alter table T_CHECK_RESULT modify column TEL_CHECK_RESULT varchar(32)  comment '电审结果';

alter table T_CHECK_RESULT modify column TEL_CHECK_NOT_PASS_REASON varchar(1024)  comment '电审未通过原因';

alter table T_CHECK_RESULT modify column TEL_CHECK_COMMENT varchar(1024)  comment '电审备注';

/*==============================================================*/
/* Table: T_CUSTOM_INFO                                         */
/*==============================================================*/
create table T_CUSTOM_INFO
(
   ID                   varchar(64) not null,
   CUST_NO              varchar(32) not null,
   NAME                 varchar(32) not null,
   ID_TYPE              varchar(10) not null,
   ID_NO                varchar(32) not null,
   AGE                  int not null,
   SEX                  varchar(10) not null,
   MOBILE               varchar(32) not null,
   QQ                   varchar(32),
   WEIXIN               varchar(32),
   EDUCATION            varchar(10) not null,
   MARRY_STATUS         varchar(10) not null,
   ADDR_PROVINCE        varchar(64) not null,
   ADDR_CITY            varchar(64) not null,
   ADDR_COUNTY          varchar(64) not null,
   ADDR_EXT             varchar(128),
   HOUSE_OWNER          varchar(10) not null,
   HOUSE_MATE           varchar(128) not null,
   HOUSE_HOLD           varchar(10) not null,
   FAMILY_MEMBER        varchar(128) not null,
   LIVE_TIME            varchar(10) not null,
   UNIT_NAME            varchar(128),
   UNIT_TYPE            varchar(10),
   UNIT_INDUSTRY        varchar(10),
   UNIT_TEL             varchar(32),
   RANK                 varchar(10),
   RAN_NAME             varchar(32),
   UNIT_ADDR_PROVINCE   varchar(64),
   UNIT_ADDR_CITY       varchar(64),
   UNIT_ADDR_COUNTY     varchar(64),
   UNIT_ADDR_EXT        varchar(128),
   MONTY_INCOME         double(15,2),
   YEAR_INCOME          double(15,2),
   primary key (ID)
);

alter table T_CUSTOM_INFO comment '客户资料';

alter table T_CUSTOM_INFO modify column ID varchar(64)  comment '主键';

alter table T_CUSTOM_INFO modify column CUST_NO varchar(32)  comment '客户编号';

alter table T_CUSTOM_INFO modify column NAME varchar(32)  comment '姓名';

alter table T_CUSTOM_INFO modify column ID_TYPE varchar(10)  comment '证件类型';

alter table T_CUSTOM_INFO modify column ID_NO varchar(32)  comment '证件号码';

alter table T_CUSTOM_INFO modify column AGE int  comment '年龄';

alter table T_CUSTOM_INFO modify column SEX varchar(10)  comment '性别';

alter table T_CUSTOM_INFO modify column MOBILE varchar(32)  comment '手机号码';

alter table T_CUSTOM_INFO modify column QQ varchar(32)  comment 'QQ号';

alter table T_CUSTOM_INFO modify column WEIXIN varchar(32)  comment '微信号';

alter table T_CUSTOM_INFO modify column EDUCATION varchar(10)  comment '学历';

alter table T_CUSTOM_INFO modify column MARRY_STATUS varchar(10)  comment '婚姻状况';

alter table T_CUSTOM_INFO modify column ADDR_PROVINCE varchar(64)  comment '现住址所属省';

alter table T_CUSTOM_INFO modify column ADDR_CITY varchar(64)  comment '现住址所属市';

alter table T_CUSTOM_INFO modify column ADDR_COUNTY varchar(64)  comment '现住址所属区';

alter table T_CUSTOM_INFO modify column ADDR_EXT varchar(128)  comment '现住址明细';

alter table T_CUSTOM_INFO modify column HOUSE_OWNER varchar(10)  comment '住所权属';

alter table T_CUSTOM_INFO modify column HOUSE_MATE varchar(128)  comment '同住人';

alter table T_CUSTOM_INFO modify column HOUSE_HOLD varchar(10)  comment '户籍所属';

alter table T_CUSTOM_INFO modify column FAMILY_MEMBER varchar(128)  comment '家庭成员';

alter table T_CUSTOM_INFO modify column LIVE_TIME varchar(10)  comment '来本地时长';

alter table T_CUSTOM_INFO modify column UNIT_NAME varchar(128)  comment '单位名称';

alter table T_CUSTOM_INFO modify column UNIT_TYPE varchar(10)  comment '单位类型';

alter table T_CUSTOM_INFO modify column UNIT_INDUSTRY varchar(10)  comment '所属行业';

alter table T_CUSTOM_INFO modify column UNIT_TEL varchar(32)  comment '单位电话';

alter table T_CUSTOM_INFO modify column RANK varchar(10)  comment '职级';

alter table T_CUSTOM_INFO modify column RAN_NAME varchar(32)  comment '职位名称';

alter table T_CUSTOM_INFO modify column UNIT_ADDR_PROVINCE varchar(64)  comment '单位地址所属省';

alter table T_CUSTOM_INFO modify column UNIT_ADDR_CITY varchar(64)  comment '单位地址所属市';

alter table T_CUSTOM_INFO modify column UNIT_ADDR_COUNTY varchar(64)  comment '单位地址所属区';

alter table T_CUSTOM_INFO modify column UNIT_ADDR_EXT varchar(128)  comment '单位地址明细';

alter table T_CUSTOM_INFO modify column MONTY_INCOME double(15,2)  comment '月收入';

alter table T_CUSTOM_INFO modify column YEAR_INCOME double(15,2)  comment '年收入';

/*==============================================================*/
/* Index: Index_1                                               */
/*==============================================================*/
create unique index Index_1 on T_CUSTOM_INFO
(
   CUST_NO
);

/*==============================================================*/
/* Table: T_HIS_BEANMAP                                         */
/*==============================================================*/
create table T_HIS_BEANMAP
(
   ID                   varchar(64) not null,
   CLASS_NAME           varchar(64),
   CLASS_CNAME          varchar(64),
   TABLE_NAME           varchar(64),
   TABLE_CNAME          varchar(64),
   primary key (ID)
)
DEFAULT CHARACTER SET = utf8
DEFAULT COLLATE = utf8_general_ci;

alter table T_HIS_BEANMAP comment '对象映射表';

alter table T_HIS_BEANMAP modify column ID varchar(64)  comment '主键';

alter table T_HIS_BEANMAP modify column CLASS_NAME varchar(64)  comment '类名';

alter table T_HIS_BEANMAP modify column CLASS_CNAME varchar(64)  comment '类名（中文）';

alter table T_HIS_BEANMAP modify column TABLE_NAME varchar(64)  comment '表名';

alter table T_HIS_BEANMAP modify column TABLE_CNAME varchar(64)  comment '表名中文';

/*==============================================================*/
/* Table: T_HIS_FIELDCOMMENT                                    */
/*==============================================================*/
create table T_HIS_FIELDCOMMENT
(
   ID                   varchar(64) not null,
   TABLE_NAME           varchar(64),
   TABLE_CNAME          varchar(32),
   FIELD_NAME           varchar(32),
   FIELD_CNAME          varchar(32),
   primary key (ID)
)
DEFAULT CHARACTER SET = utf8
DEFAULT COLLATE = utf8_general_ci;

alter table T_HIS_FIELDCOMMENT comment '数据表字段注释表';

alter table T_HIS_FIELDCOMMENT modify column ID varchar(64)  comment '主键';

alter table T_HIS_FIELDCOMMENT modify column TABLE_NAME varchar(64)  comment '表名';

alter table T_HIS_FIELDCOMMENT modify column TABLE_CNAME varchar(32)  comment '表名（中文）';

alter table T_HIS_FIELDCOMMENT modify column FIELD_NAME varchar(32)  comment '字段名';

alter table T_HIS_FIELDCOMMENT modify column FIELD_CNAME varchar(32)  comment '字段名（中文）';

/*==============================================================*/
/* Table: T_HIS_OPER                                            */
/*==============================================================*/
create table T_HIS_OPER
(
   ID                   varchar(64) not null,
   RECORD_ID            varchar(64),
   APP_ID               varchar(32),
   TABLE_NAME           varchar(32),
   FIELD_NAME           varchar(32),
   CLASS_NAME           varchar(64),
   UPD_MODE             varchar(32),
   PRE_VALUE            varchar(200),
   AFTER_VALUE          varchar(200),
   OPER_TIME            datetime,
   ACCOUN_ID            varchar(64),
   primary key (ID)
)
DEFAULT CHARACTER SET = utf8
DEFAULT COLLATE = utf8_general_ci;

alter table T_HIS_OPER comment '历史操作记录表';

alter table T_HIS_OPER modify column ID varchar(64)  comment '主键';

alter table T_HIS_OPER modify column RECORD_ID varchar(64)  comment '原始记录主键';

alter table T_HIS_OPER modify column APP_ID varchar(32)  comment '申请单号';

alter table T_HIS_OPER modify column TABLE_NAME varchar(32)  comment '表名';

alter table T_HIS_OPER modify column FIELD_NAME varchar(32)  comment '字段名';

alter table T_HIS_OPER modify column CLASS_NAME varchar(64)  comment '类名';

alter table T_HIS_OPER modify column UPD_MODE varchar(32)  comment '更新模式';

alter table T_HIS_OPER modify column PRE_VALUE varchar(200)  comment '操作前值';

alter table T_HIS_OPER modify column AFTER_VALUE varchar(200)  comment '操作后值';

alter table T_HIS_OPER modify column OPER_TIME datetime  comment '操作时间';

alter table T_HIS_OPER modify column ACCOUN_ID varchar(64)  comment '操作人';

/*==============================================================*/
/* Table: T_REJECT_RECOMMIT                                     */
/*==============================================================*/
create table T_REJECT_RECOMMIT
(
   ID                   varchar(64) not null,
   APP_ID               varchar(64) not null,
   REJECT_REASON        varchar(1024),
   RECOMMIT_REASON      varchar(1024),
   RECOMMIT_COMMENT     varchar(1024),
   primary key (ID)
);

alter table T_REJECT_RECOMMIT comment '拒绝申请复议表';

alter table T_REJECT_RECOMMIT modify column ID varchar(64)  comment '主键';

alter table T_REJECT_RECOMMIT modify column APP_ID varchar(64)  comment '申请单编号';

alter table T_REJECT_RECOMMIT modify column REJECT_REASON varchar(1024)  comment '拒绝原因';

alter table T_REJECT_RECOMMIT modify column RECOMMIT_REASON varchar(1024)  comment '复议原因';

alter table T_REJECT_RECOMMIT modify column RECOMMIT_COMMENT varchar(1024)  comment '复议备注';

/*==============================================================*/
/* Table: T_TASK_PROCESS_RESULT                                 */
/*==============================================================*/
create table T_TASK_PROCESS_RESULT
(
   ID                   varchar(64) not null,
   RUN_PATH_ID          varchar(64) not null,
   PROCESS_RESULT       varchar(64) not null,
   PROCESS_RESULT_DESC  varchar(1024),
   COMMENT              varchar(1024),
   TASK_BUSINESS_ID     varchar(64),
   primary key (ID)
);

alter table T_TASK_PROCESS_RESULT comment '流程处理结果';

alter table T_TASK_PROCESS_RESULT modify column ID varchar(64)  comment '主键';

alter table T_TASK_PROCESS_RESULT modify column RUN_PATH_ID varchar(64)  comment '路径ID';

alter table T_TASK_PROCESS_RESULT modify column PROCESS_RESULT varchar(64)  comment '处理结果';

alter table T_TASK_PROCESS_RESULT modify column PROCESS_RESULT_DESC varchar(1024)  comment '处理结果描述';

alter table T_TASK_PROCESS_RESULT modify column COMMENT varchar(1024)  comment '备注';

alter table T_TASK_PROCESS_RESULT modify column TASK_BUSINESS_ID varchar(64)  comment '任务节点业务数据ID';

