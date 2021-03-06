SELECT 
	TABLE_CATALOG, 
	TABLE_SCHEMA, 
	TABLE_NAME,
	TABLE_TYPE
FROM INFORMATION_SCHEMA.TABLES
WHERE
	TABLE_CATALOG = :catalog AND 
	TABLE_SCHEMA = :schema AND
	TABLE_NAME = :table