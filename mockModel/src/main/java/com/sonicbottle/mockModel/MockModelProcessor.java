package com.sonicbottle.mockModel;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Timestamp;

public class MockModelProcessor {

	public static void initAnnotations(Object obj) {
		Class<? extends Object> clazz = obj.getClass();

		Field[] fields = clazz.getDeclaredFields();

		try {
			for (Field field : fields) {
				
				if (!field.isAnnotationPresent(MockModel.class)) {
					continue;
				}
				
				Object value = DataFactory.populateField(field);
				
				Method setterMethod = clazz.getMethod(setterName(field.getName()), field.getType());
				setterMethod.invoke(obj, value);
			}
		} catch (Exception e) {
			System.err.println(e);
		}

	}

	private static String setterName(String fieldName) {
		char[] ca = fieldName.toCharArray();
		ca[0] = Character.toUpperCase(ca[0]);
		return "set" + new String(ca);
	}
	
}
