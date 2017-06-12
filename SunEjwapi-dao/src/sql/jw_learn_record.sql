CREATE TABLE `jw_learn_record` (
  `id` VARCHAR(32) NOT NULL COMMENT 'ID',
  `content_id` VARCHAR(32) NOT NULL COMMENT '文件内容id',
  `name` VARCHAR(255) NULL COMMENT '阅读人姓名',
  `uid` VARCHAR(32) NOT NULL COMMENT '阅读人UID',
  `time` DATETIME NOT NULL COMMENT '阅读时间',
  `createtime` DATETIME NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  INDEX `index_learnrecord_contentid` (`content_id` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = '政策学习阅读记录表';