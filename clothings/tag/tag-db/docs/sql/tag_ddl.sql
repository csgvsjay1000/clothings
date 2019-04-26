
CREATE TABLE `b_tag` (
  `fid` bigint(20) NOT NULL COMMENT '主键id',
  `epc` varchar(64) NOT NULL COMMENT '标签 EPC值',
  `location_num` varchar(64) NOT NULL DEFAULT 'N' COMMENT '具体位置id 关联b_location.num',
  `fstatus` varchar(16) DEFAULT 'N' COMMENT '状态    1 绑定  ，2 解绑',
  `item_number` varchar(64) DEFAULT 'N' COMMENT '商品款号 ',
  `barcode` varchar(64) DEFAULT 'N' COMMENT '商品条码',
  `name` varchar(64) DEFAULT 'N' COMMENT '商品名称',
  `color` varchar(64) DEFAULT 'N' COMMENT '颜色',
  `size` varchar(64) DEFAULT 'N' COMMENT '尺寸',
  `remark` varchar(256) DEFAULT 'N' COMMENT '备注',
  `attr01` varchar(256) DEFAULT 'N' COMMENT '预留字段1',
  `attr02` varchar(256) DEFAULT 'N' COMMENT '预留字段2',
  `attr03` varchar(256) DEFAULT 'N' COMMENT '预留字段3',
  `create_date` timestamp COMMENT '创建时间',
  `last_update` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `last_modifyer` varchar(64) DEFAULT 'N' COMMENT '记录最后修改人 关联b_user.fid',
  `creater` varchar(64) DEFAULT 'N' COMMENT '记录创建人 关联b_user.fid',
  PRIMARY KEY (`fid`),
  UNIQUE KEY `epc_UNIQUE` (`epc`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='标签当前状态表';

CREATE TABLE `b_tag_flow` (
  `fid` bigint(20) NOT NULL COMMENT '主键id',
  `fstatus` varchar(16) DEFAULT 'N' COMMENT '状态',
  `operation_name` varchar(64) NOT NULL DEFAULT 'N' COMMENT '业务操作名称',
  `epc` varchar(64) NOT NULL COMMENT '标签 EPC值',
  `barcode` varchar(64) DEFAULT 'N' COMMENT '条码',
  `color` varchar(64) DEFAULT 'N' COMMENT '关联b_goods_detail.fid',
  `size` varchar(64) DEFAULT 'N' COMMENT '关联b_goods_detail.fid',
  `bill_num` varchar(64) DEFAULT 'N' COMMENT '单据号',
  `package_num` varchar(64) DEFAULT 'N' COMMENT '关联箱号',
  `location_num` varchar(64) NOT NULL DEFAULT 'N' COMMENT '当前操作方的位置id',
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `creater` varchar(64) NOT NULL DEFAULT 'N' COMMENT '记录创建人 关联b_user.fid',
  PRIMARY KEY (`fid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='标签操作流水表';


