package com.wbd.jbs.semaphore;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * 信号量， 是一种基于计数的信号量， 它可以设定一个阀值，基于此值
 * 
 * 多个线程竞争获取许可信号， 做注解的申请后归还， 超过阀值，线程申请许可被阻塞 ，该信号量可以用来构建对象池，比如，数据库连接池，线程池。
 * 
 * ，我们也可以创建计数为1的Semaphore,将其作为一种类似互斥锁的机制，这也叫二元信号量，表示两种互斥状态。用法如下：
 * availablePermits函数用来获取当前可用的资源数量 wc.acquire();//申请资源 wc.release();//释放资源
 * 
 * @author zgh
 *
 */
public class SemaphoreDemo extends Thread {

	Semaphore semaphore;
	String name;

	public SemaphoreDemo(Semaphore semaphore, String name) {
		this.semaphore = semaphore;
		this.name = name;
	}

	@Override
	public void run() {

		//获取可用的资源数
	  int availablePermits = semaphore.availablePermits();
	  if(availablePermits>0) {
		  System.out.println(name+"有位置");
	  }else {
		  
		  System.out.println(name+"没有位置");
	  }
	  
	  try {
		  //申请资源
		semaphore.acquire();
		System.out.println(name+"执行");
		Thread.sleep(new Random().nextInt(1000));
		System.out.println(name+"执行完成");
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		//释放资源
		semaphore.release();
	}
	}

	public static void main(String[] args) {

		//最多同时支持3个线程同步执行
		Semaphore semaphore = new Semaphore(3);
		for(int i=0;i<10;i++) {
			new SemaphoreDemo(semaphore,i+"").start();
		}
	}

}
