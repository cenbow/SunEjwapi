CREATE TABLE `jw_ems` (
  `id` varchar(255) NOT NULL DEFAULT '',
  `tx_logistic_id` varchar(64) NOT NULL DEFAULT '' COMMENT '物流订单号-对应recordID',
  `mail_num` varchar(64) DEFAULT NULL COMMENT '物流运单号',
  `status` int(11) DEFAULT NULL COMMENT '揽投状态，1成功 其他失败',
  `desc` varchar(500) DEFAULT NULL COMMENT '揽投失败原因描述',
  `create_time` timestamp DEFAULT NULL  COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='EMS物流订单表';

CREATE TABLE `jw_ems_status` (
  `id` varchar(64) NOT NULL DEFAULT '',
  `mail_num` varchar(64) DEFAULT NULL COMMENT '物流运单号',
  `serial_number` varchar(64) DEFAULT NULL COMMENT '序列号',
  `procdate` varchar(12) DEFAULT NULL COMMENT '发生日期',
  `proctime` varchar(12) DEFAULT NULL COMMENT '发生时间',
  `org_full_name` varchar(255) DEFAULT NULL COMMENT '所在地名称',
  `action` varchar(12) DEFAULT NULL COMMENT '业务动作',
  `proper_delivery` varchar(20) DEFAULT NULL COMMENT '签收情况代码',
  `not_proper_delivery` varchar(20) DEFAULT NULL COMMENT '未妥投原因代码',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `effect` int(2) DEFAULT NULL COMMENT '0无效 1 有效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='EMS物流状态表';

CREATE TABLE `jw_ems_info` (
  `record_id` varchar(64) NOT NULL DEFAULT '' COMMENT '记录ID',
  `username` varchar(32) NOT NULL DEFAULT '' COMMENT '收件人姓名',
  `phone` varchar(32) NOT NULL DEFAULT '' COMMENT '收件人手机号',
  `area_id` varchar(64) NOT NULL DEFAULT '' COMMENT '地区ID',
  `address` varchar(255) NOT NULL DEFAULT '' COMMENT '收件地址',
  `postcode` varchar(12) DEFAULT NULL COMMENT '邮政编码',
  PRIMARY KEY (`record_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='EMS物流信息表';