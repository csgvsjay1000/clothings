package ivg.cn.clo.factory.model.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**  推送采购订单DTO */
@SuppressWarnings("serial")
@Getter 
@Setter
public class FGoodsPurchaseOrderDTO implements Serializable{

	private String num;  

    private String itemNumber;

    private String fromLocationId;  // 发货方

    private String toLocationId;  // 收货方

    private Integer bookQty;  // 采购商品总数量
    
    List<OrderDetail> details;  // 每款商品数
    
    @Getter 
    @Setter
    public static class OrderDetail implements Serializable{
		
		private String barcode;

	    private Integer bookQty;
	    
	}
}
