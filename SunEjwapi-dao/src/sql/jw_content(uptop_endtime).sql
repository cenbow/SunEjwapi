/*2017-03-22 新增字段    洪黎*/
ALTER TABLE `jw_content` 
ADD COLUMN `uptop_endtime` DATETIME NULL COMMENT '置顶截止时间' AFTER `msg_status`;