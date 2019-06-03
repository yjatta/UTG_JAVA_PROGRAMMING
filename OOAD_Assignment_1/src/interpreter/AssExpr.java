package interpreter;

public class AssExpr extends Expr{
	VarExpr var;
	Expr expr;
	
	public AssExpr(VarExpr var, Expr expr) {
		// TODO Auto-generated constructor stub
		this.var = var;
		this.expr = expr;
	}

	@Override
	Object accept(Visitor v, Object o) {
		// TODO Auto-generated method stub
		return v.visitAss(this, o);
	}

	
}
