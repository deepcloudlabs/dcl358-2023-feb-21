package com.example.application;

import com.example.service.PreciousBusinessService;

public class SyncBusinessApplication {
	public static void main(String[] args) {
		System.err.println("Application is just started.");
		var businessService = new PreciousBusinessService();
		var result = businessService.doSyncBusiness();
		System.err.println(result);
		System.err.println("Application is completed.");
	}
}
