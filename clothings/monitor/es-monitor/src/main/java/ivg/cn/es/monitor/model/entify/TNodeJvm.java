package ivg.cn.es.monitor.model.entify;

import java.util.Date;

public class TNodeJvm {
    private Long fid;

    private String ip;

    private Long heapUsedInBytes;

    private Integer heapUsedPercent;

    private Long heapCommittedInBytes;

    private Long heapMaxInBytes;

    private Integer threadsCount;

    private Long youngCollectionCount;

    private Integer youngAvgCollectionTimeInMillis;

    private Integer youngCollectionFrequency;

    private Long oldCollectionCount;

    private Integer oldAvgCollectionTimeInMillis;

    private Integer oldCollectionFrequency;

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

    public Long getHeapUsedInBytes() {
        return heapUsedInBytes;
    }

    public void setHeapUsedInBytes(Long heapUsedInBytes) {
        this.heapUsedInBytes = heapUsedInBytes;
    }

    public Integer getHeapUsedPercent() {
        return heapUsedPercent;
    }

    public void setHeapUsedPercent(Integer heapUsedPercent) {
        this.heapUsedPercent = heapUsedPercent;
    }

    public Long getHeapCommittedInBytes() {
        return heapCommittedInBytes;
    }

    public void setHeapCommittedInBytes(Long heapCommittedInBytes) {
        this.heapCommittedInBytes = heapCommittedInBytes;
    }

    public Long getHeapMaxInBytes() {
        return heapMaxInBytes;
    }

    public void setHeapMaxInBytes(Long heapMaxInBytes) {
        this.heapMaxInBytes = heapMaxInBytes;
    }

    public Integer getThreadsCount() {
        return threadsCount;
    }

    public void setThreadsCount(Integer threadsCount) {
        this.threadsCount = threadsCount;
    }

    public Long getYoungCollectionCount() {
        return youngCollectionCount;
    }

    public void setYoungCollectionCount(Long youngCollectionCount) {
        this.youngCollectionCount = youngCollectionCount;
    }

    public Integer getYoungAvgCollectionTimeInMillis() {
        return youngAvgCollectionTimeInMillis;
    }

    public void setYoungAvgCollectionTimeInMillis(Integer youngAvgCollectionTimeInMillis) {
        this.youngAvgCollectionTimeInMillis = youngAvgCollectionTimeInMillis;
    }

    public Integer getYoungCollectionFrequency() {
        return youngCollectionFrequency;
    }

    public void setYoungCollectionFrequency(Integer youngCollectionFrequency) {
        this.youngCollectionFrequency = youngCollectionFrequency;
    }

    public Long getOldCollectionCount() {
        return oldCollectionCount;
    }

    public void setOldCollectionCount(Long oldCollectionCount) {
        this.oldCollectionCount = oldCollectionCount;
    }

    public Integer getOldAvgCollectionTimeInMillis() {
        return oldAvgCollectionTimeInMillis;
    }

    public void setOldAvgCollectionTimeInMillis(Integer oldAvgCollectionTimeInMillis) {
        this.oldAvgCollectionTimeInMillis = oldAvgCollectionTimeInMillis;
    }

    public Integer getOldCollectionFrequency() {
        return oldCollectionFrequency;
    }

    public void setOldCollectionFrequency(Integer oldCollectionFrequency) {
        this.oldCollectionFrequency = oldCollectionFrequency;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}