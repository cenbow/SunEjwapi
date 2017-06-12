CREATE TABLE `sys_message_appraise` (
  `msg_id` varchar(32) NOT NULL COMMENT 'message ID',
  `user_id` varchar(32) DEFAULT NULL COMMENT '评价人ID',
  `create_time` datetime NOT NULL COMMENT '评价时间',
  `turning_speed` tinyint(3) NOT NULL DEFAULT '5' COMMENT '接警速度',
  `processing_efficiency` tinyint(3) NOT NULL DEFAULT '5' COMMENT '处理效率',
  `feedback_result` tinyint(3) NOT NULL DEFAULT '5' COMMENT '反馈结果',
  PRIMARY KEY (`msg_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='咨询评价表'; 