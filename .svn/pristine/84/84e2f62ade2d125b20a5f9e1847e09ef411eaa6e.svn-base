CREATE TABLE `sys_apply_record` (
  `id` varchar(32) NOT NULL,
  `type` tinyint(3) NOT NULL COMMENT '类型,1无犯罪记录证明',
  `record_id` varchar(32) NOT NULL DEFAULT '' COMMENT '记录ID',
  `user_id` varchar(32) NOT NULL COMMENT '操作用户id',
  `status` tinyint(3) NOT NULL COMMENT '状态: 0 待受理：提交申请等待受理 ,1 待审批：已受理等待审核结果, 2 待确认：审核通过，等待用户选择领取方式, 3 待自取：用户选择自行领取 4,配送中：用户选择邮寄并支付成功 5 ,未通过：未受理被驳回 ,6 已签收：快递单号状态变为收件,7 已领取：用户自行前往领取成功, 8 已失效：审核通过、未受理、选择自行领取以上三种7天内无后续操作',
  `desc` varchar(500) NOT NULL COMMENT '描述',
  `create_time` datetime NOT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`),
  KEY `index_applyrecord_recordid` (`record_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='网上办事操作日志表';