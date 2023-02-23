package com.example.application;

import java.util.concurrent.ThreadLocalRandom;

public class StudyStreamAPI {

	public static void main(String[] args) {
		ThreadLocalRandom.current()
		                 .ints(1, 60)
		                 .distinct()
		                 .limit(6)
		                 .sorted()
		                 .boxed()
		                 .forEach(System.out::println); // terminal method/lazy evaluation

	}

}
