CREATE TABLE `jw_ems_pay_result` (
  `id` varchar(255)  NOT NULL DEFAULT '' COMMENT '记录ID',
  `biz_order_no` varchar(64)  NOT NULL DEFAULT '' COMMENT '业务订单号，对应申请记录ID',
  `pay_platform` varchar(11)  NOT NULL DEFAULT '' COMMENT '支付平台 P，微信支付 Q，电子扫码支付',
  `pay_way` varchar(11)  NOT NULL DEFAULT '' COMMENT '支付接口 01.PC网页在线跳转支付 02 手机端wap支付',
  `pay_amount` varchar(11)  NOT NULL DEFAULT '' COMMENT '交易金额 以分为单位',
  `pay_result` varchar(3)  NOT NULL DEFAULT '' COMMENT '支付状态 01待付,02已付,03已退款，04退款失败',
  `req_reserved` varchar(255)  DEFAULT NULL COMMENT '请求方保留用域，用户自定义。回调用户前台地址时，会原样返回',
  `pay_time` varchar(64)  NOT NULL DEFAULT '' COMMENT '交易传输时间',
  `user_pay_account` varchar(64)  DEFAULT NULL COMMENT '付款人的支付账号（银行卡号或支付宝账号）',
  `order_time` varchar(64)  NOT NULL DEFAULT '' COMMENT '订单发起时间',
  `refund_result` varchar(11)  DEFAULT NULL COMMENT '退款结果 T:成功 F 失败',
  `faild_reason` varchar(255)  DEFAULT NULL COMMENT '退款失败原因',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='物流支付记录表';