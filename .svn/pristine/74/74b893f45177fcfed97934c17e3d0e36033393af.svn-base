
CREATE TABLE `jw_alert_appraise` (
  `alert_id` varchar(32) NOT NULL COMMENT '警情ID',
  `user_id` varchar(32) DEFAULT NULL COMMENT '评价人ID，为空时为系统自动评价',
  `create_time` datetime NOT NULL COMMENT '评价时间',
  `turning_speed` tinyint(3) NOT NULL DEFAULT '5' COMMENT '接警速度',
  `processing_efficiency` tinyint(3) NOT NULL DEFAULT '5' COMMENT '处理效率',
  `feedback_result` tinyint(3) NOT NULL DEFAULT '5' COMMENT '反馈结果',
  PRIMARY KEY (`alert_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='警情评价表';