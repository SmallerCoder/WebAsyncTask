package com.example.demo.annotationTask.tasks;

import java.util.concurrent.Future;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

@Component
public class MyTasks {

	/**
	 * 正常的异步任务处理(没有返回值)
	 * @throws InterruptedException
	 */
	@Async
	public void getUserInfoAnnotationWithoutReturn() throws InterruptedException {
		System.out.println("当前异步任务线程名为 ： " + Thread.currentThread().getName());
		System.err.println("这是一个没有任何返回值的异步方法！");
		Thread.sleep(5 * 1000L);
		System.err.println("getUserInfoAnnotationWithoutReturn方法运行结束了！");
	}

	/**
	 * 正常的异步任务处理(有返回值)
	 * @throws InterruptedException
	 */
	@Async
	public Future<String> getUserInfoAnnotationWithString() throws InterruptedException {
		System.out.println("当前异步任务线程名为 ： " + Thread.currentThread().getName());
		System.err.println("这是一个有返回值的异步方法！");
		Future<String> futureResult = new AsyncResult<String>("getUserInfoAnnotationWithString执行成功啦！");
		Thread.sleep(5 * 1000L);
		System.err.println("getUserInfoAnnotationWithString方法运行结束了！");
		return futureResult;
	}

	/**
	 * 测试异常异步任务
	 * @return
	 */
	@Async
	public String getUserInfoAnnotationWithException() throws InterruptedException {
		System.out.println("当前异步任务线程名为 ： " + Thread.currentThread().getName());
		System.err.println("这是一个有异常的异步方法！");
		int b = 10 / 0;
		Thread.sleep(5 * 1000L);
		System.err.println("getUserInfoAnnotationWithString方法运行结束了！");
		return "";
	}

}
