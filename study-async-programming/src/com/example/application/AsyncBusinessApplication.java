package com.example.application;

import java.util.concurrent.TimeUnit;

import com.example.service.PreciousBusinessService;

public class AsyncBusinessApplication {
	public static void main(String[] args) {
		System.err.println("[%s] AsyncApplication is just started.".formatted(Thread.currentThread().getName()));
		var businessService = new PreciousBusinessService();
		businessService.doAsyncBusiness()
		               .thenAcceptAsync(result -> System.err.println("[%s] %s".formatted(Thread.currentThread().getName(),result)));
		System.err.println("[%s] AsyncApplication is completed.".formatted(Thread.currentThread().getName()));
		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (Exception e) {
		}
	}
}
