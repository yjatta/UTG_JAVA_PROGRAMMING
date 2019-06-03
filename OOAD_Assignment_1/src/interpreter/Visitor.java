package interpreter;

public abstract class Visitor {

	public abstract Object visitAss (AssExpr a , Object o ) ;
	public abstract Object visitAnd (AndExpr a , Object o ) ;
	public abstract Object  visitCst (CstExpr c , Object o ) ;
	public abstract Object visitNot (NotExpr d , Object o ) ;
	public abstract Object visitOr (OrExpr m , Object o ) ;
	public abstract Object visitXor (XorExpr m , Object o ) ;
	//public abstract Object visitNeg ( NegExpr n , Object o ) ;
	//public abstract Object visitPlus ( PlusExpr p , Object o) ;
	public abstract Object visitVar(VarExpr v , Object o );

}
