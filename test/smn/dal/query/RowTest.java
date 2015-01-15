package smn.dal.query;

import java.util.Date;

import smn.dal.model.Row;

public class RowTest {
	public void test(){
		Row row=new Row(5);
		row.set(0, 1);
		row.set(1, 2.3f);
		row.set(2, "zhang");
		row.set(3, true);
		row.set(4, "time", System.currentTimeMillis());
		
		assertTrue(1==row.getInt(0));
		assertError(row.get("name"));
		assertError(row.get(5));
		
		row.set(2, "name","zhangd");
		assertTrue(row.getString("name").equals("zhangd"));
	}
	
	public void test2(){
		Row row=new Row(1,2.3f,"zhangd",true,new Date());
		
		assertTrue(row.size()==5);
		assertError(row.get("name"));
		assertError(row.get(5));
		
		row.set(2, "name","zhangd");
		assertTrue(row.getString("name").equals("zhangd"));
	}

	private void assertError(Object object) {
		// TODO Auto-generated method stub
		
	}

	private void assertTrue(boolean b) {
		// TODO Auto-generated method stub
		
	}
}
