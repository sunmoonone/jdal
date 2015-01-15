package smn.dal.model;

import java.util.ArrayList;

public class ColumnCollection extends ArrayList<Column<?>> {
	private static final long serialVersionUID = -4134055079013505979L;

	public ColumnCollection(Column<?>... col1){
		if(col1==null)return;
		for(Column<?> c:col1){
			this.add(c);
		}
	}
}
