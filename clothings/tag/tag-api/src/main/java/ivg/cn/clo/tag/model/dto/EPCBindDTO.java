package ivg.cn.clo.tag.model.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**  EPC绑定DTO */
@SuppressWarnings("serial")
@Getter
@Setter
public class EPCBindDTO implements Serializable{
	
	private String locationNum;  // 位置
	
	private String user;  // 操作人
	
	private List<GoodDetail> details;
}
