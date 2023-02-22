package com.example.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

import com.example.dto.TickerDTO;

@Service
@SuppressWarnings("deprecation")
public class Exercise01 {

	@Value("${binanceUrl}")
	private String binanceUrl;
	
	@Scheduled(fixedRateString = "${period}")
	public void syncCallBinanceRestApi() {
		var restTemplate = new RestTemplate();
		var ticker = restTemplate.getForEntity(binanceUrl, TickerDTO.class).getBody();
		System.out.println("[RestTemplate] %s".formatted(ticker));
	}
	
	@Scheduled(fixedRateString = "${period}")
	public void asyncCallBinanceRestApi() {
		var restTemplate = new AsyncRestTemplate();
		restTemplate.getForEntity(binanceUrl, TickerDTO.class)
		            .addCallback(
		               response -> System.out.println("[AsyncRestTemplate] %s".formatted(response.getBody())),
		               error -> System.err.println(error)
		            );             
		
	}
}
