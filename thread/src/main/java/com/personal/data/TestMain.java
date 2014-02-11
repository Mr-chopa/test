package com.personal.data;

public class TestMain implements Runnable{  
	private static long[] longArray = {0x000000A00000000Dl, 0x0000000B000000C0l};		
	private long longValue;		
	
	public TestMain(){	}	    
	
	public static void main( String[] args )    {        
		TestMain test = new TestMain();        
		Thread t1 = new Thread(test);         
		Thread t2 = new Thread(test);
		Thread t3 = new Thread(test);   
		Thread t4 = new Thread(test);   
		Thread t5 = new Thread(test);   
		
		t1.start();        
		t2.start();
		t3.start();
		t4.start();
		t5.start();
	}    	
	
	public void run() {		
		int i = 0;		
		int length = longArray.length;		
		
		while (true) {			
			longValue = longArray[i % length];	
			i = (i < length) ? i + 1 : 0;						
			checkValue(longValue); 		
		}	
	}		
	
	private void checkValue(long l) {		
		for (int i = 0; i < longArray.length; i++) {			
			if (l == longArray[i]) {				
				return;			
			}		
		}		
		
		System.out.println(l + "값이 다릅니다. ");		
		System.exit(-1);	
	}
}
