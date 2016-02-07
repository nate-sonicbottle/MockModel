package com.sonicbottle.mockModel;

import static org.junit.Assert.*;

import org.junit.Test;

public class DataTypesTest{
	
	MockModeTest test = new MockModeTest();	
	
	@Test
	public void testStringFields() {
		String stringField = test.getStringField();
		assertEquals("String isn't null & begins with string", stringField.substring(0,6), "string");
	}
	
	@Test
	public void testIntFields() {
		int intField = test.getIntField();
		assertNotSame("int is not default", 0, intField);
	}

	@Test
	public void testNullFieldIsStillNull() {
		String nullField = test.getNullField();
		assertNull(nullField);
	}
	
	@Test 
	public void testIntegerField() {
		Integer integerField = test.getIntergerField();
		assertNotNull(integerField);
	}
	
	@Test
	public void testBooleanField() {
		Boolean booleanField = test.getBooleanField();
		assertNotNull(booleanField);
	}
	
	@Test
	public void testBooleanPrimitiveField() {
		boolean booleanField = test.getBooleanField();
	}
	
	@Test
	public void testShortField() {
		short shortField = test.getShortField();
		assertFalse("short is not default", (0 == shortField));
	}
	
	@Test 
	public void testCharacterField() {
		 Character characterField = test.getCharacterField();
		assertNotNull(characterField);
	}
	
	@Test
	public void testCharField() {
		char charField = test.getCharField();
		assertFalse("short is not default", (0 == charField));
	}
	
	@Test 
	public void testDoubleField() {
		 Double doubleField = test.getDoubleField();
		assertNotNull(doubleField);
	}
	
	@Test
	public void testDoublePrimitiveField() {
		double doublePrimitiveField= test.getDoublePrimitiveField();
		assertFalse("short is not default", (0.0d == doublePrimitiveField));
	}
	
	@Test
	public void testPOJOField() {
		TestPOJO testPOJO = test.getTestPOJO();
		assertNotNull(testPOJO);
		assertNotNull(testPOJO.getName());
	}
}
