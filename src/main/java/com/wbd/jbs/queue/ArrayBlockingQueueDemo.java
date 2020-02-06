package com.wbd.jbs.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 高性能队列 线程安全 concurrentLinkedQueue 通过无锁的方式，实现了高并发状态下的高性能
 * 通常情况下性能高于blockingQueue,一个基于连接点的无界线程安全队列，该队列的元素时FIFO原则 不允许null元素
 * add,offer都是加入元素的方法， poll,peek都是获取头部元素节点， poll,take会删除元素，peek不会
 * blockingqueue是阻塞队列（也是线程安全的），是一个支持两个附加操作的队列， 这两个附加操作时， 在队列为空时
 * 
 * 获取元素的线程会等待队列为非空，当队列满时，写入，存储元素的线程会等待队列可用
 * 
 * 阻塞队列用在的场景是， 生产者生产的效率大于消费者， 或者消费者的消费效率大于生产者的效率。
 * 后进先出（LIFO）：后插入队列的元素最先出队列，这种队列优先处理最近发生的事件。
 * 
 * @author zgh
 *  concurrentlinkedqueue 下的 add,offer, poll,peek都是一样的，不会阻塞队列， 只有在阻塞队列中，他们的方法是有区别的
 * blockingqueue下的 add,offer, poll,peek区别
 *  add 如果可以在不超过队列的容量的情况下立即将其指定的元素插入到该队列的尾部，如果队列已满，则返回 true并抛出 IllegalStateException 
 *  offer 如果可以在不超过队列容量的情况下立即将其指定的元素插入该队列的尾部，则在成功时true如果该队列已满，则返回false 。 该方法通常比方法add(E)更为方便 ，只能通过抛出异常来插入元素。 
 *  peek()检索但不删除此队列的头，如果此队列为空，则返回 null 
 *  poll() 检索并删除此队列的头部，如果此队列为空，则返回 null 。 
    take() 检索并删除此队列的头，如有必要，等待元素可用。 

 */
public class ArrayBlockingQueueDemo {

	public static void main(String[] args) {
		// ArrayBlockingQueue是一个有界队列，他的内部实现是一个数组，他的容量是有限的， 我们
		// 必须在其初始化的时候指定他的大小，容量大小一旦指定就不可以改变， 也是先进先出，最新插入的对象是尾部，最新移出的对象是头部
		ArrayBlockingQueue<String> arrays = new ArrayBlockingQueue<String>(3);
		arrays.add("zs");
		arrays.add("lisi");
		arrays.offer("ww");
		try {
			arrays.offer("zl", 1, TimeUnit.SECONDS); // 该元素无法加入， 因为上面已经申请了3个元素， 也加入了3个元素，
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			System.out.println(arrays.take());// 检索并删除此队列的头，如有必要，等待元素可用
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(arrays.size());

		// LinkedBlockingQueue阻塞队列大小的配置是可选的，如果我们初始化时指定一个大小，它就是有边界的，如果不指定，它就是无边界的。说是无边界，其实是采用了默认大小为Integer.MAX_VALUE的容量
		// 。它的内部实现是一个链表。
		
		LinkedBlockingQueue<String> linkedBlockingQueue = new LinkedBlockingQueue<String>();
		
		linkedBlockingQueue.add("abc");
		linkedBlockingQueue.add("ef");
		linkedBlockingQueue.add("g");
		linkedBlockingQueue.add("f");
		System.out.println(linkedBlockingQueue.size());
	}

}
