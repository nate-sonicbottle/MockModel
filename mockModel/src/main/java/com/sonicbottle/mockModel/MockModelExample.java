package com.sonicbottle.mockModel;

public class MockModelExample {

	@MockModel
	private String hello;
	
	public MockModelExample() {
		MockModelProcessor.initAnnotations(this);
	}

	public String getHello() {
		return hello;
	}

	public void setHello(String hello) {
		this.hello = hello;
	}
}
