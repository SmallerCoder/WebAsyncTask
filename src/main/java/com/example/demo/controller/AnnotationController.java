package com.example.demo.controller;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.annotationTask.tasks.MyTasks;

/**
 * 
 * The class AnnotationController.
 *
 * Description:基于注解的异步控制器
 *
 * @author: huangjiawei
 * @since: 2018年6月15日
 * @version: $Revision$ $Date$ $LastChangedBy$
 *
 */
@RestController
public class AnnotationController {

	@Autowired
	private MyTasks myTask;

	/**
	 * 测试没有返回值的异步任务（注解）
	 * @return
	 */
	@RequestMapping(value = "getUserInfoAnnotationWithoutReturn.json", method = RequestMethod.GET)
	public String getUserInfoAnnotationWithoutReturn() {
		System.err.println("当前处理线程名为 ： " + Thread.currentThread().getName());
		try {
			myTask.getUserInfoAnnotationWithoutReturn();
		} catch (InterruptedException e) {}
		return "success";
	}

	/**
	 * 测试有返回值的异步任务（注解）
	 * @return
	 */
	@RequestMapping(value = "getUserInfoAnnotationWithString.json", method = RequestMethod.GET)
	public String getUserInfoAnnotationWithString() {
		System.err.println("当前处理线程名为 ： " + Thread.currentThread().getName());
		Future<String> futureResult = null;
		String returnResult = "";
		try {
			futureResult = myTask.getUserInfoAnnotationWithString();
			returnResult = futureResult.get(2L, TimeUnit.SECONDS);
		} catch (Exception e) {
			return "处理超时";
		}
		return returnResult;
	}

	/**
	 * 测试有异常的异步任务
	 * @return
	 * @throws InterruptedException 
	 */
	@RequestMapping(value = "getUserInfoAnnotationWithException.json", method = RequestMethod.GET)
	public String getUserInfoAnnotationWithException() throws InterruptedException {
		System.err.println("当前处理线程名为 ： " + Thread.currentThread().getName());
		myTask.getUserInfoAnnotationWithException();
		return "";
	}
}
