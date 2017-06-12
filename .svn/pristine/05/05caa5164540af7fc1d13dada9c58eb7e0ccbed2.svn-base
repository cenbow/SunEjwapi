CREATE TABLE `jw_criminal_record` (
  `id` varchar(255) NOT NULL DEFAULT '',
  `user_id` varchar(255) NOT NULL DEFAULT '' COMMENT '用户ID',
  `idcard_no` varchar(255) NOT NULL DEFAULT '' COMMENT '身份证号码',
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '姓名',
  `unit_name` varchar(255) NOT NULL DEFAULT '' COMMENT '单位名称',
  `target` varchar(255) NOT NULL DEFAULT '' COMMENT '证明用途',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `police_id` varchar(255) DEFAULT '' COMMENT '民警ID',
  `police_name` varchar(255) DEFAULT '' COMMENT '民警姓名',
  `police_phone` varchar(15) DEFAULT '' COMMENT '民警电话',
  `finish_time` timestamp NULL DEFAULT '0000-00-00 00:00:00' COMMENT '开据时间,点击自行领取或邮寄的时间',
  `user_phone` varchar(15) NOT NULL DEFAULT '' COMMENT '用户电话',
  `status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '打印状态 0 未打印 1 成功 2 失败',
  `print_time` timestamp NULL DEFAULT NULL COMMENT '打印时间',
  `address` varchar(255) DEFAULT NULL COMMENT '现居地',
  `auth_status` int(3) DEFAULT '0' COMMENT '状态: 0 待受理：提交申请等待受理 ,1 待审批：已受理等待审核结果, 2 待确认：审核通过，等待用户选择领取方式, 3 待自取：用户选择自行领取 4,配送中：用户选择邮寄并支付成功 5 ,未通过：未受理被驳回 ,6 已签收：快递单号状态变为收件,7 已领取：用户自行前往领取成功, 8 已失效：审核通过、未受理、选择自行领取以上三种7天内无后续操作',
  `has_record` tinyint(2) NOT NULL DEFAULT '0' COMMENT '是否有犯罪记录 0 未知， 1 没有 ， 2 有',
  `auth_desc` varchar(255) DEFAULT '' COMMENT '审核拒绝原因',
  `res_address` varchar(255) DEFAULT '' COMMENT '户籍地',
  `idcard_img1` varchar(255) DEFAULT NULL COMMENT '身份证正面照片地址',
  `idcard_img2` varchar(255) DEFAULT NULL COMMENT '身份证反面照片地址',
  `attachment` varchar(255) DEFAULT NULL COMMENT '公函照片地址',
  `dept_id` varchar(255) DEFAULT NULL COMMENT '受理单位ID',
  `type` int(2) DEFAULT NULL COMMENT '类型 0 自己 1 代办 ',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `recieve_type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '用户接受方式,默认0， 1 自取 2 EMS',
  `print_idcrad` varchar(64) DEFAULT NULL COMMENT '打印人身份证号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='无犯罪记录证明申请表';

/* 下午5:42:19 120.26.169.207 standard_ejingwu */ 
ALTER TABLE `jw_criminal_record` ADD `receiver_name` VARCHAR(255)  NULL  DEFAULT NULL  COMMENT '领取人身份证姓名'  AFTER `invalid_time`;


/* 下午2:00:00 120.26.169.207 standard_ejingwu */
ALTER TABLE  `jw_criminal_record` ADD COLUMN `audit_time` TIMESTAMP NULL COMMENT '审核时间' AFTER `receiver_name`;

/* 下午15:00:00 120.26.169.207 standard_ejingwu */
ALTER TABLE  `jw_criminal_record` 
CHANGE COLUMN `auth_status` `auth_status` INT(3) NULL DEFAULT '0' COMMENT '状态: 0 待受理：提交申请等待受理 ,1 待审批：已受理等待审核结果, 2 待确认：审核通过，等待用户选择领取方式, 3 待自取：用户选择自行领取 4,配送中：用户选择邮寄并支付成功 5 ,未通过：未受理被驳回 ,6 已签收：快递单号状态变为收件,7 已领取：用户自行前往领取成功, 8 已失效：审核通过、未受理、选择自行领取以上三种7天内无后续操作, 9 支付失败, 10 已评价' ;
