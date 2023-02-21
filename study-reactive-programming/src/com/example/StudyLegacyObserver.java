package com.example;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("deprecation")
public class StudyLegacyObserver {

	public static void main(String[] args) {
		System.err.println("Application is just started.");
		var events = List.of(new TradeEvent("orcl", 100, 20), new TradeEvent("msft", 101, 10),
				new TradeEvent("ibm", 102, 30), new TradeEvent("apple", 103, 50), new TradeEvent("orcl", 104, 40));
		var observable = new TradeEventObservable();
		Observer slowObserver = (o, event) -> {
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (Exception e) {
			}
			System.err.println("Event (%s) is proccessed by the slow observer.".formatted(event));
		};
		Observer fastObserver = (o, event) -> {
			System.err.println("Event (%s) is proccessed by the fast observer.".formatted(event));
		};
		observable.addObserver(slowObserver);
		observable.addObserver(fastObserver);
		events.forEach(observable::notifyObservers);
		System.err.println("Application is completed.");
	}

}

@SuppressWarnings("deprecation")
class TradeEventObservable extends Observable {

	@Override
	public void notifyObservers(Object event) {
		setChanged();
		super.notifyObservers(event);
	}

}
