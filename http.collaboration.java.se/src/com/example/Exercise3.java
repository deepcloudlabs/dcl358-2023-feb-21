package com.example;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;

public class Exercise3 {
	private static final String BINANCE_URL = "wss://stream.binance.com:9443/ws/btcusdt@trade";
	public static void main(String[] args) throws Exception {
		var listener = new BinanceWebsocketListener();
		HttpClient.newHttpClient()
				  .newWebSocketBuilder()
				  .buildAsync(URI.create(BINANCE_URL), listener);
		TimeUnit.SECONDS.sleep(60);
	}

}

class BinanceWebsocketListener implements WebSocket.Listener {

	@Override
	public void onOpen(WebSocket webSocket) {
		System.err.println("Connected to the bianance websocket server.");
		webSocket.request(1);
	}

	@Override
	public CompletionStage<?> onText(WebSocket webSocket, CharSequence data, boolean last) {
		System.err.println(data);
		webSocket.request(1);
		return null;
	}

	@Override
	public CompletionStage<?> onClose(WebSocket webSocket, int statusCode, String reason) {
		System.err.println("Disconnected from the bianance websocket server.");
		return null;

	}

	@Override
	public void onError(WebSocket webSocket, Throwable error) {
		System.err.println("Error has occured: %s.".formatted(error.getMessage()));
	}
	
}
