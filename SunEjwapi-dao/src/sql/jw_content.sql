##增加消息发送状态标识
/* 上午10:10:44 120.26.169.207 standard_ejingwu */ 
ALTER TABLE `jw_content` ADD `msg_status` INT(11)  NULL  DEFAULT NULL  COMMENT '消息发送状态'  AFTER `uptop_id`;

/* 下午13:00:00 120.26.169.207 standard_ejingwu*/
ALTER TABLE `jw_content` 
ADD COLUMN `code` TINYINT(3) NULL DEFAULT 0 COMMENT '办事指南对应的网上办事事项    默认0     1：无犯罪记录' AFTER `uptop_endtime`;