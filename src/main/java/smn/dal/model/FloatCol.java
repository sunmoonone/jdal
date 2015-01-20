package smn.dal.model;

public class FloatCol extends Column<Float>{
	/**
	 * create a float column
	 * @param options {string:colName} [KeyType:key] [int:len] [Value:default] [boolean:required] [string:desc]
	 */
	public FloatCol(Object... options){
		_parseOptions(this, options);
	}
}
