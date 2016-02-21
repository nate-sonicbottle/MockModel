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

		if (fieldType.isPrimitive()) {
			if (fieldType.toString().equals("int")) {
				value = getInteger();
			} else 
				if (fieldType.toString().equals("long")) {
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
		} else if (fieldType.isAssignableFrom(String.class)) {
			value = getString();
		} else if (fieldType.isAssignableFrom(Integer.class)) {
			value = getInteger();
		} else if (fieldType.isAssignableFrom(Long.class)) {
			value = getLong();
		} else if (fieldType.isAssignableFrom(Short.class)) {
			value = getShort();
		} else if (fieldType.isAssignableFrom(Double.class)) {
			value = getDouble();
		} else if (fieldType.isAssignableFrom(Boolean.class)) {
			value = getBoolean();
		} else if (fieldType.isAssignableFrom(Character.class)) {
			value = getChar();
		} else if (fieldType.isAssignableFrom(Timestamp.class)) {
			value = getTimestamp();
		} else if (fieldType.isAssignableFrom(Date.class)) {
			value = getDate();
		} else if (!fieldType.isInterface()) {
			value = getObject(fieldType);
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
				try {
					Method setterMethod = clazz.getMethod(
							setterName(field.getName()), field.getType());
					setterMethod.invoke(obj, value);
				} catch (NoSuchMethodException e) {
					// Since setter injection failed, lets change permissions on field to accessible 
					field.setAccessible(true);
					field.set(obj, value);
				}
			}
		} catch (Exception e) {
			return null; // Failed to produce object
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
	
	public static Date getDate() {
		return new Date();
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
