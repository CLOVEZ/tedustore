package cn.tedu.store.service.impl;

import cn.tedu.store.service.ex.ServiceException;

/**
 * 订单数据不存在
 */
public class OrderNotFoundException extends ServiceException {

	private static final long serialVersionUID = 5002673989248771650L;

	public OrderNotFoundException() {
		super();
	}

	public OrderNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public OrderNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public OrderNotFoundException(String message) {
		super(message);
	}

	public OrderNotFoundException(Throwable cause) {
		super(cause);
	}

}
