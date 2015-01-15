package smn.dal.model;

public class ByteCol extends Column<Byte> {
	/**
	 * create a byte column
	 * @param options {string:colName} [KeyType:key] [int:len] [Value:default] [boolean:required] [string:desc]
	 */
	public ByteCol(Object... options){
		_parseOptions(this, options);
	}
}
