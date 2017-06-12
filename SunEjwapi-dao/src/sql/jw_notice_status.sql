/* 上午10:21:57 local standard_ejingwu */ 
ALTER TABLE `jw_notice` ADD `status` TINYINT(3)  NULL  DEFAULT NULL  COMMENT '发送状态 0 失败 1成功'  AFTER `update_time`;
/* 上午10:22:14 local standard_ejingwu */ 
ALTER TABLE `jw_notice` ADD `msg` VARCHAR(255)  NULL  DEFAULT NULL  COMMENT '失败原因描述'  AFTER `status`;
