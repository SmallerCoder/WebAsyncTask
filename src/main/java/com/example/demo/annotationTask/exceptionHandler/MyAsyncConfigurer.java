package com.example.demo.annotationTask.exceptionHandler;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.stereotype.Service;

@Service
public class MyAsyncConfigurer implements AsyncConfigurer {

	@Override
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		return new handler();
	}
}

/**
 * 
 * The class handler.
 *
 * Description:异常信息捕获
 *
 * @author: huangjiawei
 * @since: 2018年6月15日
 * @version: $Revision$ $Date$ $LastChangedBy$
 *
 */
class handler implements AsyncUncaughtExceptionHandler {

	@Override
	public void handleUncaughtException(Throwable ex, Method method, Object... params) {
		System.err.println("出现异常的方法名为：　" + method.getName());
		System.err.println("出现的参数为：" + Arrays.toString(params));
		System.err.println("抛出的异常信息为： " + ex.getMessage());
	}

}
