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
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;

/**
 * Handles actually detecting and processing of any fields marked with the MockModel annotation
 * 
 * @author Nathan
 */
public class MockModelProcessor {

	/**
	 * Initializes fields annotated with @MockModle for given object.
	 * 
	 * @param obj - Object we want the mocks injected on
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public static void initMocks(Object obj) throws IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		Class<? extends Object> clazz = obj.getClass();

		Field[] fields = clazz.getDeclaredFields();

		for (Field field : fields) {

			if (!field.isAnnotationPresent(MockModel.class)) {
				continue;
			}

			Object value = DataFactory.populateField(field);
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

	}
	
	

	private static String setterName(String fieldName) {
		char[] ca = fieldName.toCharArray();
		ca[0] = Character.toUpperCase(ca[0]);
		return "set" + new String(ca);
	}

}
