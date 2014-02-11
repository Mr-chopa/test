package com.personal.thread;

public class ThreadObj extends Thread {

	public ThreadObj(String name) {
		super(name);
	}
	
	public void run() {
		try {
			while(!Thread.interrupted()) {
				Thread.sleep(10);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
