/*
 * The MIT License (MIT)
 * 
 * Copyright (c) 2016 Nathan Phillips

 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.sonicbottle.mockModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.Timestamp;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests all the dataTypes to ensure that MockModel works as expected
 *
 * @author Nathan
 */
public class DataTypesTest {

	private static final String FINAL_FIELD_TEXT = "Can't be changed";

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
	@MockModel private TestEnum enumField;
	@MockModel final private String finalStringField = FINAL_FIELD_TEXT;
	
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
	
	@Test
	public void testFinalStringField() {
		assertEquals(FINAL_FIELD_TEXT, finalStringField);
	}

	@Test
	public void testEnum() {
		assertNotNull(enumField);
	}
}
