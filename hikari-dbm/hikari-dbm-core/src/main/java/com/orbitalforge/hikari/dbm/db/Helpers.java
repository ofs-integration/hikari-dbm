package com.orbitalforge.hikari.dbm.db;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class Helpers {
	private Helpers() { }
	
	/**
	 * Implementation of String.join to allow for < Java 1.8 Compatibility
	 * @param delimiter
	 * @param values
	 * @return
	 */
	public static String join(String delimiter, String... values) {
		boolean and = false;
		StringBuffer buffer = new StringBuffer();
		
		for(String v : values) {
			if(and) { buffer.append(delimiter); }
			else { and = true; }
			
			buffer.append(v);
		}
		
		return buffer.toString();
	}
	
	public static String join(String delimiter, List<String> values) {
		boolean and = false;
		StringBuffer buffer = new StringBuffer();
		
		for(String v : values) {
			if(and) { buffer.append(delimiter); }
			else { and = true; }
			
			buffer.append(v);
		}
		
		return buffer.toString();
	}
		
	public static String[] getColumns(ResultSet resultSet) throws SQLException {
		ResultSetMetaData metadata = resultSet.getMetaData();
		String[] results = new String[metadata.getColumnCount()];
		
		for(int i = 1; i <= metadata.getColumnCount(); i++) {
			results[i - 1] = metadata.getColumnName(i);
		}
		
		return results;
	}
	
	public static List<Map<String, Object>> readResults(ResultSet resultSet) throws SQLException {
		String[] columns = getColumns(resultSet);
		List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
		
		while(resultSet.next()) {
			Map<String, Object> current = new LinkedHashMap<String, Object>();
			
			for(int i = 0; i < columns.length; i++) {
				Object data = resultSet.getObject(columns[i]);
				if(data != null) {
					current.put(columns[i].toLowerCase(), resultSet.getObject(columns[i]));
				}
			}
			
			results.add(current);
		}
		
		return results;
	}

	public static boolean parseBoolean(Object objectValue) {
		if(objectValue == null) return false;
		if(objectValue.getClass() == String.class) {
			String value = ((String)objectValue).toLowerCase();
			switch(value) {
				case "no":
				case "fasle":
				case "0":
					return false;
				case "yes":
				case "true":
				case "1":
					return true;
				default:
					return false;
			}
		}
		
		if(	objectValue.getClass() == Integer.class ||
			objectValue.getClass() == Byte.class) {
			return ((Integer)objectValue) == 1;
		}
		
		return false;
	}
}
