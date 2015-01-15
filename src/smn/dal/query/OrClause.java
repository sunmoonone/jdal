package smn.dal.query;

public class OrClause extends Clause {
	private Clause[] clauses;
	public OrClause(Clause[] clause) {
		clauses=clause;
	}
}
