/*2017-03-13 新增表    洪黎*/
CREATE TABLE `jw_content_dept` (
  `id` varchar(32) NOT NULL,
  `content_id` varchar(32) NOT NULL COMMENT '内容发布id',
  `dept_id` varchar(45) NOT NULL COMMENT '单位id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='内容发布对象区域关联表';

/*2017-03-24 新增索引    洪黎*/
ALTER TABLE  `jw_content_dept` 
ADD INDEX `index_jwcontentdept_contentid` (`content_id` ASC);

ALTER TABLE  `jw_content_dept` 
ADD INDEX `index_jwcontentdept_deptid` (`dept_id` ASC);