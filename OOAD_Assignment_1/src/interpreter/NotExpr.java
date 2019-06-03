package interpreter;

public  class NotExpr extends Expr {
	
	protected final Expr value;
	
	public NotExpr(Expr a) {
		// TODO Auto-generated constructor stub
		
		this.value = a;
	}

	@Override
	Object accept(Visitor v, Object o) {
		// TODO Auto-generated method stub
		return v.visitNot(this, o);
	}

}
