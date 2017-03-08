
create table T_APPLY_ALTER_REPAYDATE
(
   ID                   varchar(64) not null,
   APP_ID               varchar(32) not null,
   OLD_CLOSING_DATE     date not null,
   NEW_CLOSING_DATE     date not null,
   ALTER_DAY            int not null,
   ALTER_INTEREST       double(15,2) not null,
   APPLY_COMMENT        varchar(256),
   APPLY_STATUS         varchar(10) not null,
   CREATE_ID            varchar(64) not null,
   CREATE_DATE          date not null,
   PROC_INST_ID         varchar(64) not null,
   primary key (ID)
);

alter table T_APPLY_ALTER_REPAYDATE comment '申请变更还款日';

alter table T_APPLY_ALTER_REPAYDATE modify column ID varchar(64)  comment 'ID';

alter table T_APPLY_ALTER_REPAYDATE modify column APP_ID varchar(32)  comment '关联申请单号';

alter table T_APPLY_ALTER_REPAYDATE modify column OLD_CLOSING_DATE date  comment '原结账日';

alter table T_APPLY_ALTER_REPAYDATE modify column NEW_CLOSING_DATE date  comment '新结账日';

alter table T_APPLY_ALTER_REPAYDATE modify column ALTER_DAY int  comment '变更天数';

alter table T_APPLY_ALTER_REPAYDATE modify column ALTER_INTEREST double(15,2)  comment '变更利息';

alter table T_APPLY_ALTER_REPAYDATE modify column APPLY_COMMENT varchar(256)  comment '申请备注';

alter table T_APPLY_ALTER_REPAYDATE modify column APPLY_STATUS varchar(10)  comment '申请状态';

alter table T_APPLY_ALTER_REPAYDATE modify column CREATE_ID varchar(64)  comment '创建人';

alter table T_APPLY_ALTER_REPAYDATE modify column CREATE_DATE date  comment '创建日期';

alter table T_APPLY_ALTER_REPAYDATE modify column PROC_INST_ID varchar(64)  comment '流程实例ID';

/*==============================================================*/
/* Table: T_APPLY_EXTEND_PERIOD                                 */
/*==============================================================*/
create table T_APPLY_EXTEND_PERIOD
(
   ID                   varchar(64) not null,
   APP_ID               varchar(64) not null,
   REPAY_CAPITAL        double(15,2) not null,
   REPAY_INTEREST       double(15,2) not null,
   REPAY_OVERDUE_AMOUNT double(15,2) not null,
   OTHER_FEE            double(15,2) not null,
   OTHER_OVERDUE_AMOUNT double(15,2) not null,
   EXTEND_FEE           double(15,2) not null,
   NEW_OLD_INTEREST     double(15,2) not null,
   OLD_REPAY_MODE       varchar(10),
   NEW_REPAY_MODE       varchar(10),
   REMAIN_CAPITAL       double(15,2) not null,
   NEW_CAPITAL          double(15,2),
   APPLY_COMMENT        varchar(256),
   APPLY_STATUS         varchar(10) not null,
   APPLY_END_DATE       date not null,
   CREATE_ID            varchar(64) not null,
   CREATE_DATE          date not null,
   PROC_INST_ID         varchar(64) not null,
   primary key (ID)
);

alter table T_APPLY_EXTEND_PERIOD comment '申请展期表';

alter table T_APPLY_EXTEND_PERIOD modify column ID varchar(64)  comment 'ID';

alter table T_APPLY_EXTEND_PERIOD modify column APP_ID varchar(64)  comment '关联申请单号';

alter table T_APPLY_EXTEND_PERIOD modify column REPAY_CAPITAL double(15,2)  comment '应还本金';

alter table T_APPLY_EXTEND_PERIOD modify column REPAY_INTEREST double(15,2)  comment '应还利息';

alter table T_APPLY_EXTEND_PERIOD modify column REPAY_OVERDUE_AMOUNT double(15,2)  comment '应还罚息';

alter table T_APPLY_EXTEND_PERIOD modify column OTHER_FEE double(15,2)  comment '其他费用';

alter table T_APPLY_EXTEND_PERIOD modify column OTHER_OVERDUE_AMOUNT double(15,2)  comment '其他费用罚息';

alter table T_APPLY_EXTEND_PERIOD modify column EXTEND_FEE double(15,2)  comment '展期费用';

alter table T_APPLY_EXTEND_PERIOD modify column NEW_OLD_INTEREST double(15,2)  comment '新旧还款日利息';

alter table T_APPLY_EXTEND_PERIOD modify column OLD_REPAY_MODE varchar(10)  comment '原还款方式';

alter table T_APPLY_EXTEND_PERIOD modify column NEW_REPAY_MODE varchar(10)  comment '新还款方式';

alter table T_APPLY_EXTEND_PERIOD modify column REMAIN_CAPITAL double(15,2)  comment '剩余本金';

alter table T_APPLY_EXTEND_PERIOD modify column NEW_CAPITAL double(15,2)  comment '新计息本金';

alter table T_APPLY_EXTEND_PERIOD modify column APPLY_COMMENT varchar(256)  comment '申请备注';

alter table T_APPLY_EXTEND_PERIOD modify column APPLY_STATUS varchar(10)  comment '申请状态';

alter table T_APPLY_EXTEND_PERIOD modify column APPLY_END_DATE date  comment '申请有效截止日期';

alter table T_APPLY_EXTEND_PERIOD modify column CREATE_ID varchar(64)  comment '创建人';

alter table T_APPLY_EXTEND_PERIOD modify column CREATE_DATE date  comment '创建日期';

alter table T_APPLY_EXTEND_PERIOD modify column PROC_INST_ID varchar(64)  comment '流程实例ID';

/*==============================================================*/
/* Table: T_APPLY_PUBLIC_REPAY                                  */
/*==============================================================*/
create table T_APPLY_PUBLIC_REPAY
(
   ID                   varchar(64) not null,
   APP_ID               varchar(64) not null,
   CHARGE_AMOUNT        double(15,2) not null,
   CHARGE_DATE          double(15,2) not null,
   APPLY_STATUS         varchar(10) not null,
   APPLY_COMMENT        varchar(128),
   CHARGE_RESULT        varchar(64) not null,
   CREATE_ID            varchar(64) not null,
   CRATE_DATE           date not null,
   PROC_INST_ID         varchar(64) not null,
   primary key (ID)
);

alter table T_APPLY_PUBLIC_REPAY comment '申请对公还款';

alter table T_APPLY_PUBLIC_REPAY modify column ID varchar(64)  comment 'ID';

alter table T_APPLY_PUBLIC_REPAY modify column APP_ID varchar(64)  comment '关联申请单号';

alter table T_APPLY_PUBLIC_REPAY modify column CHARGE_AMOUNT double(15,2)  comment '还款金额';

alter table T_APPLY_PUBLIC_REPAY modify column CHARGE_DATE double(15,2)  comment '还款日期';

alter table T_APPLY_PUBLIC_REPAY modify column APPLY_STATUS varchar(10)  comment '申请状态';

alter table T_APPLY_PUBLIC_REPAY modify column APPLY_COMMENT varchar(128)  comment '申请备注';

alter table T_APPLY_PUBLIC_REPAY modify column CHARGE_RESULT varchar(64)  comment '还款结果';

alter table T_APPLY_PUBLIC_REPAY modify column CREATE_ID varchar(64)  comment '创建人';

alter table T_APPLY_PUBLIC_REPAY modify column CRATE_DATE date  comment '创建日期';

alter table T_APPLY_PUBLIC_REPAY modify column PROC_INST_ID varchar(64)  comment '流程实例ID';

/*==============================================================*/
/* Table: T_APPLY_REFUND                                        */
/*==============================================================*/
create table T_APPLY_REFUND
(
   ID                   varchar(64) not null,
   APP_ID               varchar(32) not null,
   REFUND_AMOUNT        double(15,2) not null,
   REFUND_DATE          date not null,
   APPLY_COMMENT        varchar(256),
   APPLY_STATUS         varchar(10) not null,
   CREATE_ID            varchar(64) not null,
   CREATE_DATE          date not null,
   primary key (ID)
);

alter table T_APPLY_REFUND comment '申请退款表';

alter table T_APPLY_REFUND modify column ID varchar(64)  comment 'ID';

alter table T_APPLY_REFUND modify column APP_ID varchar(32)  comment '关联申请单号';

alter table T_APPLY_REFUND modify column REFUND_AMOUNT double(15,2)  comment '退款金额';

alter table T_APPLY_REFUND modify column REFUND_DATE date  comment '退款日期';

alter table T_APPLY_REFUND modify column APPLY_COMMENT varchar(256)  comment '申请备注';

alter table T_APPLY_REFUND modify column APPLY_STATUS varchar(10)  comment '申请状态';

alter table T_APPLY_REFUND modify column CREATE_ID varchar(64)  comment '创建人';

alter table T_APPLY_REFUND modify column CREATE_DATE date  comment '创建日期';

/*==============================================================*/
/* Table: T_APPLY_REMISSION                                     */
/*==============================================================*/
create table T_APPLY_REMISSION
(
   ID                   varchar(64) not null,
   APP_ID               varchar(32) not null,
   REMISSION_DATE       date not null,
   APPLY_COMMENT        varchar(256),
   APPLY_STATUS         varchar(10) not null,
   CREATE_ID            varchar(64) not null,
   CREATE_DATE          date not null,
   primary key (ID)
);

alter table T_APPLY_REMISSION comment '申请减免表';

alter table T_APPLY_REMISSION modify column ID varchar(64)  comment 'ID';

alter table T_APPLY_REMISSION modify column APP_ID varchar(32)  comment '关联申请单号';

alter table T_APPLY_REMISSION modify column REMISSION_DATE date  comment '减免时间';

alter table T_APPLY_REMISSION modify column APPLY_COMMENT varchar(256)  comment '申请备注';

alter table T_APPLY_REMISSION modify column APPLY_STATUS varchar(10)  comment '申请状态';

alter table T_APPLY_REMISSION modify column CREATE_ID varchar(64)  comment '创建人';

alter table T_APPLY_REMISSION modify column CREATE_DATE date  comment '创建日期';

/*==============================================================*/
/* Table: T_APPLY_SETTLE                                        */
/*==============================================================*/
create table T_APPLY_SETTLE
(
   ID                   varchar(64) not null,
   APP_ID               varchar(64) not null,
   SETTLE_TYPE          varchar(10) not null,
   REPAY_CAPITAL        double(15,2) not null,
   REPAY_INTEREST       double(15,2) not null,
   REPAY_OVERDUE_AMOUNT double(15,2) not null,
   OTHER_FEE            double(15,2) not null,
   OTHER_OVERDUE_AMOUNT double(15,2) not null,
   LATE_FEE             double(15,2) not null,
   SETTLE_CAPITAL       double(15,2) not null,
   SETTLE_TOTAL_AMOUNT  double(15,2) not null,
   SETTLE_AFTER_CAPITAL double(15,2) not null,
   SETTLE_START_PERIOD  int not null,
   SETTLE_END_PERIOD    int not null,
   APPLY_COMMENT        varchar(256),
   APPLY_STATUS         varchar(10) not null,
   APPLY_END_DATE       date not null,
   CREATE_ID            varchar(64) not null,
   CREATE_DATE          date not null,
   PROC_INST_ID         varchar(64) not null,
   primary key (ID)
);

alter table T_APPLY_SETTLE comment '申请提前结清表';

alter table T_APPLY_SETTLE modify column ID varchar(64)  comment 'ID';

alter table T_APPLY_SETTLE modify column APP_ID varchar(64)  comment '关联申请单号';

alter table T_APPLY_SETTLE modify column SETTLE_TYPE varchar(10)  comment '提前结清类型';

alter table T_APPLY_SETTLE modify column REPAY_CAPITAL double(15,2)  comment '应还本金';

alter table T_APPLY_SETTLE modify column REPAY_INTEREST double(15,2)  comment '应还利息';

alter table T_APPLY_SETTLE modify column REPAY_OVERDUE_AMOUNT double(15,2)  comment '应还罚息';

alter table T_APPLY_SETTLE modify column OTHER_FEE double(15,2)  comment '其他费用';

alter table T_APPLY_SETTLE modify column OTHER_OVERDUE_AMOUNT double(15,2)  comment '其他费用罚息';

alter table T_APPLY_SETTLE modify column LATE_FEE double(15,2)  comment '违约金';

alter table T_APPLY_SETTLE modify column SETTLE_CAPITAL double(15,2)  comment '结清本金';

alter table T_APPLY_SETTLE modify column SETTLE_TOTAL_AMOUNT double(15,2)  comment '结清金额';

alter table T_APPLY_SETTLE modify column SETTLE_AFTER_CAPITAL double(15,2)  comment '结清后剩余本金';

alter table T_APPLY_SETTLE modify column SETTLE_START_PERIOD int  comment '结清开始期数';

alter table T_APPLY_SETTLE modify column SETTLE_END_PERIOD int  comment '结清截止期数';

alter table T_APPLY_SETTLE modify column APPLY_COMMENT varchar(256)  comment '申请备注';

alter table T_APPLY_SETTLE modify column APPLY_STATUS varchar(10)  comment '申请状态';

alter table T_APPLY_SETTLE modify column APPLY_END_DATE date  comment '申请有效截止日期';

alter table T_APPLY_SETTLE modify column CREATE_ID varchar(64)  comment '创建人';

alter table T_APPLY_SETTLE modify column CREATE_DATE date  comment '创建日期';

alter table T_APPLY_SETTLE modify column PROC_INST_ID varchar(64)  comment '流程实例ID';

/*==============================================================*/
/* Table: T_CHARGE_LOG                                          */
/*==============================================================*/
create table T_CHARGE_LOG
(
   ID                   varchar(64) not null,
   APP_ID               varchar(64) not null,
   OFFER_ID             varchar(64) not null,
   CHARGE_MODE          varchar(10) not null,
   MERCHANT_NO          varchar(32) not null,
   ORDER_NO             varchar(64) not null,
   OPEN_BANK_NO         varchar(32) not null,
   CUST_NAME            varchar(32) not null,
   CARD_NO              varchar(32) not null,
   ID_TYPE              varchar(10) not null,
   ID_NO                varchar(32) not null,
   CHARGE_AMOUNT        double(15,2) not null,
   CHARGE_TIME          timestamp not null,
   CHARGE_RESULT        varchar(64) not null,
   THIRD_PARTY_RESULT   varchar(64),
   THIRD_PARTY_TIME     timestamp,
   THIRD_PARTY_RESULT_DESC varchar(128),
   primary key (ID)
);

alter table T_CHARGE_LOG comment '扣款记录表';

alter table T_CHARGE_LOG modify column ID varchar(64)  comment 'ID';

alter table T_CHARGE_LOG modify column APP_ID varchar(64)  comment '关联申请单号';

alter table T_CHARGE_LOG modify column OFFER_ID varchar(64)  comment '报盘ID';

alter table T_CHARGE_LOG modify column CHARGE_MODE varchar(10)  comment '扣款方式';

alter table T_CHARGE_LOG modify column MERCHANT_NO varchar(32)  comment '商户号';

alter table T_CHARGE_LOG modify column ORDER_NO varchar(64)  comment '订单号';

alter table T_CHARGE_LOG modify column OPEN_BANK_NO varchar(32)  comment '客户开户行号';

alter table T_CHARGE_LOG modify column CUST_NAME varchar(32)  comment '客户姓名';

alter table T_CHARGE_LOG modify column CARD_NO varchar(32)  comment '客户卡号';

alter table T_CHARGE_LOG modify column ID_TYPE varchar(10)  comment '证件类型';

alter table T_CHARGE_LOG modify column ID_NO varchar(32)  comment '证件号码';

alter table T_CHARGE_LOG modify column CHARGE_AMOUNT double(15,2)  comment '扣款金额';

alter table T_CHARGE_LOG modify column CHARGE_TIME timestamp  comment '扣款时间';

alter table T_CHARGE_LOG modify column CHARGE_RESULT varchar(64)  comment '扣款结果';

alter table T_CHARGE_LOG modify column THIRD_PARTY_RESULT varchar(64)  comment '第三方返回结果';

alter table T_CHARGE_LOG modify column THIRD_PARTY_TIME timestamp  comment '第三方返回时间';

alter table T_CHARGE_LOG modify column THIRD_PARTY_RESULT_DESC varchar(128)  comment '第三方返回结果描述';

/*==============================================================*/
/* Table: T_GENERAL_LEDGER                                      */
/*==============================================================*/
create table T_GENERAL_LEDGER
(
   ID                   varchar(64) not null,
   APP_ID               varchar(32) not null,
   PRODUCT_NAME         varchar(32) not null,
   PRODUCT_CODE         varchar(10) not null,
   PERIOD               int not null,
   FINANCE_AMT          double(15,2) not null,
   YEAR_RATE            double(15,4) not null,
   DAY_LATE_RATE        double(15,4) not null,
   DAY_EXTEND_RATE      double(15,4) not null,
   REPAY_MODE           varchar(10) not null,
   LOAN_DATE            date not null,
   REMAIN_CAPITAL       double(15,2) not null,
   REPAY_STATUS         varchar(10) not null,
   SETTLE_DATE          date,
   PROCESS_STATUS       varchar(10) not null,
   RESERVER1            varchar(100),
   RESERVER2            varchar(100),
   RESERVER3            date,
   RESERVER4            date,
   RESERVER5            double(15,2),
   RESERVER6            double(15,2),
   RESERVER7            varchar(100),
   RESERVER8            varchar(100),
   RESERVER9            varchar(100),
   RESERVER10           varchar(100),
   primary key (ID)
);

alter table T_GENERAL_LEDGER comment '总账表';

alter table T_GENERAL_LEDGER modify column ID varchar(64)  comment 'ID';

alter table T_GENERAL_LEDGER modify column APP_ID varchar(32)  comment '关联申请单号';

alter table T_GENERAL_LEDGER modify column PRODUCT_NAME varchar(32)  comment '产品名称';

alter table T_GENERAL_LEDGER modify column PRODUCT_CODE varchar(10)  comment '产品编码';

alter table T_GENERAL_LEDGER modify column PERIOD int  comment '融资期限';

alter table T_GENERAL_LEDGER modify column FINANCE_AMT double(15,2)  comment '融资金额';

alter table T_GENERAL_LEDGER modify column YEAR_RATE double(15,4)  comment '年利率';

alter table T_GENERAL_LEDGER modify column DAY_LATE_RATE double(15,4)  comment '日罚息率';

alter table T_GENERAL_LEDGER modify column DAY_EXTEND_RATE double(15,4)  comment '日展期率';

alter table T_GENERAL_LEDGER modify column REPAY_MODE varchar(10)  comment '还款方式';

alter table T_GENERAL_LEDGER modify column LOAN_DATE date  comment '放款日期';

alter table T_GENERAL_LEDGER modify column REMAIN_CAPITAL double(15,2)  comment '剩余本金';

alter table T_GENERAL_LEDGER modify column REPAY_STATUS varchar(10)  comment '还款状态';

alter table T_GENERAL_LEDGER modify column SETTLE_DATE date  comment '结清日期';

alter table T_GENERAL_LEDGER modify column PROCESS_STATUS varchar(10)  comment '当前处理状态';

alter table T_GENERAL_LEDGER modify column RESERVER1 varchar(100)  comment '保留字段1';

alter table T_GENERAL_LEDGER modify column RESERVER2 varchar(100)  comment '保留字段2';

alter table T_GENERAL_LEDGER modify column RESERVER3 date  comment '保留字段3';

alter table T_GENERAL_LEDGER modify column RESERVER4 date  comment '保留字段4';

alter table T_GENERAL_LEDGER modify column RESERVER5 double(15,2)  comment '保留字段5';

alter table T_GENERAL_LEDGER modify column RESERVER6 double(15,2)  comment '保留字段6';

alter table T_GENERAL_LEDGER modify column RESERVER7 varchar(100)  comment '保留字段7';

alter table T_GENERAL_LEDGER modify column RESERVER8 varchar(100)  comment '保留字段8';

alter table T_GENERAL_LEDGER modify column RESERVER9 varchar(100)  comment '保留字段9';

alter table T_GENERAL_LEDGER modify column RESERVER10 varchar(100)  comment '保留字段10';

/*==============================================================*/
/* Index: Index_1                                               */
/*==============================================================*/
create unique index Index_1 on T_GENERAL_LEDGER
(
   APP_ID
);

/*==============================================================*/
/* Table: T_MANUAL_OFFER                                        */
/*==============================================================*/
create table T_MANUAL_OFFER
(
   ID                   varchar(64) not null,
   APP_ID               varchar(32) not null,
   OFFER_AMOUNT         double(15,2) not null,
   APPLY_COMMENT        varchar(256),
   APPLY_STATUS         varchar(10) not null,
   OFFER_ID             varchar(64) not null,
   CHAGE_RESULT         varchar(64),
   CREATE_ID            varchar(64) not null,
   CRATE_DATE           date not null,
   PROC_INST_ID         varchar(64) not null,
   primary key (ID)
);

alter table T_MANUAL_OFFER comment '人工报盘申请';

alter table T_MANUAL_OFFER modify column ID varchar(64)  comment 'ID';

alter table T_MANUAL_OFFER modify column APP_ID varchar(32)  comment '关联申请单号';

alter table T_MANUAL_OFFER modify column OFFER_AMOUNT double(15,2)  comment '报盘金额';

alter table T_MANUAL_OFFER modify column APPLY_COMMENT varchar(256)  comment '申请备注';

alter table T_MANUAL_OFFER modify column APPLY_STATUS varchar(10)  comment '申请状态';

alter table T_MANUAL_OFFER modify column OFFER_ID varchar(64)  comment '报盘任务ID';

alter table T_MANUAL_OFFER modify column CHAGE_RESULT varchar(64)  comment '扣款结果';

alter table T_MANUAL_OFFER modify column CREATE_ID varchar(64)  comment '创建人';

alter table T_MANUAL_OFFER modify column CRATE_DATE date  comment '创建日期';

alter table T_MANUAL_OFFER modify column PROC_INST_ID varchar(64)  comment '流程实例ID';

/*==============================================================*/
/* Table: T_OFFER_DETAIL                                        */
/*==============================================================*/
create table T_OFFER_DETAIL
(
   ID                   varchar(64) not null,
   OFFER_ID             varchar(64) not null,
   FEE_TYPE             varchar(10) not null,
   FEE_REF_ID           varchar(64) not null,
   CHARGE_CAPITAL       double(15,2) not null,
   CHARGE_INTEREST      double(15,2) not null,
   CHARGE_OVERDUE_AMOUNT double(15,2) not null,
   primary key (ID)
);

alter table T_OFFER_DETAIL comment '报盘明细表';

alter table T_OFFER_DETAIL modify column ID varchar(64)  comment 'ID';

alter table T_OFFER_DETAIL modify column OFFER_ID varchar(64)  comment '报盘汇总ID';

alter table T_OFFER_DETAIL modify column FEE_TYPE varchar(10)  comment '费用类型';

alter table T_OFFER_DETAIL modify column FEE_REF_ID varchar(64)  comment '费用关联ID';

alter table T_OFFER_DETAIL modify column CHARGE_CAPITAL double(15,2)  comment '扣款本金';

alter table T_OFFER_DETAIL modify column CHARGE_INTEREST double(15,2)  comment '扣款利息';

alter table T_OFFER_DETAIL modify column CHARGE_OVERDUE_AMOUNT double(15,2)  comment '扣款罚息';

/*==============================================================*/
/* Table: T_OFFER_SUMMARY                                       */
/*==============================================================*/
create table T_OFFER_SUMMARY
(
   ID                   varchar(64) not null,
   APP_ID               varchar(32) not null,
   OFFER_SOURCE         varchar(10) not null,
   CHARGE_MODE          varchar(10),
   MERCHANT_NO          varchar(32),
   OFFER_AMOUNT         double(15,2) not null,
   OFFER_TIME           timestamp not null,
   CHARGE_STATUS        varchar(10),
   RESERVER1            varchar(100),
   RESERVER2            varchar(100),
   RESERVER3            date,
   RESERVER4            date,
   RESERVER5            double(15,2),
   RESERVER6            double(15,2),
   primary key (ID)
);

alter table T_OFFER_SUMMARY comment '报盘汇总表';

alter table T_OFFER_SUMMARY modify column ID varchar(64)  comment 'ID';

alter table T_OFFER_SUMMARY modify column APP_ID varchar(32)  comment '关联申请单号';

alter table T_OFFER_SUMMARY modify column OFFER_SOURCE varchar(10)  comment '报盘来源';

alter table T_OFFER_SUMMARY modify column CHARGE_MODE varchar(10)  comment '扣款方式';

alter table T_OFFER_SUMMARY modify column MERCHANT_NO varchar(32)  comment '商户号';

alter table T_OFFER_SUMMARY modify column OFFER_AMOUNT double(15,2)  comment '报盘金额';

alter table T_OFFER_SUMMARY modify column OFFER_TIME timestamp  comment '报盘时间';

alter table T_OFFER_SUMMARY modify column CHARGE_STATUS varchar(10)  comment '扣款状态';

alter table T_OFFER_SUMMARY modify column RESERVER1 varchar(100)  comment '保留字段1';

alter table T_OFFER_SUMMARY modify column RESERVER2 varchar(100)  comment '保留字段2';

alter table T_OFFER_SUMMARY modify column RESERVER3 date  comment '保留字段3';

alter table T_OFFER_SUMMARY modify column RESERVER4 date  comment '保留字段4';

alter table T_OFFER_SUMMARY modify column RESERVER5 double(15,2)  comment '保留字段5';

alter table T_OFFER_SUMMARY modify column RESERVER6 double(15,2)  comment '保留字段6';

/*==============================================================*/
/* Table: T_OTHER_FEE                                           */
/*==============================================================*/
create table T_OTHER_FEE
(
   ID                   varchar(64) not null,
   APP_ID               varchar(32) not null,
   FEE_TOTAL_AMOUNT     double(15,2) not null,
   VALUE_DATE           date,
   CLOSING_DATE         date,
   ADDUP_OVERDUE_DAY    int,
   ADDUP_OVERDUE_AMOUNT double(15,2),
   APPLY_STATUS         varchar(10) not null,
   REPAY_STATUS         varchar(10),
   SETTLE_DATE          date,
   SETTLE_MODE          varchar(32),
   APPLY_COMMENT        varchar(256),
   CREATE_ID            varchar(64) not null,
   CREATE_DATE          date not null,
   PROC_INST_ID         varchar(64) not null,
   primary key (ID)
);

alter table T_OTHER_FEE comment '其他费用申请表';

alter table T_OTHER_FEE modify column ID varchar(64)  comment 'ID';

alter table T_OTHER_FEE modify column APP_ID varchar(32)  comment '申请单号';

alter table T_OTHER_FEE modify column FEE_TOTAL_AMOUNT double(15,2)  comment '费用总金额';

alter table T_OTHER_FEE modify column VALUE_DATE date  comment '起息日';

alter table T_OTHER_FEE modify column CLOSING_DATE date  comment '结账日';

alter table T_OTHER_FEE modify column ADDUP_OVERDUE_DAY int  comment '累计罚息天数';

alter table T_OTHER_FEE modify column ADDUP_OVERDUE_AMOUNT double(15,2)  comment '累计罚息金额';

alter table T_OTHER_FEE modify column APPLY_STATUS varchar(10)  comment '申请状态';

alter table T_OTHER_FEE modify column REPAY_STATUS varchar(10)  comment '还款状态';

alter table T_OTHER_FEE modify column SETTLE_DATE date  comment '结清日期';

alter table T_OTHER_FEE modify column SETTLE_MODE varchar(32)  comment '结清方式';

alter table T_OTHER_FEE modify column APPLY_COMMENT varchar(256)  comment '申请备注';

alter table T_OTHER_FEE modify column CREATE_ID varchar(64)  comment '创建人';

alter table T_OTHER_FEE modify column CREATE_DATE date  comment '创建日期';

alter table T_OTHER_FEE modify column PROC_INST_ID varchar(64)  comment '流程实例ID';

/*==============================================================*/
/* Table: T_OTHER_FEE_DETAIL                                    */
/*==============================================================*/
create table T_OTHER_FEE_DETAIL
(
   ID                   varchar(64) not null,
   SEQ                  int not null,
   ITEM_NAME            varchar(32) not null,
   FEE_ID               varchar(64) not null,
   ITEM_AMOUNT          double(15,2) not null,
   primary key (ID)
);

alter table T_OTHER_FEE_DETAIL comment '其他费用明细';

alter table T_OTHER_FEE_DETAIL modify column ID varchar(64)  comment 'ID';

alter table T_OTHER_FEE_DETAIL modify column SEQ int  comment '序号';

alter table T_OTHER_FEE_DETAIL modify column ITEM_NAME varchar(32)  comment '费用种类';

alter table T_OTHER_FEE_DETAIL modify column FEE_ID varchar(64)  comment '费用汇总ID';

alter table T_OTHER_FEE_DETAIL modify column ITEM_AMOUNT double(15,2)  comment '费用金额';

/*==============================================================*/
/* Table: T_REMISSION_ITEM                                      */
/*==============================================================*/
create table T_REMISSION_ITEM
(
   ID                   varchar(64) not null,
   APPLY_TYPE           varchar(10) not null,
   APPLY_REF_ID         varchar(64) not null,
   CAPITAL              double(15,2),
   INTEREST             double(15,2),
   OVERDUE_AMOUNT       double(15,2),
   OTHER_FEE            double(15,2),
   OTHER_OVERDUE_AMOUNT double(15,2),
   LATE_FEE             double(15,2),
   primary key (ID)
);

alter table T_REMISSION_ITEM comment '申请减免项';

alter table T_REMISSION_ITEM modify column ID varchar(64)  comment 'ID';

alter table T_REMISSION_ITEM modify column APPLY_TYPE varchar(10)  comment '申请类型';

alter table T_REMISSION_ITEM modify column APPLY_REF_ID varchar(64)  comment '关联申请类型ID';

alter table T_REMISSION_ITEM modify column CAPITAL double(15,2)  comment '减免本金';

alter table T_REMISSION_ITEM modify column INTEREST double(15,2)  comment '减免利息';

alter table T_REMISSION_ITEM modify column OVERDUE_AMOUNT double(15,2)  comment '减免罚息';

alter table T_REMISSION_ITEM modify column OTHER_FEE double(15,2)  comment '减免其他费用';

alter table T_REMISSION_ITEM modify column OTHER_OVERDUE_AMOUNT double(15,2)  comment '减免其他费用罚息';

alter table T_REMISSION_ITEM modify column LATE_FEE double(15,2)  comment '减免违约金';

/*==============================================================*/
/* Table: T_REPAY_LOG                                           */
/*==============================================================*/
create table T_REPAY_LOG
(
   ID                   varchar(64) not null,
   APP_ID               varchar(64) not null,
   FEE_TYPE             varchar(10) not null,
   FEE_REF_ID           varchar(64) not null,
   CHARGE_ITEM          varchar(10) not null,
   CHARGE_AMOUNT        double(15,2) not null,
   CHARGE_TIME          timestamp not null,
   CHARGE_MODE          varchar(10) not null,
   primary key (ID)
);

alter table T_REPAY_LOG comment '还款记录';

alter table T_REPAY_LOG modify column ID varchar(64)  comment 'ID';

alter table T_REPAY_LOG modify column APP_ID varchar(64)  comment '关联申请单号';

alter table T_REPAY_LOG modify column FEE_TYPE varchar(10)  comment '费用类型';

alter table T_REPAY_LOG modify column FEE_REF_ID varchar(64)  comment '费用关联ID';

alter table T_REPAY_LOG modify column CHARGE_ITEM varchar(10)  comment '还款项目';

alter table T_REPAY_LOG modify column CHARGE_AMOUNT double(15,2)  comment '还款金额';

alter table T_REPAY_LOG modify column CHARGE_TIME timestamp  comment '还款时间';

alter table T_REPAY_LOG modify column CHARGE_MODE varchar(10)  comment '还款方式';

/*==============================================================*/
/* Table: T_REPAY_PLAN                                          */
/*==============================================================*/
create table T_REPAY_PLAN
(
   ID                   varchar(64) not null,
   APP_ID               varchar(32) not null,
   PERIOD               int not null,
   REPAY_CAPITAL        double(15,2) not null,
   REPAY_INTEREST       double(15,2) not null,
   VALUE_DATE           date not null,
   CLOSING_DATE         date not null,
   REMAIN_CAPITAL       double(15,2) not null,
   REPAY_TOTAL_AMOUNT   double(15,2) not null,
   ADDUP_OVERDUE_DAY    int not null,
   ADDUP_OVERDUE_AMOUNT double(15,2) not null,
   REPAY_STATUS         varchar(10) not null,
   SETTLE_MODE          varchar(32),
   SETTLE_DATE          date,
   RESERVER1            varchar(100),
   RESERVER2            varchar(100),
   RESERVER3            date,
   RESERVER4            date,
   RESERVER5            double(15,2),
   RESERVER6            double(15,2),
   primary key (ID)
);

alter table T_REPAY_PLAN comment '还款计划表';

alter table T_REPAY_PLAN modify column ID varchar(64)  comment 'ID';

alter table T_REPAY_PLAN modify column APP_ID varchar(32)  comment '关联申请单号';

alter table T_REPAY_PLAN modify column PERIOD int  comment '还款期数';

alter table T_REPAY_PLAN modify column REPAY_CAPITAL double(15,2)  comment '应还本金';

alter table T_REPAY_PLAN modify column REPAY_INTEREST double(15,2)  comment '应还利息';

alter table T_REPAY_PLAN modify column VALUE_DATE date  comment '起息日';

alter table T_REPAY_PLAN modify column CLOSING_DATE date  comment '结账日';

alter table T_REPAY_PLAN modify column REMAIN_CAPITAL double(15,2)  comment '剩余本金';

alter table T_REPAY_PLAN modify column REPAY_TOTAL_AMOUNT double(15,2)  comment '应还总额';

alter table T_REPAY_PLAN modify column ADDUP_OVERDUE_DAY int  comment '累计逾期天数';

alter table T_REPAY_PLAN modify column ADDUP_OVERDUE_AMOUNT double(15,2)  comment '累计逾期金额';

alter table T_REPAY_PLAN modify column REPAY_STATUS varchar(10)  comment '还款状态';

alter table T_REPAY_PLAN modify column SETTLE_MODE varchar(32)  comment '结清方式';

alter table T_REPAY_PLAN modify column SETTLE_DATE date  comment '结清日期';

alter table T_REPAY_PLAN modify column RESERVER1 varchar(100)  comment '保留字段1';

alter table T_REPAY_PLAN modify column RESERVER2 varchar(100)  comment '保留字段2';

alter table T_REPAY_PLAN modify column RESERVER3 date  comment '保留字段3';

alter table T_REPAY_PLAN modify column RESERVER4 date  comment '保留字段4';

alter table T_REPAY_PLAN modify column RESERVER5 double(15,2)  comment '保留字段5';

alter table T_REPAY_PLAN modify column RESERVER6 double(15,2)  comment '保留字段6';

/*==============================================================*/
/* Index: Index_1                                               */
/*==============================================================*/
create unique index Index_1 on T_REPAY_PLAN
(
   APP_ID,
   PERIOD
);

/*==============================================================*/
/* Table: T_STAY_ACCOUNT                                        */
/*==============================================================*/
create table T_STAY_ACCOUNT
(
   ID                   varchar(64) not null,
   APP_ID               varchar(32) not null,
   STAY_AMOUNT          double(15,2) not null,
   RESERVER1            varchar(100),
   RESERVER2            varchar(100),
   RESERVER3            date,
   RESERVER4            date,
   RESERVER5            double(15,2),
   RESSERVER6           double(15,2),
   primary key (ID)
);

alter table T_STAY_ACCOUNT comment '挂账表';

alter table T_STAY_ACCOUNT modify column ID varchar(64)  comment 'ID';

alter table T_STAY_ACCOUNT modify column APP_ID varchar(32)  comment '关联申请单号';

alter table T_STAY_ACCOUNT modify column STAY_AMOUNT double(15,2)  comment '挂账金额';

alter table T_STAY_ACCOUNT modify column RESERVER1 varchar(100)  comment '保留字段1';

alter table T_STAY_ACCOUNT modify column RESERVER2 varchar(100)  comment '保留字段2';

alter table T_STAY_ACCOUNT modify column RESERVER3 date  comment '保留字段3';

alter table T_STAY_ACCOUNT modify column RESERVER4 date  comment '保留字段4';

alter table T_STAY_ACCOUNT modify column RESERVER5 double(15,2)  comment '保留字段5';

alter table T_STAY_ACCOUNT modify column RESSERVER6 double(15,2)  comment '保留字段6';

/*==============================================================*/
/* Table: T_STAY_ACCOUNT_LOG                                    */
/*==============================================================*/
create table T_STAY_ACCOUNT_LOG
(
   ID                   varchar(64) not null,
   STAY_ID              varchar(64) not null,
   STAY_AMOUNT          double(15,2) not null,
   STAY_TIME            timestamp not null,
   STAY_SOURCE          varchar(10) not null,
   primary key (ID)
);

alter table T_STAY_ACCOUNT_LOG comment '挂账记录表';

alter table T_STAY_ACCOUNT_LOG modify column ID varchar(64)  comment 'ID';

alter table T_STAY_ACCOUNT_LOG modify column STAY_ID varchar(64)  comment '挂账表ID';

alter table T_STAY_ACCOUNT_LOG modify column STAY_AMOUNT double(15,2)  comment '挂账金额';

alter table T_STAY_ACCOUNT_LOG modify column STAY_TIME timestamp  comment '挂账时间';

alter table T_STAY_ACCOUNT_LOG modify column STAY_SOURCE varchar(10)  comment '挂账来源';

/*==============================================================*/
/* Table: T_WAITING_CHARGE                                      */
/*==============================================================*/
create table T_WAITING_CHARGE
(
   ID                   varchar(64) not null,
   APP_ID               varchar(64) not null,
   FEE_TYPE             varchar(10) not null,
   FEE_REF_ID           varchar(64) not null,
   REPAY_CAPITAL        double(15,2) not null,
   REPAY_INTEREST       double(15,2) not null,
   REPAY_OVERDUE_AMOUNT double(15,2) not null,
   CLOSING_DATE         date not null,
   REPAY_DATE           date not null,
   OFFER_STATUS         varchar(10) not null,
   OFFER_ID             varchar(64),
   GEN_TIME             datetime not null,
   VERSION_ID           integer not null,
   BATCH_TASK_ID        varchar(64) not null,
   BATCH_PROCESS_STATUS varchar(10) not null,
   RESERVER1            varchar(100),
   RESERVER2            varchar(100),
   RESERVER3            date,
   RESERVER4            date,
   RESERVER5            double(15,2),
   RESERVER6            double(15,2),
   RESERVER7            varchar(100),
   RESERVER8            varchar(100),
   RESERVER9            varchar(100),
   RESERVER10           varchar(100),
   primary key (ID)
);

alter table T_WAITING_CHARGE comment '待扣款明细表';

alter table T_WAITING_CHARGE modify column ID varchar(64)  comment 'ID';

alter table T_WAITING_CHARGE modify column APP_ID varchar(64)  comment '关联申请单号';

alter table T_WAITING_CHARGE modify column FEE_TYPE varchar(10)  comment '费用类型';

alter table T_WAITING_CHARGE modify column FEE_REF_ID varchar(64)  comment '费用关联ID';

alter table T_WAITING_CHARGE modify column REPAY_CAPITAL double(15,2)  comment '应还本金';

alter table T_WAITING_CHARGE modify column REPAY_INTEREST double(15,2)  comment '应还利息';

alter table T_WAITING_CHARGE modify column REPAY_OVERDUE_AMOUNT double(15,2)  comment '应还罚息';

alter table T_WAITING_CHARGE modify column CLOSING_DATE date  comment '结账日';

alter table T_WAITING_CHARGE modify column REPAY_DATE date  comment '还款日';

alter table T_WAITING_CHARGE modify column OFFER_STATUS varchar(10)  comment '报盘状态';

alter table T_WAITING_CHARGE modify column OFFER_ID varchar(64)  comment '报盘任务ID';

alter table T_WAITING_CHARGE modify column GEN_TIME datetime  comment '产生时间';

alter table T_WAITING_CHARGE modify column VERSION_ID integer  comment '版本号';

alter table T_WAITING_CHARGE modify column BATCH_TASK_ID varchar(64)  comment '日切批处理任务号';

alter table T_WAITING_CHARGE modify column BATCH_PROCESS_STATUS varchar(10)  comment '批量任务处理状态';

alter table T_WAITING_CHARGE modify column RESERVER1 varchar(100)  comment '保留字段1';

alter table T_WAITING_CHARGE modify column RESERVER2 varchar(100)  comment '保留字段2';

alter table T_WAITING_CHARGE modify column RESERVER3 date  comment '保留字段3';

alter table T_WAITING_CHARGE modify column RESERVER4 date  comment '保留字段4';

alter table T_WAITING_CHARGE modify column RESERVER5 double(15,2)  comment '保留字段5';

alter table T_WAITING_CHARGE modify column RESERVER6 double(15,2)  comment '保留字段6';

alter table T_WAITING_CHARGE modify column RESERVER7 varchar(100)  comment '保留字段7';

alter table T_WAITING_CHARGE modify column RESERVER8 varchar(100)  comment '保留字段8';

alter table T_WAITING_CHARGE modify column RESERVER9 varchar(100)  comment '保留字段9';

alter table T_WAITING_CHARGE modify column RESERVER10 varchar(100)  comment '保留字段10';

/*==============================================================*/
/* Table: T_WAITING_CHARGE_NEW                                  */
/*==============================================================*/
create table T_WAITING_CHARGE_NEW
(
   ID                   varchar(64) not null,
   APPLY_TYPE           varchar(10) not null,
   APPLY_REF_ID         varchar(64) not null,
   APP_ID               varchar(64) not null,
   FEE_TYPE             varchar(10) not null,
   FEE_REF_ID           varchar(64) not null,
   PERIOD               int,
   DO_SETTLE            bool not null,
   REPAY_CAPITAL        double(15,2) not null,
   REPAY_INTEREST       double(15,2) not null,
   REPAY_OVERDUE_AMOUNT double(15,2) not null,
   VALUE_DATE           date not null,
   CLOSING_DATE         date not null,
   ADDUP_OVERDUE_DAY    int not null,
   ADDUP_OVERDUE_AMOUNT double(15,2) not null,
   RESERVER1            varchar(100),
   RESERVER2            varchar(100),
   RESERVER3            date,
   RESERVER4            date,
   RESERVER5            double(15,2),
   RESERVER6            double(15,2),
   primary key (ID)
);

alter table T_WAITING_CHARGE_NEW comment '临时代扣款明细表';

alter table T_WAITING_CHARGE_NEW modify column ID varchar(64)  comment 'ID';

alter table T_WAITING_CHARGE_NEW modify column APPLY_TYPE varchar(10)  comment '申请类型';

alter table T_WAITING_CHARGE_NEW modify column APPLY_REF_ID varchar(64)  comment '关联申请任务ID';

alter table T_WAITING_CHARGE_NEW modify column APP_ID varchar(64)  comment '关联申请单号';

alter table T_WAITING_CHARGE_NEW modify column FEE_TYPE varchar(10)  comment '费用类型';

alter table T_WAITING_CHARGE_NEW modify column FEE_REF_ID varchar(64)  comment '关联费用ID';

alter table T_WAITING_CHARGE_NEW modify column PERIOD int  comment '还款计划期数';

alter table T_WAITING_CHARGE_NEW modify column DO_SETTLE bool  comment '是否结清处理';

alter table T_WAITING_CHARGE_NEW modify column REPAY_CAPITAL double(15,2)  comment '应还本金';

alter table T_WAITING_CHARGE_NEW modify column REPAY_INTEREST double(15,2)  comment '应还利息';

alter table T_WAITING_CHARGE_NEW modify column REPAY_OVERDUE_AMOUNT double(15,2)  comment '应还罚息';

alter table T_WAITING_CHARGE_NEW modify column VALUE_DATE date  comment '起息日';

alter table T_WAITING_CHARGE_NEW modify column CLOSING_DATE date  comment '结账日';

alter table T_WAITING_CHARGE_NEW modify column ADDUP_OVERDUE_DAY int  comment '累计逾期天数';

alter table T_WAITING_CHARGE_NEW modify column ADDUP_OVERDUE_AMOUNT double(15,2)  comment '累计逾期罚息';

alter table T_WAITING_CHARGE_NEW modify column RESERVER1 varchar(100)  comment '保留字段1';

alter table T_WAITING_CHARGE_NEW modify column RESERVER2 varchar(100)  comment '保留字段2';

alter table T_WAITING_CHARGE_NEW modify column RESERVER3 date  comment '保留字段3';

alter table T_WAITING_CHARGE_NEW modify column RESERVER4 date  comment '保留字段4';

alter table T_WAITING_CHARGE_NEW modify column RESERVER5 double(15,2)  comment '保留字段5';

alter table T_WAITING_CHARGE_NEW modify column RESERVER6 double(15,2)  comment '保留字段6';

alter table T_OFFER_DETAIL add constraint FK_Reference_22 foreign key (OFFER_ID)
      references T_OFFER_SUMMARY (ID) on delete restrict on update restrict;

alter table T_OTHER_FEE_DETAIL add constraint FK_Reference_21 foreign key (FEE_ID)
      references T_OTHER_FEE (ID) on delete restrict on update restrict;

alter table T_STAY_ACCOUNT_LOG add constraint FK_Reference_20 foreign key (STAY_ID)
      references T_STAY_ACCOUNT (ID) on delete restrict on update restrict;

