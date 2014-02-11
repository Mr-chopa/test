package com.personal.thread;

import org.junit.Test;

public class TestThreadMain {
	@Test
	public void testThreadMain() throws Exception {
		ThreadMain main = new ThreadMain();
	}
	
	@Test
	public void testMakeThread() throws Exception {
		ThreadMain main = new ThreadMain();
		main.makeThread(100);
	}
	
	public void testRemoveThread() throws Exception {
		ThreadMain main = new ThreadMain();
		main.removeThread(100);
	}
}
