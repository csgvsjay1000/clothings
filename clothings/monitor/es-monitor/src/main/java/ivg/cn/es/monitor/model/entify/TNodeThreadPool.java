package ivg.cn.es.monitor.model.entify;

import java.util.Date;

public class TNodeThreadPool {
    private Long fid;

    private String ip;

    private Integer writeQueue;

    private Integer writeTotalRejected;

    private Integer writeRejectedOneDay;

    private Integer searchQueue;

    private Integer searchTotalRejected;

    private Integer searchRejectedOneDay;

    private Integer getQueue;

    private Integer getTotalRejected;

    private Integer getRejectedOneDay;

    private Date createDate;

    public Long getFid() {
        return fid;
    }

    public void setFid(Long fid) {
        this.fid = fid;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getWriteQueue() {
        return writeQueue;
    }

    public void setWriteQueue(Integer writeQueue) {
        this.writeQueue = writeQueue;
    }

    public Integer getWriteTotalRejected() {
        return writeTotalRejected;
    }

    public void setWriteTotalRejected(Integer writeTotalRejected) {
        this.writeTotalRejected = writeTotalRejected;
    }

    public Integer getWriteRejectedOneDay() {
        return writeRejectedOneDay;
    }

    public void setWriteRejectedOneDay(Integer writeRejectedOneDay) {
        this.writeRejectedOneDay = writeRejectedOneDay;
    }

    public Integer getSearchQueue() {
        return searchQueue;
    }

    public void setSearchQueue(Integer searchQueue) {
        this.searchQueue = searchQueue;
    }

    public Integer getSearchTotalRejected() {
        return searchTotalRejected;
    }

    public void setSearchTotalRejected(Integer searchTotalRejected) {
        this.searchTotalRejected = searchTotalRejected;
    }

    public Integer getSearchRejectedOneDay() {
        return searchRejectedOneDay;
    }

    public void setSearchRejectedOneDay(Integer searchRejectedOneDay) {
        this.searchRejectedOneDay = searchRejectedOneDay;
    }

    public Integer getGetQueue() {
        return getQueue;
    }

    public void setGetQueue(Integer getQueue) {
        this.getQueue = getQueue;
    }

    public Integer getGetTotalRejected() {
        return getTotalRejected;
    }

    public void setGetTotalRejected(Integer getTotalRejected) {
        this.getTotalRejected = getTotalRejected;
    }

    public Integer getGetRejectedOneDay() {
        return getRejectedOneDay;
    }

    public void setGetRejectedOneDay(Integer getRejectedOneDay) {
        this.getRejectedOneDay = getRejectedOneDay;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}