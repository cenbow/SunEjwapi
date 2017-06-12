CREATE TABLE `sys_user_log` (
  `id` varchar(32) COLLATE utf8_bin NOT NULL,
  `user_id` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '关联sys_user.id',
  `action` tinyint(3) NOT NULL COMMENT '用户行为 (1-登录,0-登出)',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户日志表';
