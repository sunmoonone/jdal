package smn.dal.model;

import smn.dal.model.BinaryCol;
import smn.dal.model.CharCol;
import smn.dal.model.FloatCol;
import smn.dal.model.KeyType;
import smn.dal.model.SmnModel;
import smn.dal.model.BooleanCol;
import smn.dal.model.DateCol;
import smn.dal.model.IntCol;
import smn.dal.model.StringCol;
import smn.dal.query.Value;

public class Member extends SmnModel<Integer>{

	final public IntCol id = new IntCol("id",KeyType.Primary);
	final public StringCol name = new StringCol("name",10,true);
	final public IntCol age = new IntCol("age",Value.create(0));
	final public CharCol sex = new CharCol("sex","性别");
	final public StringCol mobile = new StringCol("mobile",13,"电话",true);
	final public BooleanCol enabled = new BooleanCol("enabled",new Value(true));
	final public StringCol region = new StringCol("region");
	final public StringCol school = new StringCol("school");
	final public FloatCol balance = new FloatCol("balance",Value.create(0f));
	final public BinaryCol img 	=new BinaryCol("img");
	final public DateCol createTime = new DateCol("create_time");

	public String __getTable(){
		return "member";
	}
	
	public Integer getId() {
		return id.getValue();
	}

	public void setId(Integer id) {
		this.id.setValue(id);
	}

	public String getName() {
		return name.getValue();
	}

	public void setName(String name) {
		this.name.setValue(name);
	}

	public Integer getAge() {
		return age.getValue();
	}

	public void setAge(Integer age) {
		this.age.setValue(age);
	}

	public char getSex() {
		return sex.getValue();
	}

	public void setSex(char sex) {
		this.sex.setValue(sex);
	}

	public String getMobile() {
		return mobile.getValue();
	}

	public void setMobile(String mobile) {
		this.mobile.setValue(mobile);
	}

	public boolean isEnabled() {
		return enabled.getValue();
	}

	public void setEnabled(boolean enabled) {
		this.enabled.setValue(enabled);
	}

	public String getRegion() {
		return region.getValue();
	}

	public void setRegion(String region) {
		this.region.setValue(region);
	}

	public String getSchool() {
		return school.getValue();
	}

	public void setSchool(String school) {
		this.school.setValue(school);
	}

}
