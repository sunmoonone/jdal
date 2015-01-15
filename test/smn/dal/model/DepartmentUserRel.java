package smn.dal.model;

import smn.dal.model.ColumnCollection;
import smn.dal.model.IntCol;
import smn.dal.model.SmnModel;

public class DepartmentUserRel extends SmnModel {
	
	public IntCol memberId=new IntCol("member_id");
	public IntCol departmentId=new IntCol("department_id");
	
	private ColumnCollection __columns;
	@Override
	public ColumnCollection __getColumns() {
		if(__columns==null){
			__columns=new ColumnCollection(memberId,departmentId);
		}
		return __columns;
	}

	@Override
	public String __getTable() {
		// TODO Auto-generated method stub
		return null;
	}

}
