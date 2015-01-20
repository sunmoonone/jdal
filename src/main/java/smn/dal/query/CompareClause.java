package smn.dal.query;

public class CompareClause extends Clause {
	protected Expr left;
	protected String op;
	protected Expr right;
	
	public CompareClause(Expr colName, String op, Expr other) {
		this.left=colName;
		this.op=op;
		this.right=other;
	}
}
