package smn.dal.model;


public class IntCol extends Column<Integer>{
	/**
	 * create a int column
	 * @param options {string:colName} [KeyType:key] [int:len] [Value:default] [boolean:required] [string:desc]
	 */
	public IntCol(Object... options){
		_parseOptions(this,options);
	}
}
