package com.personal.thread;

import java.util.ArrayList;
import java.util.List;

public class ThreadMain {
	private long number = 1L;
	private List<ThreadObj> threadList = null;
	
	public ThreadMain() {
		threadList = new ArrayList<ThreadObj>();
	}
	
	public static void main() {
		ThreadMain main = new ThreadMain();
	}

	public void makeThread(int count) {
		for(int i=0; i<count; i++) {
			threadList.add(new ThreadObj("Number " + (number++)));
		}
	}

	public void removeThread(int number) {
		System.out.println(threadList.get(number-1).getName() + " 쓰레드 종료");
		threadList.get(number-1).interrupt();
	}
}
