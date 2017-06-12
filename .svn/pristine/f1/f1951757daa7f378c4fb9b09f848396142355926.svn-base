CREATE TABLE `jw_ems_area` (
  `id` int(11) NOT NULL COMMENT '地区代码',
  `parent_id` int(11) NOT NULL COMMENT '上级地区代码',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `merger_name` varchar(255) DEFAULT NULL COMMENT '全称',
  `short_name` varchar(255) DEFAULT NULL COMMENT '简称',
  `merger_short_name` varchar(255) DEFAULT NULL COMMENT '全简称',
  `level_type` int(11) DEFAULT NULL COMMENT '层级  1省直辖市 2 市 3 区县',
  `city_code` varchar(255) DEFAULT NULL COMMENT '城市代码',
  `zip_code` varchar(255) DEFAULT NULL COMMENT '邮编',
  `pinyin` varchar(255) DEFAULT NULL COMMENT '名称拼音',
  `jianpin` varchar(255) DEFAULT NULL COMMENT '简称拼音',
  `firstchar` varchar(255) DEFAULT NULL COMMENT '首字母',
  `lng` varchar(255) DEFAULT NULL COMMENT '经度',
  `lat` varchar(255) DEFAULT NULL COMMENT '纬度',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='邮政地区代码表';