package utg.mcc.semantic;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import utg.mcc.parser.ast.ASTAdd;
import utg.mcc.parser.ast.ASTAnd;
import utg.mcc.parser.ast.ASTAssignStatement;
import utg.mcc.parser.ast.ASTBlockStatement;
import utg.mcc.parser.ast.ASTCall;
import utg.mcc.parser.ast.ASTDiv;
import utg.mcc.parser.ast.ASTEq;
import utg.mcc.parser.ast.ASTFunDeclaration;
import utg.mcc.parser.ast.ASTGe;
import utg.mcc.parser.ast.ASTGt;
import utg.mcc.parser.ast.ASTIfStatement;
import utg.mcc.parser.ast.ASTIntegerConstant;
import utg.mcc.parser.ast.ASTLe;
import utg.mcc.parser.ast.ASTLt;
import utg.mcc.parser.ast.ASTMul;
import utg.mcc.parser.ast.ASTNe;
import utg.mcc.parser.ast.ASTNeg;
import utg.mcc.parser.ast.ASTNot;
import utg.mcc.parser.ast.ASTOr;
import utg.mcc.parser.ast.ASTParDeclaration;
import utg.mcc.parser.ast.ASTPrintStatement;
import utg.mcc.parser.ast.ASTProgram;
import utg.mcc.parser.ast.ASTReturnStatement;
import utg.mcc.parser.ast.ASTSub;
import utg.mcc.parser.ast.ASTVarDeclaration;
import utg.mcc.parser.ast.ASTVariable;
import utg.mcc.parser.ast.ASTVisitor;
import utg.mcc.parser.ast.ASTWhileStatement;
import utg.mcc.translate.TrueFalse;
import utg.utils.Message;

/**
 * this class is responsible for type checking, the allocation of virtual
 * addresses and the translation into intermediate code.
 * 
 * the class uses TIPair objects that wrap an intermediate code subtree together
 * with its type information
 */
public class ByteCodeGenerator extends ASTVisitor {

  private final Message m = new Message("bytecode generator");

  private FileWriter out;

  private String source = null;

  private int maxStack = 0;

  private int actStack = 0;

  private int labels = 0;

  public ByteCodeGenerator(String source) {
    super();
    this.source = source;
    try {
      this.out = new FileWriter(new File("A.j"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * this method updates the java stack maximal height
   * 
   * @param i
   *          the actual stack increment (decrement)
   */
  public void as(int i) {
    actStack += i;
    if (actStack > maxStack)
      maxStack = actStack;
  }

  /**
   * this method is used for writing the target code
   * 
   * @param s
   *          the target instruction string
   */
  public void emit(String s) {
    try {
      out.write(s + "\n");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void em(String s) {
    emit(s);
  }

  private void eml(String s) {
    emit(s + ":");
  }

  private void emt(String s) {
    emit("\t" + s);
  }

  private String label() {
    return "L" + labels++;
  }

  @Override
  public Object visitAdd(ASTAdd add, Object o) {
    TrueFalse tf = (o != null ? (TrueFalse) o : null);
    add.op1.accept(this, tf);
    add.op2.accept(this, tf);
    emt("iadd");
    as(-1);
    return null;
  }

  @Override
  public Object visitAnd(ASTAnd and, Object o) {
    TrueFalse tf = (o != null ? (TrueFalse) o : null);
    String next = label();
    and.op1.accept(this, new TrueFalse(next, tf.f));
    eml(next);
    and.op2.accept(this, tf);
    return null;
  }

  @Override
  public Object visitAssignStatement(
      ASTAssignStatement assignStatement, Object o) {
    assignStatement.expression.accept(this, null);
    ASTVariable variable = assignStatement.variable;
    if (variable.entry.global) {
      emt("putstatic A/" + variable.entry.name + " "
          + variable.entry.type.signature());
    } else {
      emt("istore " + variable.entry.address);
    }
    as(-1);
    return null;
  }

  @Override
  public Object visitBlock(ASTBlockStatement block, Object o) {
    TrueFalse tf = (o != null ? (TrueFalse) o : null);
    m.verbose("> block");
    m.i();
    for (int i = 0; i < block.statements.size(); i++) {
      block.statements.elementAt(i).accept(this, tf);
    }
    m.d();
    m.verbose("< block");
    return null;
  }

  @Override
  public Object visitCall(ASTCall call, Object o) {
    m.verbose("> call [" + call.name + "]");
    m.i();
    m.verbose("resolving " + call.name);
    for (int i = 0; i < call.parameters.size(); i++) {
      call.parameters.elementAt(i).accept(this, null);
    }
    emt("invokestatic utg/mcc/bytecode/A/" + call.entry.name
        + call.entry.type.signature());
    as(1 - call.entry.formals);
    m.d();
    m.verbose("< call");
    return null;
  }

  @Override
  public Object visitDiv(ASTDiv div, Object o) {
    TrueFalse tf = (o != null ? (TrueFalse) o : null);
    div.op1.accept(this, tf);
    div.op2.accept(this, tf);
    emt("idiv");
    as(-1);
    return null;
  }

  @Override
  public Object visitEq(ASTEq eq, Object o) {
    TrueFalse tf = (o != null ? (TrueFalse) o : null);
    eq.op1.accept(this, o);
    eq.op2.accept(this, o);
    emt("isub");
    as(-1);
    emt("ifeq " + tf.t);
    as(-1);
    emt("goto " + tf.f);
    return null;
  }

  @Override
  public Object visitFunDeclaration(ASTFunDeclaration funDeclaration,
      Object o) {
    actStack = 0;
    maxStack = 0;
    em(".method public static " + funDeclaration.name
        + funDeclaration.entry.type.signature());
    funDeclaration.block.accept(this, null);
    em(".limit locals " + funDeclaration.entry.addresses);
    em(".limit stack " + maxStack);
    em(".end method");
    em("");
    return null;
  }

  @Override
  public Object visitGe(ASTGe ge, Object o) {
    TrueFalse tf = (o != null ? (TrueFalse) o : null);
    ge.op1.accept(this, o);
    ge.op2.accept(this, o);
    emt("isub");
    as(-1);
    emt("ifge " + tf.t);
    as(-1);
    emt("goto " + tf.f);
    return null;
  }

  @Override
  public Object visitGt(ASTGt gt, Object o) {
    TrueFalse tf = (o != null ? (TrueFalse) o : null);
    gt.op1.accept(this, o);
    gt.op2.accept(this, o);
    emt("isub");
    as(-1);
    emt("ifgt " + tf.t);
    as(-1);
    emt("goto " + tf.f);
    return null;
  }

  @Override
  public Object visitIfStatement(ASTIfStatement ifStatement, Object o) {
    String lthen = label();
    String lelse = label();
    String lend = label();
    ifStatement.expression.accept(this, new TrueFalse(lthen, lelse));
    eml(lthen);
    ifStatement.thenStatement.accept(this, null);
    // emt("goto " + lend);
    eml(lelse);
    if (ifStatement.elseStatement != null) {
      ifStatement.elseStatement.accept(this, null);
    }
    eml(lend);
    return null;
  }

  @Override
  public Object visitIntegerConstant(
      ASTIntegerConstant integerConstant, Object o) {
    emt("ldc " + integerConstant.value);
    as(1);
    return null;
  }

  @Override
  public Object visitLe(ASTLe le, Object o) {
    TrueFalse tf = (o != null ? (TrueFalse) o : null);
    le.op1.accept(this, o);
    le.op2.accept(this, o);
    emt("isub");
    as(-1);
    emt("ifle " + tf.t);
    as(-1);
    emt("goto " + tf.f);
    return null;
  }

  @Override
  public Object visitLt(ASTLt lt, Object o) {
    TrueFalse tf = (o != null ? (TrueFalse) o : null);
    lt.op1.accept(this, o);
    lt.op2.accept(this, o);
    emt("isub");
    as(-1);
    emt("iflt " + tf.t);
    as(-1);
    emt("goto " + tf.f);
    return null;
  }

  @Override
  public Object visitMul(ASTMul mul, Object o) {
    TrueFalse tf = (o != null ? (TrueFalse) o : null);
    mul.op1.accept(this, tf);
    mul.op2.accept(this, tf);
    emt("imul");
    as(-1);
    return null;
  }

  @Override
  public Object visitNe(ASTNe ne, Object o) {
    TrueFalse tf = (o != null ? (TrueFalse) o : null);
    ne.op1.accept(this, o);
    ne.op2.accept(this, o);
    emt("isub");
    as(-1);
    emt("ifne " + tf.t);
    as(-1);
    emt("goto " + tf.f);
    return null;
  }

  @Override
  public Object visitNeg(ASTNeg neg, Object o) {
    TrueFalse tf = (o != null ? (TrueFalse) o : null);
    neg.op.accept(this, tf);
    emt("ineg");
    return null;
  }

  @Override
  public Object visitNot(ASTNot not, Object o) {
    TrueFalse tf = (o != null ? (TrueFalse) o : null);
    not.op.accept(this, new TrueFalse(tf.f, tf.t));
    return null;
  }

  @Override
  public Object visitOr(ASTOr or, Object o) {
    TrueFalse tf = (o != null ? (TrueFalse) o : null);
    String next = label();
    or.op1.accept(this, new TrueFalse(tf.t, next));
    eml(next);
    or.op2.accept(this, tf);
    return null;
  }

  @Override
  public Object visitParDeclaration(ASTParDeclaration parDeclaration,
      Object o) {
    return null;
  }

  @Override
  public Object visitPrintStatement(ASTPrintStatement printStatement,
      Object o) {
    emt("getstatic java/lang/System/out Ljava/io/PrintStream;");
    as(1);
    printStatement.expression.accept(this, o);
    emt("invokevirtual java/io/PrintStream/println(I)V");
    as(-1);
    return null;
  }

  @Override
  public Object visitProgram(ASTProgram program, Object o) {
    m.verbose("> program");
    m.i();
    // class header
    em(".bytecode 50.0");
    em(".source " + source);
    em(".class public utg/mcc/bytecode/A");
    em(".super java/lang/Object");
    em("");
    // global variables
    for (int i = 0; i < program.varDeclarations.size(); i++) {
      program.varDeclarations.elementAt(i).accept(this, null);
    }
    em("");
    // class constructor
    em(".method public <init>()V");
    emt(".limit locals 1");
    emt(".limit stack 1");
    emt("aload_0");
    emt("invokespecial java/lang/Object/<init>()V");
    emt("return");
    em(".end method");
    em("");
    // functions
    for (int i = 0; i < program.funDeclarations.size(); i++) {
      program.funDeclarations.elementAt(i).accept(this, null);
    }
    // executer header
    em(".method public static exec()V");
    actStack = 0;
    maxStack = 0;
    for (int i = 0; i < program.statements.size(); i++) {
      program.statements.elementAt(i).accept(this, null);
    }
    // executer footer
    emt("return");
    emt(".limit locals " + program.entry.locals);
    emt(".limit stack " + maxStack);
    em(".end method");
    try {
      out.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    m.d();
    m.verbose("< program");
    return null;
  }

  @Override
  public Object visitReturnStatement(
      ASTReturnStatement returnStatement, Object o) {
    m.verbose("> return");
    m.i();
    returnStatement.expression.accept(this, o);
    emt("ireturn");
    as(-1);
    m.d();
    m.verbose("< return ");
    return null;
  }

  @Override
  public Object visitSub(ASTSub sub, Object o) {
    TrueFalse tf = (o != null ? (TrueFalse) o : null);
    sub.op1.accept(this, tf);
    sub.op2.accept(this, tf);
    emt("isub");
    as(-1);
    return null;
  }

  @Override
  public Object visitVarDeclaration(ASTVarDeclaration varDeclaration,
      Object o) {
    if (varDeclaration.entry.global) {
      emt(".field public static " + varDeclaration.entry.name + " "
          + varDeclaration.entry.type.signature());
    }
    return null;
  }

  @Override
  public Object visitVariable(ASTVariable variable, Object o) {
    m.verbose("> variable [" + variable.name + "]");
    m.i();
    if (variable.entry.global) {
      emt("getstatic A/" + variable.entry.name + " "
          + variable.entry.type.signature());
    } else {
      emt("iload " + variable.entry.address);
    }
    as(1);
    m.d();
    m.verbose("< variable");
    return null;
  }

  @Override
  public Object visitWhileStatement(ASTWhileStatement whileStatement,
      Object o) {
    String lstart = label();
    String lend = label();
    String lbegin = label();
    TrueFalse tf = new TrueFalse(lbegin, lend);
    eml(lstart);
    whileStatement.expression.accept(this, tf);
    eml(lbegin);
    whileStatement.statement.accept(this, null);
    emt("goto " + lstart);
    eml(lend);
    return null;
  }
}
