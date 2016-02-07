package com.sonicbottle.mockModel;

public class MockModelExample {

	@MockModel
	private String hello;
	
	@MockModel
	private int bob;
	
	public MockModelExample() {
		MockModelProcessor.initAnnotations(this);
	}

	public String getHello() {
		return hello;
	}

	public void setHello(String hello) {
		this.hello = hello;
	}

	public int getBob() {
		return bob;
	}

	public void setBob(int bob) {
		this.bob = bob;
	}
	
}
