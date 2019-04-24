package ivg.cn.clo.common;

public interface DreamStatus {
	String SUCCESS = "SUCCESS";

	String FAIL = "FAIL";
	
	interface CodeStatus {

		String NORMALCODE = "200";

		String UNEXITCODE = "201";

		String MESSAGE = "操作成功";

		String SUCCESS_MESSAGE = "操作成功";

		String ERROR_MESSAGE = "操作失败";

		String INTERNAL_SERVER_ERROR_MESSAGE = "内部服务异常";

		Integer ERROR = 100;// 业务逻辑错误

		Integer OK = 200;// 请求成功

		Integer INTERNAL_SERVER_ERROR = 500;// 内部服务错误

		/** 业务码 */
		Integer EXIST_CODE = 10000;// 用户已存在

		Integer NOT_EXIST_CODE = 10001;// 不存在

		Integer MISSING_PARAMS = 10002;// 参数不完整

		Integer USERNAME_PWD_ERROR = 10003;// 用户名或密码错误

		Integer NOT_LOGIN = 10004;// 用户未登录

		Integer NOT_PERMISSION = 10005;// 用户权限不足
	}
}
