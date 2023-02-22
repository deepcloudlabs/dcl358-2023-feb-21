package com.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
// {"e":"trade","E":1677050719269,"s":"BTCUSDT","t":2770414933,"p":"24053.18000000","q":"0.00137000","b":19033982145,"a":19033982649,"T":1677050719268,"m":true,"M":true}
@Data
public class TradeEventDTO {
	@JsonProperty("s")
	private String symbol;
	@JsonProperty("p")
	private String price;
	@JsonProperty("q")
	private String quantity;
	@JsonProperty("b")
	private long bid;
	@JsonProperty("a")
	private long ask;
}
