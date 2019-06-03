package interpreter;

public class PrefixVisitor extends Visitor {

	@Override
	public Object visitAss(AssExpr a, Object o) {
		// TODO Auto-generated method stub
		return " = " + a.var . name + " " + a . expr . accept ( this , o ) + " ";
	}

	@Override
	public Object visitAnd(AndExpr a, Object o) {
		// TODO Auto-generated method stub
		return "&& "+ a.op1.accept(this, o) + a.op2.accept(this, o)+ " ";
	}

	@Override
	public Object visitCst(CstExpr c, Object o) {
		// TODO -generated method stub
		return c.value + " ";
	}

	@Override
	public Object visitNot(NotExpr d, Object o) {
		// TODO Auto-generated method stub
		return "! "+ d.value.accept(this, o)+ " ";
	}

	@Override
	public Object visitOr(OrExpr m, Object o) {
		// TODO Auto-generated method stub
		return "|| "+ m.op1.accept(this, o) + m.op2.accept(this, o)+ " ";
	}

	@Override
	public Object visitXor(XorExpr m, Object o) {
		// TODO Auto-generated method stub
		return "^^ "+ m.op1.accept(this, o) + m.op2.accept(this, o)+ " ";
	}

	@Override
	public Object visitVar(VarExpr v, Object o) {
		// TODO Auto-generated method stub
		return v.name + " ";
	}


}
