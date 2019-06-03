package interpreter;

public class Main {

	public static void main ( String [ ] args ) {
		Context context = new Context ( ) ;
		// x = true && false || true;
		// y = true ^^ x;
		VarExpr x = new VarExpr ( "x" ) ;
		VarExpr y = new VarExpr ( "y" ) ;
		Expr e1 = new OrExpr (new AndExpr (new CstExpr ( true ) , new CstExpr (false) ) ,
		new CstExpr (true)) ;
		Expr e2 = new AssExpr (x , e1) ;
		EvalVisitor v = new EvalVisitor();
		PrefixVisitor p = new PrefixVisitor();
		PostfixVisitor po = new PostfixVisitor( );
		e2 . accept (po , null );
		System . out . println ( "> " + e2 . accept ( p , null ) );
		System . out . println ( "> x = " + e2 . accept ( v , context) );
		Expr e3 = new AssExpr ( y , new XorExpr (new CstExpr ( true ) , x ) );
		System . out . println ( "> " + e3 . accept ( p , null ) ) ;
		System . out . println ( "> y = " + e3 . accept ( v , context ) );
	}
}
