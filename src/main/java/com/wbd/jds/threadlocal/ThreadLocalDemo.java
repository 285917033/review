package com.wbd.jds.threadlocal;

/**
 * threadlocal每个线程独立维护的一个对象
 * 每个线程可以独立改变自己的副本，而不会影响其他线程所对应的副本
 * Threadlocal接口方法
 * set设置当前线程的线程局部变量的值
 * get返回当前线程所对应的线程局部变量
 * remove 将当前线程的局部变量值删除，目的是为了减少内存占用
 * initalvalue 该方法是初始化线程局部变量值，在线程第一次get或者set时才知晓
 * 并且只执行一次。默认返回一个null
 * @author zgh
 *
 */
public class ThreadLocalDemo {
	
	public int count=0;
	
	static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>() {
		
		protected Integer initialValue() {
			System.out.println("init first ");
			return 0;
		};
	};
	
	public String getNumber() {
		count = threadLocal.get()+1;
		threadLocal.set(count);
		return count+"";
	}
	
	public static void main(String[] args) {
		ThreadLocalDemo tl = new ThreadLocalDemo();
		System.out.println(tl.getNumber());
		System.out.println(threadLocal.get());
	
	}

}
