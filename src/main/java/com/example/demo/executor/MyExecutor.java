package com.example.demo.executor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class MyExecutor {

	@Bean
	public static ThreadPoolTaskExecutor getExecutor() {
		ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
		taskExecutor.setCorePoolSize(30);
		taskExecutor.setMaxPoolSize(30);
		taskExecutor.setQueueCapacity(50);
		taskExecutor.setThreadNamePrefix("huang");
		return taskExecutor;
	}

	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(40);
		for (int i = 0; i < 100; i++) {
			executor.execute(() -> {
				http();
			});
		}
	}

	public static void http() {
		try {
			//TODO: 准备uri
			URI uri = new URI("http://localhost:7000/demo/getUserInfoAnnotationWithoutReturn.json");

			//TODO: new一个HTTP工厂
			SimpleClientHttpRequestFactory schr = new SimpleClientHttpRequestFactory();

			//TODO: 创建HTTP请求
			ClientHttpRequest chr = schr.createRequest(uri, HttpMethod.GET);

			//TODO：设置body
			//chr.getBody().write(param.getBytes());

			//TODO: 执行HTTP请求
			ClientHttpResponse res = chr.execute();

			//TODO: 获取返回数据流
			InputStream is = res.getBody();

			//TODO: 从流中读取数据
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String str = "";
			while ((str = br.readLine()) != null) {
				System.out.println(str);
			}

		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
