package ivg.cn.es.monitor;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BTag {
    private Long fid;

    private String epc;

    private String locationNum;

    private String fstatus;

    private String itemNumber;

    private String barcode;
    
    private String name;

    private String color;

    private String size;

    private String remark;

    private String attr01;

    private String attr02;

    private String attr03;

    private Date createDate;

    private Date lastUpdate;

    private String lastModifyer;

    private String creater;

}