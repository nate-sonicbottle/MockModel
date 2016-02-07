package com.sonicbottle.mockModel;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;

public class DataFactory {

	private static int fieldCount = 0;
	private static long time = new Date().getTime();
	private static Random ran = new Random();

	private static synchronized int fieldCount() {
		return ++fieldCount;
	}

	public static Object populateField(Field field) {
		Object value = null;
		Class<?> fieldType = field.getType();

		try {
			if (fieldType.isPrimitive()) {
				if (fieldType.toString().equals("int")) {
					value = getInteger();
				} else if (fieldType.toString().equals("long")) {
					value = getLong();
				} else if (fieldType.toString().equals("short")) {
					value = getShort();
				} else if (fieldType.toString().equals("double")) {
					value = getDouble();
				} else if (fieldType.toString().equals("boolean")) {
					value = getBoolean();
				} else if (fieldType.toString().equals("char")) {
					value = getChar();
				}
			} else if (fieldType.isInstance(String.class.newInstance())) {
				value = getString();
			} else if (fieldType.isInstance(new Integer(0))) {
				value = getInteger();
			} else if (fieldType.isInstance(new Long(0))) {
				value = getLong();
			} else if (fieldType.isInstance(new Short((short) 0))) {
				value = getShort();
			} else if (fieldType.isInstance(new Double(0))) {
				value = getDouble();
			} else if (fieldType.isInstance(Boolean.TRUE)) {
				value = getBoolean();
			} else if (fieldType.isInstance(new Character('Y'))) {
				value = getChar();
			} else if (fieldType.isInstance(new Timestamp(0))) {
			} else if (!fieldType.isInterface()) {
				value = getObject(fieldType);
			}
		} catch (InstantiationException | IllegalAccessException e) {
			System.err.println(e);
		}
		return value;
	}

	public static <T> T getObject(final Class<T> clazz) {
		T obj = null;
		Field[] fields = clazz.getDeclaredFields();

		try {
			obj = clazz.newInstance();
			for (Field field : fields) {
				Object value = populateField(field);
				Method setterMethod = clazz.getMethod(
						setterName(field.getName()), field.getType());
				setterMethod.invoke(obj, value);
			}
		} catch (Exception e) {
			System.err.println(e);
		}
		return obj;
	}

	public static int getInteger() {
		return fieldCount();
	}

	public static long getLong() {
		return fieldCount();
	}

	public static char getChar() {
		return (fieldCount() % 2) == 1 ? 'Y' : 'N';
	}

	public static short getShort() {
		return (short) fieldCount();
	}

	public static double getDouble() {
		return fieldCount() + ((double) ran.nextInt(100) / 100);
	}

	public static Timestamp getTimestamp() {
		return new Timestamp(time);
	}

	public static boolean getBoolean() {
		return (fieldCount() % 2) == 1 ? true : false;
	}

	public static String getString() {
		return "string" + fieldCount();
	}
	
	private static String setterName(String fieldName) {
		char[] ca = fieldName.toCharArray();
		ca[0] = Character.toUpperCase(ca[0]);
		return "set" + new String(ca);
	}
}