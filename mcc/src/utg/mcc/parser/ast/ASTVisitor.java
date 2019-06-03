package utg.mcc.parser.ast;

/** abstract visitor class for the ast */
public abstract class ASTVisitor {

	public abstract Object visitAdd(ASTAdd add, Object o);

	public abstract Object visitAnd(ASTAnd and, Object o);

	public abstract Object visitAssignStatement(
	    ASTAssignStatement assignStatement, Object o);

	public abstract Object visitBlock(ASTBlockStatement block, Object o);

	public abstract Object visitCall(ASTCall call, Object o);

	public abstract Object visitDiv(ASTDiv div, Object o);

	public abstract Object visitEq(ASTEq eq, Object o);

	public abstract Object visitFunDeclaration(
	    ASTFunDeclaration funDeclaration, Object o);

	public abstract Object visitGe(ASTGe ge, Object o);

	public abstract Object visitGt(ASTGt gt, Object o);

	public abstract Object visitIfStatement(ASTIfStatement ifStatement,
	    Object o);

	public abstract Object visitIntegerConstant(
	    ASTIntegerConstant integerConstant, Object o);

	public abstract Object visitLe(ASTLe le, Object o);

	public abstract Object visitLt(ASTLt lt, Object o);

	public abstract Object visitMul(ASTMul mul, Object o);

	public abstract Object visitNe(ASTNe ne, Object o);

	public abstract Object visitNeg(ASTNeg neg, Object o);

	public abstract Object visitNot(ASTNot not, Object o);

	public abstract Object visitOr(ASTOr or, Object o);

	public abstract Object visitParDeclaration(
	    ASTParDeclaration parDeclaration, Object o);

	public abstract Object visitPrintStatement(
	    ASTPrintStatement printStatement, Object o);

	public abstract Object visitProgram(ASTProgram program, Object o);

	public abstract Object visitReturnStatement(
	    ASTReturnStatement returnStatement, Object o);

	public abstract Object visitSub(ASTSub sub, Object o);

	public abstract Object visitVarDeclaration(
	    ASTVarDeclaration varDeclaration, Object o);

	public abstract Object visitVariable(ASTVariable variable, Object o);

	public abstract Object visitWhileStatement(
	    ASTWhileStatement whileStatement, Object o);

}
