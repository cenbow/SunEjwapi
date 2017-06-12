/* 下午4:59:53 120.26.169.207 standard_ejingwu */ 
ALTER TABLE `jw_alert` ADD `bd_location` VARCHAR(200)  NULL  DEFAULT ''  COMMENT '报警坐标(百度系)'  AFTER `location`;

/* 下午5:00:41 120.26.169.207 standard_ejingwu */ 
ALTER TABLE `sys_user` ADD `bd_position` VARCHAR(255)  NOT NULL  DEFAULT ''  COMMENT '经纬度坐标(百度)'  AFTER `position`;
