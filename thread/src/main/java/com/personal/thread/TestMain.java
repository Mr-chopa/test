package com.personal.thread;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class TestMain {
	private List<ThreadObj> list = new ArrayList<ThreadObj>();
	private int threadCnt = 0;
	private long line = 1L;
	private long testValue = 0L;
	
	public Vector v = new Vector();
	public HashMap map = new HashMap();
	
	public TestMain() {
		v.add(0L);
		map.put("value", 0L);
	}
	
	public void call(long value) {
		//v.set(0, value);
		map.put("value", value);
	}
	
	/**
	 * 현재 출력 라인 번호를 반환한다.
	 * @return 출력 라인 번호 ex.) 1 : 
	 */
	public String getLine() {
		return (line++) + " : " ;
	}
	
	/**
	 * 전달받은 number 갯수 만큼 쓰레드를 생성한다.
	 * @param number 생성할 쓰레드 수
	 */
	public void addThread(Integer number) {
		for(int i=0; i<number; i++) {
			list.add(new ThreadObj(++threadCnt*10000000000L, this));
		}
		
		System.out.println(getLine() + number + " 쓰레드 추가 완료");
	}
	
	public void startThread() {
		for(int i=0; i<list.size(); i++) {
			list.get(i).start();
		}
	}
	
	public void interruptThread() {
		for(int i=0; i<list.size(); i++) {
			list.get(i).interrupt();
		}
	}
	
	public void waitThread() {
		for(int i=0; i<list.size(); i++) {
			try {
				synchronized(list.get(i)) {
					list.get(i).wait();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void notifyThread() {
		for(int i=0; i<list.size(); i++) {
			list.get(i).notify();
		}
	}
	
	public void status() {
		for(int i=0; i<list.size(); i++) {
			System.out.println(getLine() + list.get(i).getName() + " = " + list.get(i).getState());
		}
		
		System.out.println(getLine() + "map = " + map.get("value"));
		System.out.println(getLine() + "testValue = " + testValue);
		//System.out.println(getLine() + "vector = " + v.get(0));
	}
	
	class ThreadObj extends Thread {
		TestMain m = null;
		long index;
		
		public ThreadObj(long index, TestMain m) {
			super("Number " + index);
			
			this.m = m;
			this.index = index;
		}
		
		public void run() {
			try{
				while(true) {
					System.out.println(Thread.currentThread().getName() + " 시작 : " + Calendar.getInstance().getTimeInMillis());
					
					//synchronized(m.map) {
						for(long i=0; i<999999999; i++) {
								//m.map.put("value", index);
								//m.v.set(0, index);
							m.call(index);
							m.testValue = m.testValue*0+index;
						}
					//}
					
					System.out.println(Thread.currentThread().getName() + " 종료 : " + Calendar.getInstance().getTimeInMillis());
					
					sleep(100);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		TestMain main = new TestMain();
		
		// 콘솔 입력 수신
		Scanner scan = new Scanner(System.in);
					
		try{
			String text = null;
			
			Method method = null;
			
			// 콘솔 입력 처리
			// @종료 조건 : exit 입력 또는 console escape
			while(scan.hasNext()) {
				text = scan.nextLine();
				
				if(text.equals("exit")) {
					System.out.println(main.getLine() + "종료");
					
					break;
				}
				else {
					try{
						// 콘솔 입력 내용을 콤마(,)로 분리
						String[] params = text.split(",");
						@SuppressWarnings("rawtypes")
						Class[] parameterTypes = null;
						Object param = null;
						
						// 파라미터 타입(Object type)에  따라 메서드를 호출 한다.
						if(params[1].equals("String")) {
							parameterTypes = new Class[]{String.class};
							param = new String(params[2]);
						}
						else if(params[1].equals("Integer")) {
							parameterTypes = new Class[]{Integer.class};
							param = new Integer(params[2]);
						}
						
						method = main.getClass().getMethod(params[0], parameterTypes);
						
						if(param == null) {
							method.invoke(main);
						}
						else {
							method.invoke(main, param);
						}
						
					}catch(Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		}catch(Exception e){
			try{ scan.close(); }catch(Exception ex){}
			
			e.printStackTrace();
		}
	}

}
