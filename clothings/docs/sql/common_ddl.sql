
CREATE TABLE `b_local_message` (
  `fid` bigint(20) NOT NULL COMMENT '主键id',
  `num` varchar(64) NOT NULL COMMENT '消息编号',
  `tb_name` varchar(64) NOT NULL COMMENT '业务',
  `business_id` bigint(20) NOT NULL COMMENT '对应业务ID',
  `business_status` int(8) DEFAULT '0' COMMENT '业务状态',
  `create_date` timestamp COMMENT '创建时间',
  `expire_date` timestamp COMMENT '有效时间',
  `last_update` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `last_modifyer` varchar(64) DEFAULT 'N' COMMENT '记录最后修改人 关联b_user.fid',
  `creater` varchar(64) DEFAULT 'N' COMMENT '记录创建人 关联b_user.fid',
  PRIMARY KEY (`fid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='本地消息事物表';

