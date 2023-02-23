package com.example.lottery.application;

import java.util.ServiceLoader;

import com.example.lottery.service.business.StandardLotteryService;
import com.example.random.service.RandomNumberGenerator;

public class LotteryApp {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		int x=3, y=5;
		var z = x + y;
		x++;
		y++;
		// z?
		ServiceLoader<RandomNumberGenerator> randomNumberGenerators =
				ServiceLoader.load(RandomNumberGenerator.class);
		var randomNumberGenerator = randomNumberGenerators.findFirst().get();
		var lotteryService = new StandardLotteryService();
		lotteryService.setRandomNumberGenerator(randomNumberGenerator);
		lotteryService.draw(60, 6, 10)
		              .forEach(System.out::println);
	}

}
