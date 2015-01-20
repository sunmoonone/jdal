package smn.dal.model;

import java.util.Date;

public class DateCol extends Column<Date>{
	/**
	 * create a date column
	 * @param options {string:colName} [KeyType:key] [int:len] [Value:default] [boolean:required] [string:desc]
	 */
	public DateCol(Object... options){
		_parseOptions(this, options);
	}
}
