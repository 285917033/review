package com.wbd.jbs.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * callable接口和runnable的区别
 * 
 * callable也只有一个方法call，该方法里可以编写执行的业务代码 返回的就是执行的结果，和runnable的区别是它有返回结果，并且可以抛异常
 * 一般配合threadPoolExector使用
 * 
 * Future也是一个接口，它可以对具体的Runnable或者Callable任务进行取消、判断任务是否已取消、查询任务是否完成、获取任务结果。如果是Runnable的话返回的结果是null(下面会剖析为什么Runnable的任务，Future还能返回结果)。
 * 接口里面有以下几个方法。注意两个get方法都会阻塞当前调用get的线程，直到返回结果或者超时才会唤醒当前的线程
 * 
 * @author zgh
 *
 */
public class CallableDemo {

	public static void main(String[] args) {

		Callable<String> callable = new Callable<String>() {

			public String call() throws Exception {

				return "123";
			}

		};

		ExecutorService executorService = Executors.newFixedThreadPool(1);

		Future<String> result = executorService.submit(callable);
		
		//取消任务
		result.cancel(true);
		System.out.println(result.isCancelled());
//		
//		try {
//			System.out.println(result.get());
//		} catch (InterruptedException e) {
//
//			e.printStackTrace();
//		} catch (ExecutionException e) {
//
//			e.printStackTrace();
//		}
		
		//futuretask的应用  ,1,采用thread，2.线程池
		//java 8 的lamdb表达式，其实调用一个参数的构造器，参数为callable接口
		FutureTask<String> futureTask = new FutureTask<String>(()->"result");
		// FutureTask实现了RunnableFuture 接口，而RunnableFuture接口又继承了runnable与future接口， 
		//所以可以当线程参数传入。
		//FutureTask它可以作为Runnable被线程执行，又可以有Future的那些操作。它的两个构造器如下
		
		Thread t1 = new Thread(futureTask);
		
		t1.start();
		
		try {
			String r = futureTask.get();
			System.out.println(r);
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
