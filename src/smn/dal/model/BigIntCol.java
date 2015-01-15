package smn.dal.model;

public class BigIntCol extends Column<Long> {
	/**
	 * create a bigint column
	 * @param options {string:colName} [KeyType:key] [int:len] [Value:default] [boolean:required] [string:desc]
	 */
	public BigIntCol(Object... options){
		_parseOptions(this, options);
	}
}
