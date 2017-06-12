/* 下午2:17:20 120.26.169.207 standard_ejingwu 
 * jw_invitation_participant
 * */ 
ALTER TABLE `jw_invitation_participant` CHANGE `invitationid` `invitation_id` VARCHAR(32)  CHARACTER SET utf8  COLLATE utf8_general_ci  NOT NULL  DEFAULT ''  COMMENT '会议通知id';

/* 下午2:18:27 120.26.169.207 standard_ejingwu 
 * jw_meeting_invitation
 * */ 
ALTER TABLE `jw_meeting_invitation` CHANGE `createtime` `create_time` BIGINT(20)  NULL  DEFAULT NULL;
ALTER TABLE `jw_meeting_invitation` CHANGE `inviterid` `inviter_id` VARCHAR(32)  CHARACTER SET utf8  COLLATE utf8_general_ci  NOT NULL  DEFAULT ''  COMMENT '邀请人uid';
ALTER TABLE `jw_meeting_invitation` CHANGE `invitername` `inviter_name` VARCHAR(32)  CHARACTER SET utf8  COLLATE utf8_general_ci  NULL  DEFAULT NULL  COMMENT '邀请人姓名';
ALTER TABLE `jw_meeting_invitation` CHANGE `roomid` `room_id` VARCHAR(32)  CHARACTER SET utf8  COLLATE utf8_general_ci  NOT NULL  DEFAULT ''  COMMENT '会议房间id';
ALTER TABLE `jw_meeting_invitation` CHANGE `roomidstr` `room_id_str` VARCHAR(32)  CHARACTER SET utf8  COLLATE utf8_general_ci  NULL  DEFAULT NULL  COMMENT '会议房间roomid';
ALTER TABLE `jw_meeting_invitation` CHANGE `roomname` `room_name` VARCHAR(32)  CHARACTER SET utf8  COLLATE utf8_general_ci  NULL  DEFAULT NULL  COMMENT '房间名称';

/* 下午2:32:19 120.26.169.207 standard_ejingwu 
 * jw_meeting_room_xy
 * */ 
ALTER TABLE `jw_meeting_room_xy` CHANGE `createtime` `create_time` BIGINT(20)  NULL  DEFAULT NULL;
ALTER TABLE `jw_meeting_room_xy` CHANGE `updatetime` `update_time` BIGINT(20)  NULL  DEFAULT NULL;
ALTER TABLE `jw_meeting_room_xy` CHANGE `communityid` `community_id` INT(11)  NOT NULL;
ALTER TABLE `jw_meeting_room_xy` CHANGE `roomid` `room_id` VARCHAR(64)  CHARACTER SET utf8  COLLATE utf8_general_ci  NOT NULL  DEFAULT '';

/* 下午2:34:52 120.26.169.207 standard_ejingwu 
 * sys_model_field
 * */ 
ALTER TABLE `sys_model_field` CHANGE `isTwoCol` `is_two_col` INT(11)  NULL  DEFAULT NULL;

/* 下午2:36:26 120.26.169.207 standard_ejingwu 
 * sys_user
 * */ 
ALTER TABLE `sys_user` CHANGE `currAddress` `curr_address` VARCHAR(255)  CHARACTER SET utf8  BINARY  NULL  DEFAULT NULL;
ALTER TABLE `sys_user` CHANGE `deviceID` `device_id` VARCHAR(255)  CHARACTER SET utf8  BINARY  NULL  DEFAULT NULL;
ALTER TABLE `sys_user` CHANGE `rlyCode` `rly_code` VARCHAR(255)  CHARACTER SET utf8  BINARY  NULL  DEFAULT NULL;
ALTER TABLE `sys_user` CHANGE `ytCode` `yt_code` VARCHAR(255)  CHARACTER SET utf8  BINARY  NULL  DEFAULT NULL;
ALTER TABLE `sys_user` CHANGE `policemanCode` `policeman_code` VARCHAR(255)  CHARACTER SET utf8  BINARY  NULL  DEFAULT NULL;
ALTER TABLE `sys_user` CHANGE `gtCode` `gt_code` VARCHAR(255)  CHARACTER SET utf8  BINARY  NULL  DEFAULT NULL;
ALTER TABLE `sys_user` CHANGE `lastLoginOutTime` `last_logout_time` DATETIME  NULL  DEFAULT NULL;
ALTER TABLE `sys_user` CHANGE `lastLoginTime` `last_login_time` DATETIME  NULL  DEFAULT NULL;
ALTER TABLE `sys_user` CHANGE `authMsg` `auth_msg` VARCHAR(500)  CHARACTER SET utf8  BINARY  NULL  DEFAULT NULL  COMMENT '认证备注,可以存储失败原因等';


/* 上午9:26:43 local standard_ejingwu 
 * jw_alert
 * */ 
ALTER TABLE `jw_alert` CHANGE `imgs` `imgs` VARCHAR(512)  CHARACTER SET utf8  COLLATE utf8_general_ci  NULL  DEFAULT '';
ALTER TABLE `jw_alert` CHANGE `voices` `voices` VARCHAR(512)  CHARACTER SET utf8  COLLATE utf8_general_ci  NULL  DEFAULT '';
ALTER TABLE `jw_alert` CHANGE `videos` `videos` VARCHAR(512)  CHARACTER SET utf8  COLLATE utf8_general_ci  NULL  DEFAULT '';

/* 上午10:55:39 local standard_ejingwu 
 * jw_comment
 * */ 
ALTER TABLE `jw_comment` CHANGE `content` `content` VARCHAR(512)  CHARACTER SET utf8  COLLATE utf8_general_ci  NOT NULL  DEFAULT ''  COMMENT '内容';
ALTER TABLE `jw_comment` CHANGE `imgs` `imgs` VARCHAR(512)  CHARACTER SET utf8  COLLATE utf8_general_ci  NULL  DEFAULT ''  COMMENT '图片 多个以,割开';
ALTER TABLE `jw_comment` CHANGE `voices` `voices` VARCHAR(512)  CHARACTER SET utf8  COLLATE utf8_general_ci  NULL  DEFAULT ''  COMMENT '声音 多个以,割开';
ALTER TABLE `jw_comment` CHANGE `videos` `videos` VARCHAR(512)  CHARACTER SET utf8  COLLATE utf8_general_ci  NULL  DEFAULT ''  COMMENT '视频 多个以,割开';

/* 上午11:07:55 local standard_ejingwu 
 * sys_audit_record
 * */ 
ALTER TABLE `sys_audit_record` CHANGE `result_imgs` `result_imgs` VARCHAR(512)  CHARACTER SET utf8  COLLATE utf8_general_ci  NULL  DEFAULT ''  COMMENT '处理上传图片';
ALTER TABLE `sys_audit_record` CHANGE `result_voices` `result_voices` VARCHAR(512)  CHARACTER SET utf8  COLLATE utf8_general_ci  NULL  DEFAULT ''  COMMENT '处理上传声音';
ALTER TABLE `sys_audit_record` CHANGE `result_videos` `result_videos` VARCHAR(512)  CHARACTER SET utf8  COLLATE utf8_general_ci  NULL  DEFAULT ''  COMMENT '处理上传视频';


