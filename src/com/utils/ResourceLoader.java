package com.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ResourceLoader {
	
	public static String getConfig(String key) {
		Properties props = new Properties();
		InputStream is = null;
		String value = null;
		
		try {
			is = ResourceLoader.class.getResourceAsStream("/application.properties");
			props.load(is);
			value = (String) props.get(key);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(is!=null) {
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		System.out.println("Get configuration: key["+key+"], value["+value+"].");
		
		return value;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		getConfig("db.connectionString");
	}

}
