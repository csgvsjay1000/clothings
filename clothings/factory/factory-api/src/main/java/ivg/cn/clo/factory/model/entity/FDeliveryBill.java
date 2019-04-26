package ivg.cn.clo.factory.model.entity;

import java.util.Date;

public class FDeliveryBill {
    private Long fid;

    private String upstreamNum;

    private Date upstreamDate;

    private String num;

    private String itemNum;

    private String billType;

    private Integer billStatus;

    private Integer allowDiff;

    private String fromLocationNum;

    private String toLocationNum;

    private Integer packageQty;

    private Integer transferQty;

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

    public String getUpstreamNum() {
        return upstreamNum;
    }

    public void setUpstreamNum(String upstreamNum) {
        this.upstreamNum = upstreamNum;
    }

    public Date getUpstreamDate() {
        return upstreamDate;
    }

    public void setUpstreamDate(Date upstreamDate) {
        this.upstreamDate = upstreamDate;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getItemNum() {
        return itemNum;
    }

    public void setItemNum(String itemNum) {
        this.itemNum = itemNum;
    }

    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }

    public Integer getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(Integer billStatus) {
        this.billStatus = billStatus;
    }

    public Integer getAllowDiff() {
        return allowDiff;
    }

    public void setAllowDiff(Integer allowDiff) {
        this.allowDiff = allowDiff;
    }

    public String getFromLocationNum() {
        return fromLocationNum;
    }

    public void setFromLocationNum(String fromLocationNum) {
        this.fromLocationNum = fromLocationNum;
    }

    public String getToLocationNum() {
        return toLocationNum;
    }

    public void setToLocationNum(String toLocationNum) {
        this.toLocationNum = toLocationNum;
    }

    public Integer getPackageQty() {
        return packageQty;
    }

    public void setPackageQty(Integer packageQty) {
        this.packageQty = packageQty;
    }

    public Integer getTransferQty() {
        return transferQty;
    }

    public void setTransferQty(Integer transferQty) {
        this.transferQty = transferQty;
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