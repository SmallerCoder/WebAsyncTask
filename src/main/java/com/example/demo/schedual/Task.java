package com.example.demo.schedual;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 
 * The class Task.
 *
 * Description:要有以下两个保证
 * 
 * 1、异步任务类定义和定时任务定义不能在同一个类中
 * 2、异步任务类不能采用new的方式创建，只能从spring容器中获取
 *
 * @author: huangjiawei
 * @since: 2018年6月15日
 * @version: $Revision$ $Date$ $LastChangedBy$
 *
 */
@Component
public class Task {

	@Autowired
	private AsyncSchedualTask asyncTask;

	/**
	 * 每两秒执行一次
	 * @throws InterruptedException
	 */
	@Scheduled(cron = "0/2 * * * * ?")
	public void say() throws InterruptedException {
		System.err.println(Thread.currentThread().getName() + "线程执行");
		System.err.println("say");
		Thread.sleep(2000L);
		asyncTask.async();
	}

}
