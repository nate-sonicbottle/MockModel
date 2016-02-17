package com.sonicbottle.mockModel;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class DataTypesTest{
	
	private String nullField;
	
	@MockModel private String stringField;
	@MockModel private int intField;
	@MockModel private Integer intergerField;
	@MockModel private Boolean booleanField;
	@MockModel private boolean booleanPrimitiveField;
	@MockModel private short shortPrimitiveField;
	@MockModel private Short shortField;
	@MockModel private Character characterField;
	@MockModel private char charField;
	@MockModel private Double doubleField;
	@MockModel private double doublePrimitiveField;
	@MockModel private TestPOJO testPOJO;
	@MockModel private Timestamp timestampField;
	@MockModel private Date dateField;
	
	@Before
	public void setUp() throws Exception {
		MockModelProcessor.initMocks(this);
	}
	
	@Test
	public void testStringFields() {
		assertEquals("String isn't null & begins with string", stringField.substring(0,6), "string");
	}
	
	@Test
	public void testIntFields() {
		assertNotSame("int is not default", 0, intField);
	}

	@Test
	public void testNullFieldIsStillNull() {
		assertNull(nullField);
	}
	
	@Test 
	public void testIntegerField() {
		assertNotNull(intergerField);
	}
	
	@Test
	public void testBooleanField() {
		assertNotNull(booleanField);
	}
		
	@Test
	public void testShortField() {
		assertFalse("short is not default", (0 == shortField));
	}
	
	@Test 
	public void testCharacterField() {
		assertNotNull(characterField);
	}
	
	@Test
	public void testCharField() {
		assertFalse("short is not default", (0 == charField));
	}
	
	@Test 
	public void testDoubleField() {
		assertNotNull(doubleField);
	}
	
	@Test
	public void testDoublePrimitiveField() {
		assertFalse("short is not default", (0.0d == doublePrimitiveField));
	}
	
	@Test
	public void testTimestampField() {
		assertNotNull(timestampField);
	}
	
	@Test
	public void testDateField() {
		assertNotNull(dateField);
	}
	
	
	@Test
	public void testPOJOField() {
		assertNotNull(testPOJO);
		assertNotNull(testPOJO.getName());
		assertTrue(testPOJO.getAge() > 1);
	}
}
