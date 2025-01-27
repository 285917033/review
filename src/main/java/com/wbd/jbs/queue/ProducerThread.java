package com.wbd.jbs.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class ProducerThread extends Thread {

	private BlockingQueue queue;

	private volatile boolean flag = true;

	private static AtomicInteger count = new AtomicInteger();

	public ProducerThread(BlockingQueue queue) {
		this.queue = queue;
	}

	@Override
	public void run() {

		System.out.println("生产者线程启动....");

		while (flag) {

			System.out.println("生产者正在生产队列~~~~");

			String data = count.incrementAndGet() + "";

			// 添加队列
			boolean offer = queue.offer(data);
			if (offer) {
				System.out.println("生产者添加队列" + data + "成功");
			} else {

				System.out.println("生产者添加队列" + data + "failure");
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				System.out.println("生产者线程停止######");
			}
		}

	}

	public void stopThread() {
		this.flag = false;
	}

}
