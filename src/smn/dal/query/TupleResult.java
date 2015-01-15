package smn.dal.query;


public class TupleResult {
	private Object[] tuple;
	
	@SuppressWarnings("unchecked")
	public <T> T get(int i) {
		return (T) tuple[i];
	}

}
