CREATE TABLE `f_goods_purchase_order` (
  `fid` bigint(20) NOT NULL COMMENT '主键id',
  `num` varchar(64) NOT NULL COMMENT '单据编码',
  `item_number` varchar(64) DEFAULT 'N' COMMENT '商品款号 ',
  `bill_type` varchar(16) DEFAULT 'N' COMMENT '单据类型',
  `bill_status` int(8) DEFAULT '0' COMMENT '单据状态 0-草稿 1-新单 2-生产中 3-已完成 4-已作废',
  `from_location_id` varchar(64) DEFAULT 'N' COMMENT '来源位置,引用b_location.fid',
  `to_location_id` varchar(64) DEFAULT 'N' COMMENT '订单发往仓库,引用b_location.fid',
  `book_qty` int(16) DEFAULT '0' COMMENT '采购订单总数量',
  `produce_qty` int(16) DEFAULT '0' COMMENT '已生产总数量 即已绑标数量',
  `transfer_qty` int(16) DEFAULT '0' COMMENT '已发货总数量 即已装箱并发出数量',
  `acceptance_qty` int(16) DEFAULT '0' COMMENT '已验收总数量 即客户验收入库总数量',
  `mixture_ratio_type` varchar(4096) DEFAULT NULL COMMENT '商品配比类型 1-大配比 2-小配比 3-..',
  `mixture_ratio_key` varchar(4096) DEFAULT NULL COMMENT '商品配比 S:M:L ',
  `mixture_ratio_value` varchar(4096) DEFAULT NULL COMMENT '商品配比 2:4:3 尾箱显示具体商品数量 整箱根据数量计算比例',
  `biz_start_date` timestamp NULL DEFAULT '0000-00-00 00:00:00' COMMENT '采购单开始生效日期',
  `active_time` int(16) DEFAULT '0' COMMENT '采购单有效时长',
  `deliver_deadline` timestamp NULL DEFAULT '0000-00-00 00:00:00' COMMENT '采购单最后交付日期',
  `remark` varchar(256) DEFAULT 'N' COMMENT '备注',
  `attr01` varchar(256) DEFAULT 'N' COMMENT '预留字段1',
  `attr02` varchar(256) DEFAULT 'N' COMMENT '预留字段2',
  `attr03` varchar(256) DEFAULT 'N' COMMENT '预留字段3',
  `create_date` timestamp COMMENT '创建时间',
  `last_update` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `last_modifyer` varchar(64) DEFAULT 'N' COMMENT '记录最后修改人 关联b_user.fid',
  `creater` varchar(64) DEFAULT 'N' COMMENT '记录创建人 关联b_user.fid',
  PRIMARY KEY (`fid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='采购单主表';

CREATE TABLE `f_goods_purchase_order_detail` (
  `fid` bigint(20) NOT NULL COMMENT '主键id',
  `purchase_order_num` varchar(64) NOT NULL COMMENT '采购单ID 关联f_goods_purchase_order.fid',
  `item_number` varchar(64) DEFAULT 'N' COMMENT '商品款号',
  `barcode` varchar(64) DEFAULT 'N' COMMENT '商品条码',
  `book_qty` int(16) DEFAULT '0' COMMENT '采购订单总数量',
  `produce_qty` int(16) DEFAULT '0' COMMENT '已生产总数量 即已绑标数量',
  `transfer_qty` int(16) DEFAULT '0' COMMENT '已发货总数量 即已装箱并发出数量',
  `acceptance_qty` int(16) DEFAULT '0' COMMENT '已验收总数量 即客户验收入库总数量',
  `remark` varchar(256) DEFAULT 'N' COMMENT '备注',
  `attr01` varchar(256) DEFAULT 'N' COMMENT '预留字段1',
  `attr02` varchar(256) DEFAULT 'N' COMMENT '预留字段2',
  `attr03` varchar(256) DEFAULT 'N' COMMENT '预留字段3',
  `create_date` timestamp COMMENT '创建时间',
  `last_update` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `last_modifyer` varchar(64) DEFAULT 'N' COMMENT '记录最后修改人 关联b_user.fid',
  `creater` varchar(64) DEFAULT 'N' COMMENT '记录创建人 关联b_user.fid',
  PRIMARY KEY (`fid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='采购单详表';

CREATE TABLE `f_goods_purchase_order_item` (
  `fid` bigint(20) NOT NULL COMMENT '主键id',
  `purchase_order_num` varchar(64) NOT NULL COMMENT '采购单ID 关联f_goods_purchase_order.num',
  `order_detail_num` varchar(64) NOT NULL COMMENT '采购详表ID 关联f_goods_purchase_order_detail.num',
  `epc` varchar(64) NOT NULL COMMENT '标签EPC值',
  `remark` varchar(256) DEFAULT 'N' COMMENT '备注',
  `attr01` varchar(256) DEFAULT 'N' COMMENT '预留字段1',
  `attr02` varchar(256) DEFAULT 'N' COMMENT '预留字段2',
  `attr03` varchar(256) DEFAULT 'N' COMMENT '预留字段3',
  `create_date` timestamp COMMENT '创建时间',
  `last_update` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `last_modifyer` varchar(64) DEFAULT 'N' COMMENT '记录最后修改人 关联b_user.fid',
  `creater` varchar(64) DEFAULT 'N' COMMENT '记录创建人 关联b_user.fid',
  PRIMARY KEY (`fid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='采购单明细表';


CREATE TABLE `f_delivery_bill` (
  `fid` bigint(20) NOT NULL COMMENT '主键id',
  `upstream_num` varchar(64) NOT NULL COMMENT '上游单据号',
  `upstream_date` timestamp  COMMENT '上游单据入我们系统时间，用于分库查找',
  `num` varchar(64) NOT NULL COMMENT '单据编码',
  `item_num` varchar(64) NOT NULL COMMENT '款号',
  `bill_type` varchar(16) DEFAULT 'N' COMMENT '单据类型,工厂发货单OF01',
  `bill_status` int(8) DEFAULT '0' COMMENT '单据状态',
  `allow_diff` int(8) DEFAULT '0' COMMENT '是否允许差异收货 0-不允许 1-允许',
  `from_location_num` varchar(64) DEFAULT 'N' COMMENT '商品移出位置,引用b_location.fid',
  `to_location_num` varchar(64) DEFAULT 'N' COMMENT '商品移入位置,引用b_location.fid',
  `package_qty` int(16) DEFAULT '0' COMMENT '包装数量',
  `transfer_qty` int(16) DEFAULT '0' COMMENT '已发货总数量 即已装箱并发出数量',
  `acceptance_qty` int(16) DEFAULT '0' COMMENT '已验收总数量 即客户验收入库总数量',
  `remark` varchar(256) DEFAULT 'N' COMMENT '备注',
  `attr01` varchar(256) DEFAULT 'N' COMMENT '预留字段1',
  `attr02` varchar(256) DEFAULT 'N' COMMENT '预留字段2',
  `attr03` varchar(256) DEFAULT 'N' COMMENT '预留字段3',
  `create_date` timestamp COMMENT '创建时间',
  `last_update` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `last_modifyer` varchar(64) DEFAULT 'N' COMMENT '记录最后修改人 关联b_user.fid',
  `creater` varchar(64) DEFAULT 'N' COMMENT '记录创建人 关联b_user.fid',
  PRIMARY KEY (`fid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='工厂移仓单主表';

CREATE TABLE `f_delivery_bill_trace` (
  `fid` bigint(20) NOT NULL COMMENT '主键id',
  `bill_num` varchar(64) NOT NULL COMMENT '上游单据号',
  `bill_status` int(8) DEFAULT '0' COMMENT '单据操作完对应的状态',
  `specific_action` varchar(64) DEFAULT 'N' COMMENT '具体操作',
  `operator` varchar(64) DEFAULT 'N' COMMENT '操作人用户名',
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间即操作时间',
  `creater` varchar(64) NOT NULL DEFAULT 'N' COMMENT '记录创建人 关联b_user.fid',
  PRIMARY KEY (`fid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='工厂移仓单主表';


CREATE TABLE `f_goods_package` (
  `fid` bigint(20) NOT NULL COMMENT '主键id',
  `from_location_num` varchar(64) NOT NULL COMMENT '商品包装位置id 关联b_location.fid',
  `to_location_num` varchar(64) DEFAULT NULL,
  `purchase_order_num` varchar(64) NOT NULL COMMENT '采购订单id 关联f_goods_purchase_order.fid',
  `bill_num` varchar(64) NOT NULL COMMENT '采购订单id 关联f_goods_purchase_order.fid',
  `num` varchar(64) NOT NULL COMMENT '包装编码',
  `name` varchar(64) DEFAULT 'N' COMMENT '包装名称 袋 箱 包',
  `nickname` varchar(64) DEFAULT 'N' COMMENT '包装昵称',
  `package_status` int(8) DEFAULT '1' COMMENT '发货方包装状态 1-新箱 2-打包中 3-包装完成 4-送审中 5-同意送货 6-不同意 5-已发货 6-已验收',
  `item_number` varchar(64) DEFAULT 'N' COMMENT '商品款号',
  `firstAssAttr` varchar(64) DEFAULT 'N' COMMENT '辅助属性1(颜色)',
  `secondAssAttr` varchar(64) DEFAULT 'N' COMMENT '辅助属性2(尺寸)',
  `sign_type` int(8) DEFAULT '0' COMMENT '包装标识 预留作为校验位',
  `mixture_ratio_type` varchar(64) NOT NULL COMMENT '商品配比类型 1-大配比 2-小配比 3-..',
  `mixture_ratio_key` varchar(64) NOT NULL COMMENT '商品配比 S:M:L ',
  `mixture_ratio_value` varchar(64) NOT NULL COMMENT '商品配比 2:4:3 尾箱显示具体商品数量 整箱根据数量计算比例',
  `goods_qty` int(16) DEFAULT '0' COMMENT '实际包装数量',
  `acceptance_qty` int(16) DEFAULT '0' COMMENT '已验收总数量',
  `remark` varchar(256) DEFAULT 'N' COMMENT '备注',
  `attr01` varchar(256) DEFAULT 'N' COMMENT '预留字段1',
  `attr02` varchar(256) DEFAULT 'N' COMMENT '预留字段2',
  `attr03` varchar(256) DEFAULT 'N' COMMENT '预留字段3',
  `create_date` timestamp COMMENT '创建时间',
  `last_update` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `last_modifyer` varchar(64) DEFAULT 'N' COMMENT '记录最后修改人 关联b_user.fid',
  `creater` varchar(64) DEFAULT 'N' COMMENT '记录创建人 关联b_user.fid',
  PRIMARY KEY (`fid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='包装主表';


CREATE TABLE `f_goods_package_detail` (
  `fid` bigint(20) NOT NULL COMMENT '主键id',
  `package_id` bigint(20) NOT NULL COMMENT '包装ID s_goods_package.fid',
  `item_number` varchar(64) DEFAULT 'N' COMMENT '商品款号',
  `barcode` varchar(64) DEFAULT 'N' COMMENT '商品条码',
  `firstAssAttr` varchar(64) DEFAULT 'N' COMMENT '辅助属性1(颜色)',
  `secondAssAttr` varchar(64) DEFAULT 'N' COMMENT '辅助属性2(尺寸)',
  `goods_qty` int(16) DEFAULT '0' COMMENT '实际包装数量',
  `acceptance_qty` int(16) DEFAULT '0' COMMENT '已验收总数量',
  `remark` varchar(256) DEFAULT 'N' COMMENT '备注',
  `attr01` varchar(256) DEFAULT 'N' COMMENT '预留字段1',
  `attr02` varchar(256) DEFAULT 'N' COMMENT '预留字段2',
  `attr03` varchar(256) DEFAULT 'N' COMMENT '预留字段3',
  `create_date` timestamp COMMENT '创建时间',
  `last_update` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `last_modifyer` varchar(64) DEFAULT 'N' COMMENT '记录最后修改人 关联b_user.fid',
  `creater` varchar(64) DEFAULT 'N' COMMENT '记录创建人 关联b_user.fid',
  PRIMARY KEY (`fid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='包装详表';

CREATE TABLE `f_goods_package_item` (
  `fid` bigint(20) NOT NULL COMMENT '主键id',
  `package_id` bigint(20) NOT NULL COMMENT '包装ID s_goods_package.fid',
  `package_detail_id` bigint(20) NOT NULL COMMENT '包装ID s_goods_package.fid',
  `item_number` varchar(64) DEFAULT 'N' COMMENT '商品款号',
  `barcode` varchar(64) DEFAULT 'N' COMMENT '商品条码',
  `epc` varchar(64) DEFAULT 'N' COMMENT 'epc值',
  `epc_status` int(8) DEFAULT '1' COMMENT '状态 1-未收货 2-已收货 3-差异收货 4-一键收货 5-零入库收货',
  `remark_status` int(8) DEFAULT NULL COMMENT '状态',
  `remark` varchar(256) DEFAULT 'N' COMMENT '备注',
  `attr01` varchar(256) DEFAULT 'N' COMMENT '预留字段1',
  `attr02` varchar(256) DEFAULT 'N' COMMENT '预留字段2',
  `attr03` varchar(256) DEFAULT 'N' COMMENT '预留字段3',
  `create_date` timestamp COMMENT '创建时间',
  `last_update` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `last_modifyer` varchar(64) DEFAULT 'N' COMMENT '记录最后修改人 关联b_user.fid',
  `creater` varchar(64) DEFAULT 'N' COMMENT '记录创建人 关联b_user.fid',
  PRIMARY KEY (`fid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='包装明细表';

