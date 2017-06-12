ALTER TABLE  `sys_message` 
CHANGE COLUMN `status` `status` TINYINT(3) NOT NULL DEFAULT '2' COMMENT '状态\n0已屏蔽\n1已回复\n2未回复\n3.已评价' ;
