package com.example.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.WebSocketClient;

import com.example.dto.TradeEventDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class Exercise02 implements WebSocketHandler {
	private final WebSocketClient webSocketClient;
	private final String binanceWssUrl;
	private final ObjectMapper objectMapper;
	
	public Exercise02(WebSocketClient webSocketClient,
			ObjectMapper objectMapper,
			@Value("${binanceWssUrl}") String binanceWssUrl) {
		this.webSocketClient = webSocketClient;
		this.binanceWssUrl = binanceWssUrl;
		this.objectMapper = objectMapper;
	}

	@PostConstruct
	public void connect() {
		webSocketClient.doHandshake(this, binanceWssUrl);
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.err.println("Successfully connected to the binance websocket server.");
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		var tradeEvent = objectMapper.readValue(message.getPayload().toString(), TradeEventDTO.class);
		System.err.println(tradeEvent);
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable e) throws Exception {
		System.err.println("An error has occurred: %s".formatted(e.getMessage()));
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		System.err.println("Disconnected from the binance websocket server.");
	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}
}
