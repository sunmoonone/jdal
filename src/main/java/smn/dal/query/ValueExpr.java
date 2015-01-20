package smn.dal.query;

public class ValueExpr extends Expr{
	protected Object value;
	
	public ValueExpr(Object obj){
		this.value=obj;
	}
}
