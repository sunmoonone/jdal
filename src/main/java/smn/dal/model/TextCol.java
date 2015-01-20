package smn.dal.model;

public class TextCol extends Column<String> {
	/**
	 * create a text column
	 * @param options {string:colName} [KeyType:key] [int:len] [Value:default] [boolean:required] [string:desc]
	 */
	public TextCol(Object... options){
		_parseOptions(this, options);
	}
}
