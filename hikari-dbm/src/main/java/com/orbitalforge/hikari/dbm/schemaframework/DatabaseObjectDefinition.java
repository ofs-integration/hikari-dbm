package com.orbitalforge.hikari.dbm.schemaframework;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import com.orbitalforge.hikari.dbm.exception.HikariDbmException;
import com.orbitalforge.hikari.dbm.platform.AbstractDbPlatform;

public abstract class DatabaseObjectDefinition {
	protected Map<String, Object> properties = new HashMap<String, Object>();

	@SuppressWarnings("unchecked")
	protected <T> T getProperty(String key, T defaultValue) {
		Object result = properties.get(key);
		if(result != null) return (T)result;
		return defaultValue;
	}
	
	protected void setProperty(String key, Object value) {
		properties.put(key, value);
	}
	
	public String getName() { return (String) properties.get("name"); }
	public void setName(String value) { setProperty("name", value); }
	
	public abstract Writer buildCreationWriter(AbstractDbPlatform platform, Writer writer) throws HikariDbmException, IOException;
	
	public abstract Writer buildDeletionWriter(AbstractDbPlatform platform, Writer writer) throws HikariDbmException, IOException;
}