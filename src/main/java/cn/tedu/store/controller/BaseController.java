package cn.tedu.store.controller;

import cn.tedu.store.service.ex.*;
import cn.tedu.store.util.ResponseResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

/**
 * 控制器类的基类
 */
public abstract class BaseController {
	
	/**
	 * 响应结果的状态：成功
	 */
	public static final Integer SUCCESS = 200;
	
	/**
	 * 从Session获取当前登录的用户id
	 * @param session HttpSession对象
	 * @return 当前登录的用户id
	 */
	protected final Integer getUidFromSession(HttpSession session) {
		return Integer.valueOf(session.getAttribute("uid").toString());
	}

	/**
	 * 统一处理异常
	 * @param e
	 * @return
	 */
	@ExceptionHandler(ServiceException.class)
	public ResponseResult<Void> handleException(Throwable e) {
		ResponseResult<Void> rr
			= new ResponseResult<Void>();
		rr.setMessage(e.getMessage());
		
		if (e instanceof UsernameDuplicateException) {
			// 400-用户名冲突
			rr.setState(400);
		} else if (e instanceof UserNotFoundException) {
			// 401-用户数据不存在
			rr.setState(401);
		} else if (e instanceof PasswordNotMatchException) {
			// 402-验证密码失败
			rr.setState(402);
		} else if (e instanceof InsertException) {
			// 500-插入数据异常
			rr.setState(500);
		} else if (e instanceof UpdateException) {
			// 501-插入数据异常
			rr.setState(501);
		}
		
		return rr;
	}
	
}
