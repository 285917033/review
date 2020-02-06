package com.wbd.ratelimiter;

import java.util.concurrent.TimeUnit;

import com.google.common.util.concurrent.RateLimiter;

public class RateLimiterDemo {

	static RateLimiter limiter = RateLimiter.create(10);
	
	private static final RateLimiter RATE_LIMITER = RateLimiter.create(5);

	public static void main(String[] args) {

//		if (limiter.tryAcquire(100)) {
//			
//			System.out.println("aaa");
//		}
//		if (limiter.tryAcquire(15)) {
//			System.out.println("abc");
//		}
//
//		if (limiter.tryAcquire(2)) {
//
//			System.out.println("cfg");
//		}
		
//		RateLimiter limiter = RateLimiter.create(5);
//		System.out.println(limiter.acquire());
//		System.out.println(limiter.acquire());
//		System.out.println(limiter.acquire());
//		System.out.println(limiter.acquire());
//		System.out.println(limiter.acquire());
//		System.out.println(limiter.acquire());
		
//		RateLimiter limiter = RateLimiter.create(5);
//		
//		System.out.println(limiter.tryAcquire());
//		System.out.println(limiter.acquire(10));
//		System.out.println(limiter.acquire(1));
//		System.out.println(limiter.acquire(1));
		
		 // 模拟有100个请求
        for (int i = 0; i < 100; i++) {
            // 尝试从令牌桶中获取令牌，若获取不到则等待300毫秒看能不能获取到
            if (RATE_LIMITER.tryAcquire(300, TimeUnit.MILLISECONDS)) {
            	
            	System.out.println(RATE_LIMITER.acquire());
                // 获取成功，执行相应逻辑
                handle(i);
            }
        }
	}
	
	 private static void handle(int i) {
	        System.out.println(i);
	    }

}
