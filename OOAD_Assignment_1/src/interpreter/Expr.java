package interpreter;

public abstract class Expr {

	
	abstract Object accept ( Visitor v , Object o ) ;
}
