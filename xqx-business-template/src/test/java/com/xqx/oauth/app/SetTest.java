package com.xqx.oauth.app;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class SetTest {
	
	private static final Set<Long> blackSet = new HashSet<>();
	private static final int count = 1000;
	

	public static void main(String[] args) {
		
		Random r = new Random();

		Thread putTask = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i= 0;i< count;i++) {
					blackSet.add(Long.valueOf(r.nextInt(10000)));
				}
				
			}
		});
		
		Thread removeTask = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					TimeUnit.MICROSECONDS.sleep(300);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				for(int i= 0;i< count;i++) {
					long l = Long.valueOf(r.nextInt(10000));
					if(blackSet.contains(l)) {
						blackSet.remove(l);
					}
					
				}
				
			}
		});
		
		Thread getTask = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i= 0;i< count;i++) {
					long l = Long.valueOf(r.nextInt(10000));
					blackSet.contains(l);
					
				}
				
			}
		});

		putTask.start();
		removeTask.start();
		getTask.start();

		try {
			TimeUnit.SECONDS.sleep(50);
			System.out.println(blackSet.size());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
