package com.example;

import java.util.List;
import java.util.concurrent.Flow;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.SubmissionPublisher;

public class StudyReactiveProgramming {
	public static void main(String[] args) {
		System.err.println("Application is just started.");		
		var events = List.of(
				new TradeEvent("orcl", 100, 20), 
				new TradeEvent("msft", 101, 10),
				new TradeEvent("ibm", 102, 30), 
				new TradeEvent("apple", 103, 50), 
				new TradeEvent("orcl", 104, 40)
		);
		var publisher = new SubmissionPublisher<TradeEvent>();
		publisher.subscribe(new SlowSubscriber());
		publisher.subscribe(new FastSubscriber());
		events.forEach(publisher::submit);
		System.err.println("Application is completed.");
		try {TimeUnit.SECONDS.sleep(30);} catch (Exception e) {}
		publisher.close();
	}
}

class FastSubscriber implements Flow.Subscriber<TradeEvent> {

	private Subscription subscription;

	@Override
	public void onSubscribe(Subscription subscription) {
		System.err.println("FastSubscriber has subscribed to the publisher!");
		this.subscription = subscription;
		this.subscription.request(1);
	}

	@Override
	public void onNext(TradeEvent event) {
		System.err.println("FastSubscriber has processed the event [%s]!".formatted(event));
		this.subscription.request(1);
	}

	@Override
	public void onError(Throwable t) {
		System.err.println("FastSubscriber has failed while receiving the event: %s".formatted(t.getMessage()));
	}

	@Override
	public void onComplete() {
		System.err.println("FastSubscriber has been completed!");
	}
	
}

class SlowSubscriber implements Flow.Subscriber<TradeEvent> {
	private Subscription subscription;
	
	@Override
	public void onSubscribe(Subscription subscription) {
		System.err.println("SlowSubscriber has subscribed to the publisher!");
		this.subscription = subscription;
		this.subscription.request(1);
	}
	
	@Override
	public void onNext(TradeEvent event) {
		try {TimeUnit.SECONDS.sleep(5);} catch (Exception e) {}
		this.subscription.request(1);
		System.err.println("SlowSubscriber has processed the event [%s]!".formatted(event));
	}
	
	@Override
	public void onError(Throwable t) {
		System.err.println("SlowSubscriber has failed while receiving the event: %s".formatted(t.getMessage()));
	}
	
	@Override
	public void onComplete() {
		System.err.println("SlowSubscriber has been completed!");
	}
	
}