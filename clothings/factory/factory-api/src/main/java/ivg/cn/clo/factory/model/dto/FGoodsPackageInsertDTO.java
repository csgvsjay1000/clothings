package ivg.cn.clo.factory.model.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/** 装箱DTO */
@Getter 
@Setter
public class FGoodsPackageInsertDTO {

	private String fromLocationNum;  // 发货方，一般为工厂

    private String toLocationNum;  // 收货方，一般值为仓库
    
    private String purchaseOrderNum;  // 采购订单，可为空
    
    private String num;  //箱号
    
    private String itemNumber;  //款号

    private String mixtureRatioKey;  // 配比key

    private String mixtureRatioValue;
    
    List<FPackDetail> packs;  // 箱详情

}
