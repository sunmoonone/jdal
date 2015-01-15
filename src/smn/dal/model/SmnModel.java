package smn.dal.model;

import java.lang.reflect.Field;
import java.util.Map;

import smn.dal.var;
import smn.dal.query.Clause;
import smn.dal.query.Query;

public abstract class SmnModel<PK> extends var{
	
	public Query query(){
		return new Query(this);
	}
	
	protected ColumnCollection __columns;

	public ColumnCollection __getColumns(){
		if(__columns==null){
			Class<?> cls=this.getClass();
			Field[] fields=cls.getDeclaredFields();
			
			if(fields==null || fields.length==0)return null;
			
			for(Field f :fields){
				if(f.getDeclaringClass().isAssignableFrom(Column.class)&& f.isAccessible()){
					try {
						__columns.add((Column<?>)f.get(this));
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return __columns;
	}
	
	public String __getTable(){
		String name=this.getClass().getName();
		StringBuilder sb=new StringBuilder();
		sb.append(name.charAt(0));
		
		for(int i=1;i<name.length();i++){
			int ask=(int)name.charAt(i);
			if(ask<95){
				sb.append('_');
				sb.append(ask);
			}else{
				sb.append(ask);
			}
		}
		return sb.toString().toUpperCase();
	}
	
	public boolean save(){
		return false;
	}
	
	public boolean update(){
		return false;
	}
	
	public boolean reload(){
		return false;
	}
	
	public <T> T get(PK pk){
		return null;
	}
	
	public <T> T copyThenGet() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean delete() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public Query filter(Clause... clause1) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * copy one db record
	 * @return
	 */
	public long copy() {
		// TODO Auto-generated method stub
		return 0;
	}
	

	public void createFrom(Query q, Map<String, String> map) {
		// TODO Auto-generated method stub
		
	}
}
