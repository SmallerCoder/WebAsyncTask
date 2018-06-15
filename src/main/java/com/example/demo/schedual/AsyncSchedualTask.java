package com.example.demo.schedual;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 
 * The class AsyncSchedualTask.
 *
 * Description:基于Spring定时任务的异步任务调用，也就是说测试在定时任务中调用地步方法，看看能不能异步执行
 *
 * @author: huangjiawei
 * @since: 2018年6月15日
 * @version: $Revision$ $Date$ $LastChangedBy$
 *
 */
@Component
public class AsyncSchedualTask {

	@Async
	public void async() {

		System.err.println(Thread.currentThread().getName() + "线程执行");
		System.err.println("async");
	}

}
