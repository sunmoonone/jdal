package smn.dal.model;


import smn.dal.model.BooleanCol;
import smn.dal.model.ColumnCollection;
import smn.dal.model.DateCol;
import smn.dal.model.IntCol;
import smn.dal.model.KeyType;
import smn.dal.model.SmnModel;
import smn.dal.model.StringCol;
import smn.dal.query.Value;

public class MemberProfile extends SmnModel<Integer>{
	final public IntCol id = new IntCol("id",KeyType.Primary);
	final public IntCol memberId = new IntCol("member_id",KeyType.Unique,true);
	final public StringCol grade = new StringCol("grade","学历");
	final public StringCol compay = new StringCol("company");
	final public StringCol home = new StringCol("home");
	final public BooleanCol married = new BooleanCol("married",new Value(false));
	final public DateCol createTime = new DateCol("create_time");
	
	@Override
	public ColumnCollection __getColumns() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String __getTable() {
		// TODO Auto-generated method stub
		return null;
	}
}
