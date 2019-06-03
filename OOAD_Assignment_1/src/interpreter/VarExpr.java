package interpreter;

public class VarExpr extends Expr {

	protected
	final
	String name ;
	public VarExpr ( String name ) {
	this . name = name ;
	}
	public String getName ( ) {
	return name ;
	}
	@Override
	public Object accept ( Visitor v , Object o ) {
	return v.visitVar(this , o ) ;
	}
}
 