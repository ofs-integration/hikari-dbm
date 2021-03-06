package com.orbitalforge.hikari.dbm.schemaframework;

public abstract class Constraint extends DatabaseObjectDefinition {
	public abstract String getConstraintType();
	
	public String getSchema() { return getProperty("sourceSchema", ""); }
	public void setSchema(String value) { setProperty("sourceSchema", value); }
	
	public String getTable() { return getProperty("sourceTable", ""); }
	public void setTable(String value) { setProperty("sourceTable", value); }
	
	public String getConstraintIdentifier() {
		return String.format("%s_%s", getConstraintType(), getName()).toUpperCase();
	}
}
