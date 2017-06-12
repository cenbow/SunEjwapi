/*2017-03-13 新增字段  洪黎*/
ALTER TABLE `jw_content` 
ADD COLUMN `type` TINYINT(3) NULL COMMENT '信息类别(1警情通报 2协查通报 3社区动态 4办事指南 5防诈骗宣传 6消息通知)' AFTER `update_time`,
ADD COLUMN `uptop_time` DATETIME NULL COMMENT '置顶时间' AFTER `type`,
ADD COLUMN `uptop_days` INT(11) NULL COMMENT '置顶天数，默认30天' AFTER `uptop_time`,
ADD COLUMN `uptop_id` VARCHAR(32) NULL COMMENT '置顶人' AFTER `uptop_days`;