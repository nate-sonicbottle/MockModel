package com.sonicbottle.mockModel;

public class MockModeTest {

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
	
	public MockModeTest() {
		MockModelProcessor.initAnnotations(this);
	}

	public String getStringField() {
		return stringField;
	}

	public void setStringField(String stringField) {
		this.stringField = stringField;
	}

	public int getIntField() {
		return intField;
	}

	public void setIntField(int intField) {
		this.intField = intField;
	}

	public Integer getIntergerField() {
		return intergerField;
	}

	public void setIntergerField(Integer intergerField) {
		this.intergerField = intergerField;
	}

	public String getNullField() {
		return nullField;
	}

	public void setNullField(String nullField) {
		this.nullField = nullField;
	}

	public Boolean getBooleanField() {
		return booleanField;
	}

	public void setBooleanField(Boolean booleanField) {
		this.booleanField = booleanField;
	}

	public boolean isBooleanPrimitiveField() {
		return booleanPrimitiveField;
	}

	public void setBooleanPrimitiveField(boolean booleanPrimitiveField) {
		this.booleanPrimitiveField = booleanPrimitiveField;
	}

	public short getShortField() {
		return shortField;
	}

	public void setShortField(short shortField) {
		this.shortField = shortField;
	}

	public short getShortPrimitiveField() {
		return shortPrimitiveField;
	}

	public void setShortPrimitiveField(short shortPrimitiveField) {
		this.shortPrimitiveField = shortPrimitiveField;
	}

	public Character getCharacterField() {
		return characterField;
	}

	public void setCharacterField(Character characterField) {
		this.characterField = characterField;
	}

	public char getCharField() {
		return charField;
	}

	public void setCharField(char charField) {
		this.charField = charField;
	}

	public Double getDoubleField() {
		return doubleField;
	}

	public void setDoubleField(Double doubleField) {
		this.doubleField = doubleField;
	}

	public double getDoublePrimitiveField() {
		return doublePrimitiveField;
	}

	public void setDoublePrimitiveField(double doublePrimitiveField) {
		this.doublePrimitiveField = doublePrimitiveField;
	}

	public void setShortField(Short shortField) {
		this.shortField = shortField;
	}

	public TestPOJO getTestPOJO() {
		return testPOJO;
	}

	public void setTestPOJO(TestPOJO testPOJO) {
		this.testPOJO = testPOJO;
	}
	
	
}
