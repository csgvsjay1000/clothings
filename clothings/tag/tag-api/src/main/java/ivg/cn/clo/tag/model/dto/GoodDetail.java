package ivg.cn.clo.tag.model.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter
@Setter
public class GoodDetail implements Serializable{
	private String itemNumber;

    private String barcode;
    
    private String name;

    private String color;

    private String size;
    
    private List<String> epcs;
}
