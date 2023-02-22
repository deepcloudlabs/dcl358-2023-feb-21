package com.example;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;

public class Exercise01 {
	private static final String BINANCE_REST_API_BASE_URL = 
			"https://api.binance.com/api/v3/ticker/price?symbol=%s";
	public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
		
		var httpClient = HttpClient.newHttpClient();
		var symbols = List.of("BTCUSDT", "LTCBTC", "BNBBTC", "NEOBTC", "EOSETH");
		var begin = System.currentTimeMillis();
		for (var symbol : symbols) {
			var httpRequest = HttpRequest.newBuilder(new URI(BINANCE_REST_API_BASE_URL.formatted(symbol)))
					                     .build();
			var response = httpClient.send(httpRequest, BodyHandlers.ofString());
			System.err.println(response.body());
		}
		var end = System.currentTimeMillis();
		System.err.println("Duration: %d ms.".formatted(end-begin));
	}

}
