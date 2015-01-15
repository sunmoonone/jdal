package smn.dal.query;

import java.util.ArrayList;
import java.util.List;

import smn.dal.var;
import smn.dal.model.Column;
import smn.dal.model.IntCol;
import smn.dal.model.SmnModel;
import smn.dal.model.StringCol;

public class Query extends var implements Cloneable {
	/*
	 * 
	 * SELECT
    [ALL | DISTINCT | DISTINCTROW ]
      [HIGH_PRIORITY]
      [STRAIGHT_JOIN]
      [SQL_SMALL_RESULT] [SQL_BIG_RESULT] [SQL_BUFFER_RESULT]
      [SQL_CACHE | SQL_NO_CACHE] [SQL_CALC_FOUND_ROWS]
    select_expr [, select_expr ...]
    [FROM table_references
      [PARTITION partition_list]
    [WHERE where_condition]
    [GROUP BY {col_name | expr | position}
      [ASC | DESC], ... [WITH ROLLUP]]
    [HAVING where_condition]
    [ORDER BY {col_name | expr | position}
      [ASC | DESC], ...]
    [LIMIT {[offset,] row_count | row_count OFFSET offset}]
    [PROCEDURE procedure_name(argument_list)]
    [INTO OUTFILE 'file_name'
        [CHARACTER SET charset_name]
        export_options
      | INTO DUMPFILE 'file_name'
      | INTO var_name [, var_name]]
    [FOR UPDATE | LOCK IN SHARE MODE]]
	 */
	private List<Clause> wheres=new ArrayList<Clause>(0);
	private List<SmnModel> models;
	
	public Query(SmnModel model) {
		if(models==null)models=new ArrayList<SmnModel>();
		models.add(model);
	}
	
	public Query leftJoin(SmnModel model,Column<?> a,Column<?> b){
		return this;
	}
	public Query rightJoin(SmnModel model,Column<?> a,Column<?> b){
		return this;
	}
	public Query innerJoin(SmnModel model,Column<?> a,Column<?> b){
		return this;
	}
	
	public Query union(Query other){
		return this;
	} 

	public Query filter(Clause... clause) {
		if(clause==null)return this;
		for(Clause c:clause){
			this.wheres.add(c);
		}
		return this;
	}

	public Query filterOr(Clause... clause) {
		if(clause==null)return this;
		this.wheres.add(new OrClause(clause));
		return this;
		
	}

	public <T> T find() {
		// TODO Auto-generated method stub
		return null;
	}

	public long delete() {
		// TODO Auto-generated method stub
		return 0;
	}

	public long update(SmnModel model) {
		// TODO Auto-generated method stub
		return 0;
	}

	public <T> T findScalar() {
		// TODO Auto-generated method stub
		return null;
	}

	public Query only(Expr... exp) {
		return this;
	}

	public Query exclude(Expr... exp) {
		// TODO Auto-generated method stub
		return this;
	}

	public Query only(SmnModel... model1) {
		// TODO Auto-generated method stub
		return this;
	}
	
	public Query exclude(SmnModel... model1) {
		// TODO Auto-generated method stub
		return this;
	}

	public <T> T findOne() {
		// TODO Auto-generated method stub
		return null;
	}

	public Query query(Expr... exp1) {
		// TODO Auto-generated method stub
		return this;
	}

	public static Expr alias(Expr exp1, Object exp2) {
		// TODO Auto-generated method stub
		return null;
	}

	public Query excludeAll() {
		// TODO Auto-generated method stub
		return null;
	}
	

	public long copy(SmnModel model){
		return 0;
	}

	public Query groupBy(Expr... expr1) {
		// TODO Auto-generated method stub
		return null;
	}

	public Query orderAsc(Expr... expr1) {
		// TODO Auto-generated method stub
		return null;
	}

	public Query orderDesc(Expr... expr1) {
		// TODO Auto-generated method stub
		return null;
	}

	public Query filterNothing() {
		// TODO Auto-generated method stub
		return null;
	}

	public long copy() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public Query clone(){
		return null;
	}
}
