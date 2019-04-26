package ivg.cn.clo.factory.model.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**  箱详情 */
@SuppressWarnings("serial")
@Getter 
@Setter
public class FPackDetail implements Serializable{

	private String itemNumber; 

    private String barcode;

    private String firstassattr;  // 颜色

    private String secondassattr;  // 尺寸

    private Integer goodsQty;

    List<String> epcs;

	
}
