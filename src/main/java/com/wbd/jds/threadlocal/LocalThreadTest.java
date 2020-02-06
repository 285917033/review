package com.wbd.jds.threadlocal;

public class LocalThreadTest extends Thread {

	private ThreadLocalDemo  tld;
	
	public LocalThreadTest(ThreadLocalDemo  tld) {
		this.tld = tld;
	}
	
	@Override
	public void run() {
		
		for(int i=0;i<3;i++) {
			System.out.println(getName()+","+tld.getNumber());
		}
	}
	
	public static void main(String[] args) {
		ThreadLocalDemo tld2 = new ThreadLocalDemo();
		LocalThreadTest t1 = new LocalThreadTest(tld2);
		LocalThreadTest t2 = new LocalThreadTest(tld2);
		LocalThreadTest t3 = new LocalThreadTest(tld2);
		
		t1.start();
		t2.start();
		t3.start();
	}
}
