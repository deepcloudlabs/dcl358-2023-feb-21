package com.example.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class PreciousBusinessService {

	public List<Integer> doSyncBusiness() {
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (Exception e) {
		}
		return List.of(4, 8, 15, 16, 23, 42);
	}

	public CompletableFuture<List<Integer>> doAsyncBusiness() {
		return CompletableFuture.supplyAsync(() -> {
			System.err.println("%s runs doAsyncBusiness inside supplyAsync.".formatted(Thread.currentThread().getName()));
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (Exception e) {
			}
			return List.of(4, 8, 15, 16, 23, 42);			
		});
	}
}
