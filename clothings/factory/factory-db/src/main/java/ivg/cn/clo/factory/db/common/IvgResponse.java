package ivg.cn.clo.factory.db.common;


public class IvgResponse<T> {
    public static final Integer OK = 200;
    public static final Integer ERROR = 1;

    private Integer code;

    private String msg;

    private T data;

    private int total;  // 总记录数

    /**
     * json数据
     */
    private Object extData;

    private static <T> IvgResponse<T> createResponse(Integer code) {
        IvgResponse<T> response = new IvgResponse<>();
        response.setCode(code);
        return response;
    }

    private static <T> IvgResponse<T> createResponse(Integer code, String message) {
        IvgResponse<T> response = new IvgResponse<>();
        response.setCode(code);
        response.setMsg(message);
        return response;
    }

    public static <T> IvgResponse<T> createResponse(Integer code, T data, int total, String message) {
        IvgResponse<T> response = new IvgResponse<>();
        response.setCode(code);
        response.setData(data);
        response.setTotal(total);
        response.setMsg(message);
        return response;
    }

    public static <T> IvgResponse<T> createResponse(Integer code, T data, int total, Object extData, String message) {
        IvgResponse<T> response = new IvgResponse<>();
        response.setCode(code);
        response.setData(data);
        response.setTotal(total);
        response.setExtData(extData);
        response.setMsg(message);
        return response;
    }


    public static <T> IvgResponse<T> createOKResponse() {
        return createResponse(OK);
    }

    public static <T> IvgResponse<T> createOKResponse(T data, String message) {
        return createResponse(OK, data, 0, message);
    }

    public static <T> IvgResponse<T> createOKResponse(T data, int total, String message) {
        return createResponse(OK, data, total, message);
    }

    public static <T> IvgResponse<T> createOKResponse(T data, Object extData, String message) {
        return createResponse(OK, data, 0, extData, message);
    }

    public static <T> IvgResponse<T> createErrorResponse() {
        return createResponse(ERROR, "服务器内部错误");
    }

    public static <T> IvgResponse<T> createErrorResponse(T data, String message) {
        return createResponse(ERROR, data, 0, message);
    }

    public static <T> IvgResponse<T> createErrorResponse(Integer code, String message) {
        return createResponse(ERROR, message);
    }

    public static <T> IvgResponse<T> createErrorWithCodeResponse(Integer code, String message) {
        return createResponse(code, message);
    }

    public boolean success() {
        return code == OK;
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Object getExtData() {
        return extData;
    }

    public void setExtData(Object extData) {
        this.extData = extData;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public IvgResponse<T> buildExtData(Object extData){
        this.extData= extData;
        return this;
    }
    public boolean isSuccess(){
        return OK.equals(this.code);
    }
}
