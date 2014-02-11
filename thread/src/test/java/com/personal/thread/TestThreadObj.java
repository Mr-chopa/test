package com.personal.thread;

import org.junit.Test;

public class TestThreadObj {
	@Test
	public void testThreadObj() throws Exception {
		ThreadObj thread = new ThreadObj("Number 1");
	}
	
	@Test
	public void testRun() throws Exception {
		ThreadObj thread = new ThreadObj("Number 1");
		thread.run();
	}
}
