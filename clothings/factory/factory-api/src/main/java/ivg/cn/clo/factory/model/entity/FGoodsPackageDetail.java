package ivg.cn.clo.factory.model.entity;

import java.util.Date;

public class FGoodsPackageDetail {
    private Long fid;

    private Long packageId;

    private String itemNumber;

    private String barcode;

    private String firstassattr;

    private String secondassattr;

    private Integer goodsQty;

    private Integer acceptanceQty;

    private String remark;

    private String attr01;

    private String attr02;

    private String attr03;

    private Date createDate;

    private Date lastUpdate;

    private String lastModifyer;

    private String creater;

    public Long getFid() {
        return fid;
    }

    public void setFid(Long fid) {
        this.fid = fid;
    }

    public Long getPackageId() {
        return packageId;
    }

    public void setPackageId(Long packageId) {
        this.packageId = packageId;
    }

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

    public Integer getAcceptanceQty() {
        return acceptanceQty;
    }

    public void setAcceptanceQty(Integer acceptanceQty) {
        this.acceptanceQty = acceptanceQty;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAttr01() {
        return attr01;
    }

    public void setAttr01(String attr01) {
        this.attr01 = attr01;
    }

    public String getAttr02() {
        return attr02;
    }

    public void setAttr02(String attr02) {
        this.attr02 = attr02;
    }

    public String getAttr03() {
        return attr03;
    }

    public void setAttr03(String attr03) {
        this.attr03 = attr03;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getLastModifyer() {
        return lastModifyer;
    }

    public void setLastModifyer(String lastModifyer) {
        this.lastModifyer = lastModifyer;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }
}