package com.wbd.jbs.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ConsumerThread extends Thread{

	private BlockingQueue<String> queue;
	
	private volatile boolean flag =true;
	
	private AtomicInteger count = new AtomicInteger();
	
	
	public ConsumerThread(BlockingQueue<String> queue) {
		this.queue = queue;
	}
	
	
	@Override
	public void run() {
		
		System.out.println("consumer run ....");
		while(flag) {
			try {
			String data = 	queue.poll(2,TimeUnit.SECONDS);
			if(data!=null) {
				
				System.out.println("消费者获取data:"+data+"success");
			}else {
				System.out.println("消费者获取data:failure");
				flag = false;
			}
			Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				System.out.println("消费者线程停止))))))))))))");
			}
		}
	}

	public void stopThread() {
		this.flag = false;
	}


	public static void main(String[] args) {
		BlockingQueue<String> queue = new LinkedBlockingQueue<String>(10);
		ProducerThread p1 = new ProducerThread(queue);
		ProducerThread p2 = new ProducerThread(queue);
		
		ConsumerThread c = new ConsumerThread(queue);
		
		p1.start();
		p2.start();
		c.start();
		try {
			Thread.sleep(10*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		p1.stopThread();
		p2.stopThread();
		
		
		
	}
}
