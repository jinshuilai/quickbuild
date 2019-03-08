package com.lai.generator;

import java.util.Arrays;
import java.util.List;

public class TableDao {

	private JdbcUtils db;
	
	public TableDao() {
		db = new JdbcUtils();
		db.getConnection();
	}
	
	public List<Columns> getColumnsByTable(String tableName) throws Exception{
		String sql = "select COLUMN_NAME name,DATA_TYPE type from information_schema.columns "+""
				+ "where table_name= ? and TABLE_SCHEMA = 'test'";
		List<Columns> result = db.findMoreRefResult(sql, Arrays.asList(new Object[]{tableName}), Columns.class);
		for (Columns columns : result) {
			columns.setFiled(CodeGenerator.tableNameConvertLowerCamel(columns.getName()));
			columns.setFiledUp(CodeGenerator.tableNameConvertUpperCamel(columns.getName()));
			String type = columns.getType();
			if("varchar".equals(type)){
				type = "String";
			}else if("int".equals(type)){
				type = "Integer";
			}else if("datetime".equals(type)){
				type = "Date";
			}
			columns.setJavaType(type);
		}
		return result;
	}
	
	public static void main(String[] args) throws Exception {
		TableDao dao = new TableDao();
		List<Columns> list = dao.getColumnsByTable("user_info");
		System.out.println(list);
	}
}
