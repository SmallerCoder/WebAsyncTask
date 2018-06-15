package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.WebAsyncTask;

/**
 * 
 * The class GetUserInfoController.
 *
 * Description:用户控制器
 *
 * @author: huangjiawei
 * @since: 2018年6月14日
 * @version: $Revision$ $Date$ $LastChangedBy$
 *
 */
@RestController
public class GetUserInfoController {

	@Autowired
	private ThreadPoolTaskExecutor executor;

	/**
	 * 测试没有发生任何异常的异步任务
	 * @return
	 */
	@RequestMapping(value = "getUserWithNoThing.json", method = RequestMethod.GET)
	public WebAsyncTask<String> getUserWithNoThing() {
		System.err.println("The main Thread name is " + Thread.currentThread().getName());

		// 此处模拟开启一个异步任务
		WebAsyncTask<String> task1 = new WebAsyncTask<String>(10 * 1000L, () -> {
			System.err.println("The first Thread name is " + Thread.currentThread().getName());
			Thread.sleep(5 * 1000L);
			return "任务1顺利执行成功！任何异常都没有抛出！";
		});

		// 任务执行完成时调用该方法
		task1.onCompletion(() -> {
			System.err.println("任务1执行完成啦！");
		});

		System.err.println("task1继续处理其他事情！");
		return task1;
	}

	/**
	 * 测试发生任务超时的异步任务
	 * @return
	 */
	@RequestMapping(value = "getUserWithTimeOut.json", method = RequestMethod.GET)
	public WebAsyncTask<String> getUserWithTimeOut() {
		System.err.println("The main Thread name is " + Thread.currentThread().getName());

		// 此处模拟开启一个异步任务
		WebAsyncTask<String> task2 = new WebAsyncTask<String>(10 * 1000L, () -> {
			System.err.println("The second Thread name is " + Thread.currentThread().getName());
			Thread.sleep(20 * 1000L);
			return "任务2执行超时！";
		});

		// 任务超时调用该方法
		task2.onTimeout(() -> {
			System.err.println("====================================" + Thread.currentThread().getName()
					+ "==============================");
			return "任务2发生超时啦！";
		});

		// 任务执行完成时调用该方法
		task2.onCompletion(() -> {
			System.err.println("任务2执行完成啦！");
		});

		System.err.println("task2继续处理其他事情！");
		return task2;
	}

	/**
	 * 测试发生error的异步任务
	 * @return
	 */
	@RequestMapping(value = "getUserWithError.json", method = RequestMethod.GET)
	public WebAsyncTask<String> getUserWithError() {
		System.err.println("The main Thread name is " + Thread.currentThread().getName());

		// 此处模拟开启一个异步任务
		WebAsyncTask<String> task3 = new WebAsyncTask<String>(10 * 1000L, () -> {
			System.err.println("The second Thread name is " + Thread.currentThread().getName());
			int num = 9 / 0;
			System.err.println(num);
			return "";
		});

		// 发生异常时调用该方法
		task3.onError(() -> {
			System.err.println("====================================" + Thread.currentThread().getName()
					+ "==============================");
			System.err.println("任务3发生error啦！");
			return "";
		});
		// 任务执行完成时调用该方法
		task3.onCompletion(() -> {
			System.err.println("任务3执行完成啦！");
		});

		System.err.println("task3继续处理其他事情！");
		return task3;
	}

	/**
	 * 测试线程池
	 * @return
	 */
	@RequestMapping(value = "getUserWithExecutor.json", method = RequestMethod.GET)
	public WebAsyncTask<String> getUserWithExecutor() {
		System.err.println("The main Thread name is " + Thread.currentThread().getName());

		// 此处模拟开启一个异步任务
		WebAsyncTask<String> task1 = new WebAsyncTask<String>(10 * 1000L, executor, () -> {
			System.err.println("The first Thread name is " + Thread.currentThread().getName());
			Thread.sleep(5000L);
			return "任务4顺利执行成功！任何异常都没有抛出！";
		});

		// 任务执行完成时调用该方法
		task1.onCompletion(() -> {
			System.err.println("任务4执行完成啦！");
		});

		System.err.println("task4继续处理其他事情！");
		return task1;
	}
}
