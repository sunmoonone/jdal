package smn.dal.model;

import java.util.Collection;

import smn.dal.query.Clause;
import smn.dal.query.CompareClause;
import smn.dal.query.Expr;
import smn.dal.query.NullExpr;
import smn.dal.query.SqlExpr;
import smn.dal.query.Value;
import smn.dal.query.ValueExpr;

public abstract class Column<T> extends Expr{
	protected String colName;
	protected KeyType key;
	protected int len;
	protected String desc;
	protected boolean required;
	protected T defaultV;
	protected T value;
	
	protected Expr colExpr;
	
	//{string:colName} [KeyType:key] [int:len] [Value:default] [boolean:required] [string:desc]
	protected static void _parseOptions(Column<?> col,Object[] options){
		col.colName=(String)options[0];
		col.colExpr=new SqlExpr(col.colName);
		
		for(int i=1,len=options.length;i<len;i++){
			if(options[i].getClass().isAssignableFrom(KeyType.class)){
				col.key=(KeyType) options[i];
				
			}else if(options[i].getClass().isAssignableFrom(int.class)){
				col.len=(Integer) options[i];
			}else if(options[i].getClass().isAssignableFrom(String.class)){
				col.desc=(String) options[i];
				
			}else if(options[i].getClass().isAssignableFrom(boolean.class)){
				col.required=(Boolean) options[i];
				
			}else if(options[i].getClass().isAssignableFrom(Value.class)){
				col.defaultV=((Value) options[i]).getValue();
			}
		}
	}
	
	public Clause eq(T other){
		
		return new CompareClause(colExpr,"==",this.toExpr(other));
	}
	
	final protected Expr toExpr(T other) {
		if(other==null){
			return new NullExpr();
		}else if(other.getClass().isAssignableFrom(Expr.class)){
			return (Expr)other;
		}else{
			return new ValueExpr(other);
		}
	}


	public Clause lt(T other){
		return new CompareClause(colExpr,"<",this.toExpr(other));
	}
	
	public Clause lte(T other){
		return new CompareClause(colExpr,"<=",this.toExpr(other));
	}
	
	public Clause gt(T other){
		return new CompareClause(colExpr,">",this.toExpr(other));
	}
	
	public Clause gte(T other){
		return new CompareClause(colExpr,">=",this.toExpr(other));
	}
	
	public Clause neq(T other){
		return new CompareClause(colExpr,"!=",this.toExpr(other));
	}
	
	public Clause contains(String other){
		return new CompareClause(colExpr,"like",new SqlExpr("%"+other.toString()+"%"));
	}
	
	public Clause startsWith(String other){
		return new CompareClause(colExpr,"like",new SqlExpr(other.toString()+"%"));
	}
	
	public Clause endsWith(String other){
		return new CompareClause(colExpr,"like",new SqlExpr("%"+other.toString()));
	}
	
	public Clause in(Collection<T> collection) {
		return new CompareClause(colExpr,"in",new ValueExpr(collection));
	}
	
	public Clause notNull() {
		return new CompareClause(colExpr,"is", new SqlExpr("not null"));
	}
	
	public Clause isNull() {
		return new CompareClause(colExpr,"is", new SqlExpr("null"));
	}
	
	public T getValue(){
		if(this.value==null)return this.defaultV;
		return this.value;
	}
	
	public T val(){
		if(this.value==null)return this.defaultV;
		return this.value;
	}
	
	public void setValue(T value){
		this.value=value;
	}
	
	public void val(T value){
		this.value=value;
	}
	/**
	 * test equality of columns values
	 * @param other
	 * @return
	 */
	public boolean veq(Column<T> other) {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean veq(T other) {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * test equality of columns values
	 * @param other
	 * @return
	 */
	public boolean vgt(Column<T> other) {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean vgt(T other) {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * test equality of columns values
	 * @param other
	 * @return
	 */
	public boolean vlt(Column<T> other) {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean vlt(T other) {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * test equality of columns values
	 * @param other
	 * @return
	 */
	public boolean vlte(Column<T> other) {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean vlte(T other) {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * test equality of columns values
	 * @param other
	 * @return
	 */
	public boolean vgte(Column<T> other) {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean vgte(T other) {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * test equality of columns values
	 * @param other
	 * @return
	 */
	public boolean vneq(Column<T> other) {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean vneq(T other) {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean vempty() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public Column<T> alias(String name,String exp){
		return new AliasCol<T>(name,exp);
	}
	
	public Column<T> alias(String name){
		return new AliasCol<T>(name);
	}
	
	public Expr alias(String name, Expr exp) {
		// TODO Auto-generated method stub
		return null;
	}
}
