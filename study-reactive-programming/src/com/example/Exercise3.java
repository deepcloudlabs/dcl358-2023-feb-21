package com.example;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;

public class Exercise3 {
	private static final String BINANCE_WS_API_URL = 
			"wss://stream.binance.com:9443/ws/btcusdt@trade";
	public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
		HttpClient.newHttpClient()
		          .newWebSocketBuilder()
		          .buildAsync(URI.create(BINANCE_WS_API_URL), new BinanceWebSocketListener());            
		try {TimeUnit.SECONDS.sleep(30);} catch (Exception e) {}
	}

}

class BinanceWebSocketListener implements WebSocket.Listener {

	@Override
	public void onOpen(WebSocket webSocket) {
		System.err.println("Successfuly connected to the Binance server.");
		webSocket.request(1);
	}

	@Override
	public CompletionStage<?> onText(WebSocket webSocket, CharSequence data, boolean last) {
		System.err.println(data);
		webSocket.request(1);
		return null;
	}
	
}