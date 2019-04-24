package ivg.cn.clo.factory.model.dto;

import java.util.List;

/**  箱详情 */
public class FPackDetail {

    private String itemNumber; 

    private String barcode;

    private String firstassattr;  // 颜色

    private String secondassattr;  // 尺寸

    private Integer goodsQty;

    List<String> epcs;

	public String getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(String itemNumber) {
		this.itemNumber = itemNumber;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getFirstassattr() {
		return firstassattr;
	}

	public void setFirstassattr(String firstassattr) {
		this.firstassattr = firstassattr;
	}

	public String getSecondassattr() {
		return secondassattr;
	}

	public void setSecondassattr(String secondassattr) {
		this.secondassattr = secondassattr;
	}

	public Integer getGoodsQty() {
		return goodsQty;
	}

	public void setGoodsQty(Integer goodsQty) {
		this.goodsQty = goodsQty;
	}

	public List<String> getEpcs() {
		return epcs;
	}

	public void setEpcs(List<String> epcs) {
		this.epcs = epcs;
	}
	
}
