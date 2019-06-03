package interpreter;

public class EvalVisitor extends Visitor {

	@Override
	public Object visitAnd(AndExpr a, Object o) {
		// TODO Auto-generated method stub
		boolean i = (Boolean)a.op1.accept(this, o);
		boolean j = (Boolean)a.op2.accept(this, o);
		return i && j;
	}

	@Override
	public Object visitCst(CstExpr c, Object o) {
		// TODO Auto-generated method stub
		return c.value;
	}

	@Override
	public Object visitNot(NotExpr d, Object o) {
		// TODO Auto-generated method stub
		return !(Boolean) d.value.accept(this, o);
	}

	@Override
	public Object visitOr(OrExpr m, Object o) {
		// TODO Auto-generated method stub
		boolean i = (Boolean) m.op1.accept(this, o);
		boolean j = (Boolean) m.op2.accept(this, o);
		return i || j;
	}

	@Override
	public Object visitXor(XorExpr m, Object o) {
		// TODO Auto-generated method stub
		boolean i = (Boolean) m.op1.accept(this, o);
		boolean j = (Boolean) m.op2.accept(this, o);
		return i != j;
	}

	@Override
	public Object visitVar(VarExpr v, Object o) {
		// TODO Auto-generated method stub
		Context c = ( Context ) o ;
		return c.lookup(v.name);
	}

	@Override
	public Object visitAss(AssExpr a, Object o) {
		// TODO Auto-generated method stub
		Context c = ( Context ) o;
		boolean i = (Boolean) a.expr.accept(this, o);
		c.assign(a.var.getName(),i);
		return i;
	}

}
