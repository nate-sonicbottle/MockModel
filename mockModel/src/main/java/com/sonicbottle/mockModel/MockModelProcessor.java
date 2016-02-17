package com.sonicbottle.mockModel;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;

public class MockModelProcessor {

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
