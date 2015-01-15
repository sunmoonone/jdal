package smn.dal.model;

public class CharCol extends Column<Character> {
	/**
	 * create a char column
	 * @param options {string:colName} [KeyType:key] [int:len] [Value:default] [boolean:required] [string:desc]
	 */
	public CharCol(Object... options){
		_parseOptions(this, options);
	}
}
