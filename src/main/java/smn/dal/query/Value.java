package smn.dal.query;

public class Value extends Expr{
	public static Value Null=new Value();
	
	private Object value;

	public Value(){
		
	}
	
	public Value(Object value){
		this.value=value;
	}
	
	public static Value create(Object value){
		return new Value(value);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getValue(){
		return (T)this.value;
	}
	
	public boolean isNull(){
		return value==null;
	}
	
}
