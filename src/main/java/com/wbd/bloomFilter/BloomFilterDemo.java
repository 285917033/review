package com.wbd.bloomFilter;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

public class BloomFilterDemo {

	public static void main(String[] args) {

		BloomFilter<CharSequence> bloomfilter = BloomFilter.create(Funnels.stringFunnel(Charset.forName("utf-8")), 1000,
				0.001);
		bloomfilter.put("123");
		bloomfilter.put("456");
		bloomfilter.put("abc");

		System.out.println(bloomfilter.mightContain("abc"));
		System.out.println(bloomfilter.mightContain("a"));
		System.out.println(bloomfilter.mightContain("789"));

		int size = 10000000;

		BloomFilter<Integer> bloomFilter2 = BloomFilter.create(Funnels.integerFunnel(), size, 0.01);

		
		for(int i=0;i<size;i++) {
			
			bloomFilter2.put(i);
			
		}
		
		List<Integer> list = new ArrayList<Integer>(1000);
		
		for(int i=size+1000;i<size+2000;i++) {
			
			if(bloomFilter2.mightContain(i)) {
				
				list.add(i);
			}
		}
		
		System.out.println("误判率为"+list.size());
	}

}
