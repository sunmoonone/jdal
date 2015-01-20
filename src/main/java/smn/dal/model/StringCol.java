package smn.dal.model;

public class StringCol extends Column<String> {
	/**
	 * create a string column
	 * @param options {string:colName} [KeyType:key] [int:len] [Value:default] [boolean:required] [string:desc]
	 */
	public StringCol(Object... options){
		_parseOptions(this, options);
	}
}
