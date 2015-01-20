package smn.dal.model;

public class ShortCol extends Column<Short> {
	/**
	 * create a short column
	 * @param options {string:colName} [KeyType:key] [int:len] [Value:default] [boolean:required] [string:desc]
	 */
	public ShortCol(Object... options){
		_parseOptions(this, options);
	}
}
