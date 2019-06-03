package interpreter;

public class OrExpr extends Expr {
	protected final Expr op1;
	
	protected final Expr op2;
	
	public  OrExpr(Expr a, Expr b) {
		// TODO Auto-generated constructor stub
		this.op1 = a;
		this.op2 = b;
	}

	@Override
	Object accept(Visitor v, Object o) {
		// TODO Auto-generated method stub
		return v.visitOr(this, o);
	}

}
