package utg.mcc.translate;

import utg.mcc.parser.ast.ASTAnd;
import utg.mcc.parser.ast.ASTEq;
import utg.mcc.parser.ast.ASTGe;
import utg.mcc.parser.ast.ASTGt;
import utg.mcc.parser.ast.ASTIfStatement;
import utg.mcc.parser.ast.ASTLe;
import utg.mcc.parser.ast.ASTLt;
import utg.mcc.parser.ast.ASTNe;
import utg.mcc.parser.ast.ASTNot;
import utg.mcc.parser.ast.ASTOr;
import utg.mcc.parser.ast.ASTWhileStatement;

public class TFByteCodeGenerator extends ByteCodeGenerator {

  public TFByteCodeGenerator(String source) {
    super(source);
  }

  @Override
  public Object visitAnd(ASTAnd and, Object o) {
    TrueFalse tf = (o != null ? (TrueFalse) o : null);
    String lNext = label();
    m.verbose("> and");
    m.i();
    and.op1.accept(this, new TrueFalse(lNext, tf.f));
    eml(lNext);
    and.op2.accept(this, tf);
    m.d();
    m.verbose("< and");
    return null;
  }

  @Override
  public Object visitEq(ASTEq eq, Object o) {
    TrueFalse tf = (o != null ? (TrueFalse) o : null);
    m.verbose("> eq");
    m.i();
    eq.op1.accept(this, null);
    eq.op2.accept(this, null);
    emt("isub");
    as(-1);
    emt("ifeq " + tf.t);
    as(-1);
    emt("goto " + tf.f);
    m.d();
    m.verbose("< eq");
    return null;
  }

  @Override
  public Object visitGe(ASTGe ge, Object o) {
    TrueFalse tf = (o != null ? (TrueFalse) o : null);
    m.verbose("> ge");
    m.i();
    ge.op1.accept(this, null);
    ge.op2.accept(this, null);
    emt("isub");
    as(-1);
    emt("ifge " + tf.t);
    as(-1);
    emt("goto " + tf.f);
    m.d();
    m.verbose("< ge");
    return null;
  }

  @Override
  public Object visitGt(ASTGt gt, Object o) {
    TrueFalse tf = (o != null ? (TrueFalse) o : null);
    m.verbose("> gt");
    m.i();
    gt.op1.accept(this, null);
    gt.op2.accept(this, null);
    emt("isub");
    as(-1);
    emt("ifgt " + tf.t);
    as(-1);
    emt("goto " + tf.f);
    m.d();
    m.verbose("< gt");
    return null;
  }

  @Override
  public Object visitIfStatement(ASTIfStatement ifStatement, Object o) {
    String lTrue = label();
    String lFalse = label();
    String lEnd = label();
    TrueFalse tf = new TrueFalse(lTrue, lFalse);
    m.verbose("> if statement");
    m.i();
    emt("\t\t; start if");
    emt("\t\t; if condition");
    ifStatement.expression.accept(this, tf);
    emt("\t\t; true");
    eml(lTrue);
    ifStatement.thenStatement.accept(this, null);
    emt("goto " + lEnd);
    emt("\t\t; false");
    eml(lFalse);
    if (ifStatement.elseStatement != null) {
      ifStatement.elseStatement.accept(this, null);
    }
    emt("\t\t; end if");
    eml(lEnd);
    m.d();
    m.verbose("< if statement");
    return null;
  }

  @Override
  public Object visitLe(ASTLe le, Object o) {
    TrueFalse tf = (o != null ? (TrueFalse) o : null);
    m.verbose("> le");
    m.i();
    le.op1.accept(this, null);
    le.op2.accept(this, null);
    emt("isub");
    as(-1);
    emt("ifle " + tf.t);
    as(-1);
    emt("goto " + tf.f);
    m.d();
    m.verbose("< le");
    return null;
  }

  @Override
  public Object visitLt(ASTLt lt, Object o) {
    TrueFalse tf = (o != null ? (TrueFalse) o : null);
    m.verbose("> lt");
    m.i();
    lt.op1.accept(this, null);
    lt.op2.accept(this, null);
    emt("isub");
    as(-1);
    emt("iflt " + tf.t);
    as(-1);
    emt("goto " + tf.f);
    m.d();
    m.verbose("< lt");
    return null;
  }

  @Override
  public Object visitNe(ASTNe ne, Object o) {
    TrueFalse tf = (o != null ? (TrueFalse) o : null);
    m.verbose("> ne");
    m.i();
    ne.op1.accept(this, null);
    ne.op2.accept(this, null);
    emt("isub");
    as(-1);
    emt("ifne " + tf.t);
    as(-1);
    emt("goto " + tf.f);
    m.d();
    m.verbose("< ne");
    return null;
  }

  @Override
  public Object visitNot(ASTNot not, Object o) {
    TrueFalse tf = (o != null ? (TrueFalse) o : null);
    m.verbose("> not");
    m.i();
    not.op.accept(this, new TrueFalse(tf.f, tf.t));
    m.d();
    m.verbose("< not");
    return null;
  }

  @Override
  public Object visitOr(ASTOr or, Object o) {
    TrueFalse tf = (o != null ? (TrueFalse) o : null);
    String lNext = label();
    m.verbose("> or");
    m.i();
    or.op1.accept(this, new TrueFalse(tf.t, lNext));
    eml(lNext);
    or.op2.accept(this, tf);
    m.d();
    m.verbose("< or");
    return null;
  }

  @Override
  public Object visitWhileStatement(ASTWhileStatement whileStatement,
      Object o) {
    String lStart = label();
    String lEnd = label();
    String lTrue = label();
    eml(lStart);
    TrueFalse tf = new TrueFalse(lTrue, lEnd);
    m.verbose("> while statement");
    m.i();
    emt("\t\t; start while");
    emt("\t\t; while condition");
    whileStatement.expression.accept(this, tf);
    emt("\t\t; true");
    eml(lTrue);
    whileStatement.statement.accept(this, null);
    emt("goto " + lStart);
    emt("\t\t; end if");
    eml(lEnd);
    m.d();
    m.verbose("< while statement");
    return null;
  }
}
