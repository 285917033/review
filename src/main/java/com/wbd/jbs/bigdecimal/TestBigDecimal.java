package com.wbd.jbs.bigdecimal;

import java.math.BigDecimal;

public class TestBigDecimal {

	public static void main(String[] args) {
		
		float a = 0.6f-0.5f;
		float b = 0.9f-0.8f;
		System.out.println(a);
		System.out.println(b);
		System.out.println(a==b);
	
		BigDecimal b1 = new BigDecimal("0.7");
		BigDecimal b2 = new BigDecimal("0.6");
		BigDecimal b3 = new BigDecimal("0.5");
		
		BigDecimal x = b1.subtract(b2);
		BigDecimal y = b2.subtract(b3);
		
		System.out.println(x);
		System.out.println(y);
		System.out.println(x.equals(y));
		
		System.out.println(b1.compareTo(b2));
		
		System.out.println(b3.compareTo(b2));
		
		System.out.println(x.compareTo(y));
		
		BigDecimal z = new BigDecimal("123.44455");
		BigDecimal z1 = z.setScale(3, BigDecimal.ROUND_HALF_DOWN);

		System.out.println(z1);
		
	 
		
	}

}
