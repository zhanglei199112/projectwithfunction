package com.example.demo.entity;

public enum SexEnum {
	women(0),
	man(1);
	
	private int value;


	private SexEnum(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return this.value;
	}

	@Override
	public String toString() {
		return "SexEnum{" +
				"value='" + value + '\'' +
				'}';
	}
}
