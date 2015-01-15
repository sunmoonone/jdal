package smn.dal.model;


public class BooleanCol extends Column<Boolean>{
	/**
	 * create a boolean column
	 * @param options {string:colName} [KeyType:key] [int:len] [Value:default] [boolean:required] [string:desc]
	 */
	public BooleanCol(Object... options){
		_parseOptions(this, options);
	}
}
