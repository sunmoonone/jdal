package smn.dal.model;

public class BinaryCol extends Column<Byte[]>{
	/**
	 * create a binary column
	 * @param options {string:colName} [KeyType:key] [int:len] [Value:default] [boolean:required] [string:desc]
	 */
	public BinaryCol(Object... options){
		_parseOptions(this, options);
	}
}
