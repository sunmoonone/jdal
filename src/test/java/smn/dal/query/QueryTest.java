package smn.dal.query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import smn.dal.model.AliasCol;
import smn.dal.model.ColumnMap;
import smn.dal.model.Row;
import smn.dal.query.Query;
import smn.dal.query.TupleResult;
import smn.dal.sql.SqlFn;
import smn.dal.model.Department;
import smn.dal.model.DepartmentUserRel;
import smn.dal.model.Member;
import smn.dal.model.MemberProfile;
import static smn.utils.Functions.*;

public class QueryTest {
	public void testQuery(){
		Member t=new Member();
		Query q=t.query();
		
		q.filter(t.name.contains("%smith")).filter(t.mobile.eq("135555550099"));
		q.filter(t.age.gt(30),t.sex.eq('m'),t.enabled.eq(true));
		q.filterOr(t.region.in(set("shanghai","guangdong","beijing")),t.school.notNull());
		//TODO t.balance < t.age+1
		q.orderAsc(t.name,t.age).orderDesc(t.region);
		List<Member> ms=q.find();
	}
	
	public void testQueryExpr(){
		Member t=new Member();
		Query q=t.query();
		
		q.query(SqlFn.if_(t.school.isNull(),"unknown school",t.school));
		q.query(SqlFn.case_(t.sex).when('m',"male").when('f',"female").end("unknow"));
		q.query(SqlFn.notnull(t.region,"N/A"));
		q.query(t.age.alias("grownUp",SqlFn.if_(t.age.gt(18), "yes", "no")));
		
		q.filter(t.name.contains("%smith")).filter(t.mobile.eq("135555550099"));
		q.filter(t.age.gt(30),t.sex.eq('m'),t.enabled.eq(true));
		q.filterOr(t.region.in(set("shanghai","guangdong","beijing")),t.school.notNull());
		
		List<Member> ms=q.find();
	}

	public void testJoinQuery(){
		Member m=new Member();
		Department d=new Department();
		DepartmentUserRel dmr=new DepartmentUserRel();
		
		List<TupleResult> rows=m.query().leftJoin(dmr, m.id, dmr.memberId).
				leftJoin(d, dmr.departmentId, d.id).
				only(m,d).
				find();
		
		for(TupleResult r:rows){
			Member member=r.get(0);
			Department depart=r.get(1);
		}
	}
	
	public void testScalar(){
		Member member=new Member();
		Query q= new Query(member);
		
		q.filter(member.id.eq(1)).only(member.name,member.id);
		String name=q.findScalar();
	}
	
	public void testSubQuery(){
		
	}
	
	public void testOnlyQuery(){
		Member member=new Member();
		Query q= new Query(member);
		
		q.filter(member.id.eq(1)).only(member.name,member.id);
		List<Member> ms=q.find();
	}
	
	public void testExcludeQuery(){
		Member member=new Member();
		Query q= new Query(member);
		
		q.filter(member.id.eq(1)).exclude(member.name,member.id);
		List<Member> ms=q.find();
	}
	
	public void testSelectOne(){
		Member member=new Member();
		Query q= new Query(member);
		
		q.filter(member.id.gt(1)).exclude(member.name,member.id);
		Member one=q.findOne();
		
		Member m=new Member();
		Department d=new Department();
		DepartmentUserRel dmr=new DepartmentUserRel();
		
		TupleResult row=m.query().leftJoin(dmr, m.id, dmr.memberId).
				leftJoin(d, dmr.departmentId, d.id).
				only(m,d).
				findOne();
		
		Member oneMember=row.get(0);
		Department oneDepart=row.get(1);
	}
	
	public void testFunctions(){
		//count
		//sum

		Member t=new Member();
		Query q=t.query();
		
		q.filter(t.age.gt(30),t.sex.eq('m'),t.enabled.eq(true));
		q.query(SqlFn.count());
		long count=q.find();
		
		q.excludeAll().query(SqlFn.count(t.id));
		count=q.find();
		
		Query q2=q.clone();
		q2.filterNothing().groupBy(t.school);
		q2.only(SqlFn.sum(t.balance),t.school).orderDesc(t.school);
		Row row=q2.findOne();
	
		assertTrue(row.getFloat(0).compareTo(0f)>0);
		assertEqual(row.getString("school").equals("henu"));
	}
	
	private void assertEqual(boolean equals) {
		// TODO Auto-generated method stub
		
	}

	private void assertTrue(boolean b) {
		// TODO Auto-generated method stub
		
	}

	public void testUpdate(){
		Member t=new Member();
		Query q=t.query();
		
		q.filter(t.age.gt(30),t.sex.eq('m'),t.enabled.eq(true));
		q.filterOr(t.region.in(set("shanghai","guangdong","beijing")),t.school.notNull());
	
		t.setAge(40);
		t.setSchool("henan");
		t.setSex('f');
		//TODO set age=age+1
		long rows=q.update(t);
		
		t=t.filter(t.balance.lt(20f)).findOne();
		t.balance.setValue(100f);
		
		t.update();
	}
	
	public void testDelete(){
		Member t=new Member();
		Query q=t.query();
		
		q.filter(t.name.contains("%smith"));
		
		long rows=q.delete();
	}
	
	public void testSave(){
		Member m=new Member();
		m.setName("zhang dan1");
		m.setAge(29);
		m.setSex('m');
		
		m.save();//insert
		
		m.setAge(30);
		m.save();//update
	}
	
	public void testDeleteModel(){
		Member m=new Member().get(1);
		if(m.getAge()>90)m.delete();
	}
	
	public void testReload(){
		Member m1=new Member().get(2);
		
		Member m2=new Member().get(2);
		assertTrue(m1.age.veq(m2.age));
		
		m1.setAge(m1.getAge()+1);
		m1.update();
		
		assertTrue(m1.age.vgt(m2.age));
		assertTrue(m1.age.vneq(m2.age));
		
		m2.reload();
		assertTrue(m1.age.veq(m2.age));
	}
	
	public void testCopy(){
		//clone a member
		Member m1=new Member().get(2);
		m1.setAge(1);
		m1.setSex('m');
		m1.setName(m1.name.val()+" Number 2");
		
		long count=m1.copy();
		Member m2=m1.copyThenGet();
		
		//clone all girls
		Member m=new Member();
		//set cloned girls' balance to 0
		m.balance.setValue(0f);
		long count1=m.filter(m.enabled.eq(true),m.sex.eq('f')).
				//set cloned girls' name to orginal name+cloned
				query(m.name.alias("name","name+' cloned'")).
				//set cloned girls' age to orginal age-1
				query(m.age.alias("age","age=age-1")).copy(m);
		
		//clone all boys
		long count2=m.filter(m.enabled.eq(true),m.sex.eq('m')).
				//set cloned boys' balance to 0
				query(m.balance.alias("balance","0")).
				//set cloned boys' name to orginal name+cloned
				query(m.name.alias("name","name+' cloned'")).
				//set cloned boys' age to orginal age-1
				query(m.age.alias("age","age=age-1")).copy();
	}
	
	public void testCopy2(){
		//create memberProfile for all enabled members and set grade to college
		Member m=new Member();
		Query q=m.filter(m.enabled.eq(true));
		MemberProfile mp=new MemberProfile();
		mp.grade.val("college");
		
		Map<String,String> map=new HashMap<String,String>();
		map.put("id","memberId");
		map.put("createTime","createTime");
		
		mp.createFrom(q,map);
	}
	
	public void testLazy(){
		
	}
}
