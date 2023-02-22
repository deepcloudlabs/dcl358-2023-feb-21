package com.example.lottery.application;

import java.util.ServiceLoader;

import com.example.lottery.service.business.StandardLotteryService;
import com.example.random.service.RandomNumberGenerator;

public class LotteryApp {

	public static void main(String[] args) {
		ServiceLoader<RandomNumberGenerator> randomNumberGenerators =
				ServiceLoader.load(RandomNumberGenerator.class);
		var randomNumberGenerator = randomNumberGenerators.findFirst().get();
		var lotteryService = new StandardLotteryService();
		lotteryService.setRandomNumberGenerator(randomNumberGenerator);
		lotteryService.draw(60, 6, 10)
		              .forEach(System.out::println);
	}

}
