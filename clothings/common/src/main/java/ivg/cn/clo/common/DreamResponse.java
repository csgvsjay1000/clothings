package ivg.cn.clo.common;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


/**
 * @ClassName: DreamResponse
 * @Description:TODO(通用返回类)
 * @author: wwj
 * @date: 2018年8月1日 下午3:21:05
 */
@SuppressWarnings({"rawtypes", "unchecked", "serial"})
public class DreamResponse<T> implements Serializable {

    private String status = DreamStatus.FAIL;// 响应状态,默认失败

    private Integer code;// 响应代码

    private String msg;// 响应消息

    private int optRzt;

    private Map<String, Object> extData;

    private int total = 0;

    private List<T> data;

    private T objData;

    public DreamResponse() {
        // TODO Auto-generated constructor stub
    }
    
    public static <T> DreamResponse createResponse(int total, List<T> data, String msg) {
        DreamResponse response = new DreamResponse();
        response.setTotal(total);
        response.setData(data);
        response.setCode(DreamStatus.CodeStatus.OK);
        response.setMsg(msg);
        return response;
    }

    @SuppressWarnings("unused")
    private static <T> DreamResponse<T> createResponse(Integer code) {
        DreamResponse<T> response = new DreamResponse<T>();
        response.setCode(code);
        return response;
    }

    public static <T> DreamResponse<T> createResponse(Integer code, String message) {
        DreamResponse response = new DreamResponse();
        response.setCode(code);
        response.setMsg(message);
        return response;
    }

    public static <T> DreamResponse createResponse(String status, Integer code, String msg) {
        return createResponse(code, msg).buildStatus(status);
    }

    public static <T> DreamResponse<T> createOKResponse() {
        return createResponse(DreamStatus.SUCCESS, DreamStatus.CodeStatus.OK, DreamStatus.CodeStatus.SUCCESS_MESSAGE);
    }

    public static <T> DreamResponse<T> createOKResponse(String message) {
        return createResponse(DreamStatus.SUCCESS, DreamStatus.CodeStatus.OK, message);
    }

    public static <T> DreamResponse<T> createErrorResponse() {
        return createResponse(DreamStatus.FAIL, DreamStatus.CodeStatus.ERROR, DreamStatus.CodeStatus.ERROR_MESSAGE);
    }

    public static <T> DreamResponse<T> createErrorResponse(String message) {
        return createResponse(DreamStatus.FAIL, DreamStatus.CodeStatus.ERROR, message);
    }

    public static <T> DreamResponse<T> createErrorResponse(Integer code, String message) {
        return createResponse(DreamStatus.FAIL, code, message);
    }

    public static <T> DreamResponse<T> createSystemErrorResponse() {
        return createResponse(DreamStatus.FAIL, DreamStatus.CodeStatus.INTERNAL_SERVER_ERROR, DreamStatus.CodeStatus.INTERNAL_SERVER_ERROR_MESSAGE);
    }

    public static <T> DreamResponse<T> createSystemErrorResponse(String message) {
        return createResponse(DreamStatus.FAIL, DreamStatus.CodeStatus.INTERNAL_SERVER_ERROR, message);
    }
    
    public DreamResponse<T> buildMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public DreamResponse<T> buildStatus(String status) {
        this.status = status;
        return this;
    }

    public DreamResponse<T> buildCode(Integer code) {
        this.code = code;
        return this;
    }
    
    public DreamResponse<T> buildData(List data) {
    	this.data = data;
    	return this;
    }
    
    public DreamResponse<T> buildExtData(Map<String,Object> extData) {
    	this.extData = extData;
    	return this;
    }
    
    public DreamResponse<T> buildObjData(T objData) {
    	this.objData = objData;
    	return this;
    }

    public T getObjData() {
        return objData;
    }

    public void setObjData(T objData) {
        this.objData = objData;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getExtData() {
        return extData;
    }

    public void setExtData(Map<String, Object> extData) {
        this.extData = extData;
    }

    public int getOptRzt() {
        return optRzt;
    }

    public void setOptRzt(int optRzt) {
        this.optRzt = optRzt;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public boolean hasSucceeded() {
        return this.code.equals(DreamStatus.CodeStatus.OK);
    }

    public boolean hasFailure() {
        return !hasSucceeded();
    }

}
