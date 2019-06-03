package interpreter;

public class CstExpr  extends Expr{
	
	protected final boolean value;
	
	public CstExpr(boolean value) {
		// TODO Auto-generated constructor stub
		this.value = value;
	}

	@Override
	Object accept(Visitor v, Object o) {
		// TODO Auto-generated method stub
		return v.visitCst(this, o);
	}

}
