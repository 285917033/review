package com.wbd.jbs.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * 实现类似计数器功能， 比如有一个任务A，它要等待其他4个任务执行完之后才能执行
 * @author zgh
 *
 */
public class CountDownLatchDemo {

	public static void main(String[] args) {
		
		final CountDownLatch countDownLatch = new CountDownLatch(2);

		
		new Thread(new Runnable() {
			/**
			 *TODO 
			 */
			public void run() {
				System.out.println("线程1...执行");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				System.out.println("execute before计算器1="+countDownLatch.getCount());
				countDownLatch.countDown();
				System.out.println("execute after计算器1="+countDownLatch.getCount());
				System.out.println("线程1执行结束");
			}
		}).start();
		
		new Thread(new Runnable() {
			
			public void run() {
				System.out.println("线程2...执行");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				System.out.println("执行之前计算器="+countDownLatch.getCount());
				countDownLatch.countDown();
				System.out.println("执行之后计算器="+countDownLatch.getCount());
				System.out.println("线程2执行结束");
			}
		}).start();
		
		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("开始执行主线程....");
		
		for(int i=0;i<20;i++) {
			System.out.println("main:i"+i);
		}
	}

}
