package smn.dal.test.model;

import smn.dal.model.ColumnCollection;
import smn.dal.model.SmnModel;
import smn.dal.model.DateCol;
import smn.dal.model.IntCol;
import smn.dal.model.StringCol;

public class Department extends SmnModel{
	public IntCol 		id=new IntCol("id");
	public StringCol 	name=new StringCol("name",true);
	public DateCol 		createTime=new DateCol("create_time");
	
	private ColumnCollection __columns;
	@Override
	public ColumnCollection __getColumns() {
		if(__columns==null){
			__columns=new ColumnCollection(id,name,createTime);
		}
		return __columns;
	}
	
	@Override
	public String __getTable() {
		return "group";
	}
	
}
