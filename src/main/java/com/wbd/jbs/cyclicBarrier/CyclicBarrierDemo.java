package com.wbd.jbs.cyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 障碍 ，cyclicBarrier初始化时规定一个数目，然后程序调用await进入等待的线程数
 * 当线程数达到了这个数目时，所有进入等待状态的线程被唤醒并继续执行
 * @author zgh
 *
 */
public class CyclicBarrierDemo extends Thread{

	CyclicBarrier cyclicBarrier;
	
	public CyclicBarrierDemo(CyclicBarrier cyclicBarrier) {
		this.cyclicBarrier = cyclicBarrier;
	}
	
	
	@Override
		public void run() {
			
		System.out.println(Thread.currentThread().getName()+"开始写入数据...");

		try {
			Thread.sleep(1000);
			System.out.println(Thread.currentThread().getName()+"写入成功~~~~~~~~");
			cyclicBarrier.await();
			System.out.println(Thread.currentThread().getName()+"写入完毕****");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	
	
	
	public static void main(String[] args) {

	CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
	
	for(int i=0;i<3;i++) {
		
		new CyclicBarrierDemo(cyclicBarrier).start();
	}
		
	}

}
