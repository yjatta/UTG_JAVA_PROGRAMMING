package utg.mcc.parser;

import utg.mcc.parser.ast.ASTAdd;
import utg.mcc.parser.ast.ASTAssignStatement;
import utg.mcc.parser.ast.ASTBlockStatement;
import utg.mcc.parser.ast.ASTCall;
import utg.mcc.parser.ast.ASTDeclaration;
import utg.mcc.parser.ast.ASTDiv;
import utg.mcc.parser.ast.ASTEq;
import utg.mcc.parser.ast.ASTExpression;
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
import utg.mcc.parser.ast.ASTNode;
import utg.mcc.parser.ast.ASTParDeclaration;
import utg.mcc.parser.ast.ASTPrintStatement;
import utg.mcc.parser.ast.ASTProgram;
import utg.mcc.parser.ast.ASTReturnStatement;
import utg.mcc.parser.ast.ASTStatement;
import utg.mcc.parser.ast.ASTSub;
import utg.mcc.parser.ast.ASTVarDeclaration;
import utg.mcc.parser.ast.ASTVariable;
import utg.mcc.parser.ast.ASTWhileStatement;
import utg.mcc.scanner.Scanner;
import utg.mcc.scanner.ScannerConstants;
import utg.mcc.scanner.Token;
import utg.utils.Message;

public class SimpleParser implements ScannerConstants {

  private int lookahead;
  private Token token;
  private final Message m;
  private final ASTProgram p = new ASTProgram();

  public SimpleParser(Scanner scanner) {
    getNextToken();
    m = new Message("parser");
  }

  private void getNextToken() {
    token = Scanner.getNextToken();
    lookahead = token.kind;
  }

  private void match(int kind) {
    if (lookahead == kind) {
      getNextToken();
    } else {
      throw new RuntimeException("unexpected token "
          + tokenImage[lookahead] + " was expecting "
          + tokenImage[kind]);
    }
  }

  public ASTNode parse() {
    while (lookahead == INT) {
      p.addDeclaration(declaration());
    }
    while (lookahead != EOF) {
      p.addStatement(statement());
    }
    match(EOF);
    return p;
  }

  public ASTDeclaration declaration() {
    match(INT);
    String name = token.image;
    match(IDENTIFIER);
    if (lookahead == SEMICOLON) {
      m.verbose("> variable declaration");
      m.i();
      match(SEMICOLON);
      ASTDeclaration d = new ASTVarDeclaration(name, "int");
      m.d();
      m.verbose("< variable declaration");
      return d;
    } else if (lookahead == LPAREN) {
      m.verbose("> function declaration");
      m.i();
      ASTFunDeclaration funDec = new ASTFunDeclaration(name, "int");
      // p.addDeclaration(new VarDeclaration(name));
      match(LPAREN);
      while (lookahead == INT) {
        match(INT);
        funDec
            .addDeclaration(new ASTParDeclaration(token.image, "int"));
        match(IDENTIFIER);
        if (lookahead == COMMA) {
          match(COMMA);
        }
      }
      match(RPAREN);
      funDec.setBlock((ASTBlockStatement) block());
      m.d();
      m.verbose("< function declaration");
      return funDec;
    }
    return null;
  }

  private ASTDeclaration variableDeclaration() {
    ASTDeclaration d;
    match(INT);
    d = new ASTVarDeclaration(token.image, "int");
    match(IDENTIFIER);
    match(SEMICOLON);
    return d;
  }

  private ASTStatement statement() {
    ASTStatement statement = null;
    m.verbose("> statement");
    m.i();
    switch (lookahead) {
    case IDENTIFIER:
      m.verbose("> assign");
      m.i();
      ASTVariable var = new ASTVariable(token.image);
      match(IDENTIFIER);
      match(ASSIGN);
      statement = new ASTAssignStatement(var, relExpr());
      match(SEMICOLON);
      m.d();
      m.verbose("< assign");
      break;
    case WHILE:
      m.verbose("> while");
      m.i();
      ASTExpression e;
      ASTStatement s;
      match(WHILE);
      match(LPAREN);
      e = relExpr();
      match(RPAREN);
      s = statement();
      statement = new ASTWhileStatement(e, s);
      m.d();
      m.verbose("< while");
      break;
    case IF:
      m.verbose("> if");
      m.i();
      ASTStatement th;
      ASTStatement el = null;
      match(IF);
      match(LPAREN);
      e = relExpr();
      match(RPAREN);
      th = statement();
      if (lookahead == ELSE) {
        match(ELSE);
        el = statement();
        statement = new ASTIfStatement(e, th, el);
      } else {
        statement = new ASTIfStatement(e, th);
      }
      m.d();
      m.verbose("< if");
      break;
    case PRINT:
      m.verbose("> print");
      m.i();
      match(PRINT);
      match(LPAREN);
      statement = new ASTPrintStatement(relExpr());
      match(RPAREN);
      match(SEMICOLON);
      m.d();
      m.verbose("< print");
      break;
    case RETURN:
      m.verbose("> return");
      m.i();
      match(RETURN);
      statement = new ASTReturnStatement(relExpr());
      match(SEMICOLON);
      m.d();
      m.verbose("< return");
      break;
    case LBRACE:
      statement = block();
      break;
    default:
      throw new RuntimeException("unexpected token "
          + tokenImage[lookahead] + " was expecting "
          + "{IDENTIFIER, WHILE, IF, PRINT, RETURN, LBRACE}");
    }
    m.d();
    m.verbose("< statement");
    return statement;
  }

  private ASTStatement block() {
    m.verbose("> block");
    m.i();
    ASTBlockStatement b = new ASTBlockStatement();
    match(LBRACE);
    while (lookahead == INT) {
      b.addDeclaration(variableDeclaration());
    }
    while (lookahead != RBRACE) {
      b.addStatement(statement());
    }
    match(RBRACE);
    m.d();
    m.verbose("< block");
    return b;
  }

  private ASTExpression relExpr() {
    m.verbose("> relation expression");
    m.i();
    ASTExpression e = addExpr();
    switch (lookahead) {
    case GT:
      match(lookahead);
      e = new ASTGt(e, addExpr());
      break;
    case LT:
      match(lookahead);
      e = new ASTLt(e, addExpr());
      break;
    case EQ:
      match(lookahead);
      e = new ASTEq(e, addExpr());
      break;
    case LE:
      match(lookahead);
      e = new ASTLe(e, addExpr());
      break;
    case GE:
      match(lookahead);
      e = new ASTGe(e, addExpr());
      break;
    case NE:
      match(lookahead);
      e = new ASTNe(e, addExpr());
      break;
    }
    m.d();
    m.verbose("< relation expression");
    return e;
  }

  private ASTExpression addExpr() {
    m.verbose("> additive expression");
    m.i();
    ASTExpression e = mulExpr();
    while (lookahead == ADD || lookahead == SUB) {
      if (lookahead == ADD) {
        match(lookahead);
        e = new ASTAdd(e, mulExpr());
      } else {
        match(lookahead);
        e = new ASTSub(e, mulExpr());
      }
    }
    m.d();
    m.verbose("< additive expression");
    return e;
  }

  private ASTExpression mulExpr() {
    m.verbose("> multiplicative expression");
    m.i();
    ASTExpression e = negExpr();
    while (lookahead == MUL || lookahead == DIV) {
      if (lookahead == MUL) {
        match(lookahead);
        e = new ASTMul(e, negExpr());
      } else {
        match(lookahead);
        e = new ASTDiv(e, negExpr());
      }
    }
    m.d();
    m.verbose("< multiplicative expression");
    return e;
  }

  private ASTExpression negExpr() {
    ASTExpression e;
    m.verbose("> negation expression");
    m.i();
    if (lookahead == SUB) {
      match(SUB);
      e = new ASTNeg(priExpr());
    } else {
      e = priExpr();
    }
    m.d();
    m.verbose("< negation expression");
    return e;
  }

  private ASTExpression priExpr() {
    m.verbose("> primitive expression");
    m.i();
    ASTExpression e = null;
    switch (lookahead) {
    case LPAREN:
      match(LPAREN);
      e = relExpr();
      match(RPAREN);
      break;
    case INTEGER:
      e = new ASTIntegerConstant(Integer.parseInt(token.image));
      match(INTEGER);
      break;
    case IDENTIFIER:
      String name = token.image;
      match(IDENTIFIER);
      if (lookahead == LPAREN) {
        m.verbose("> function call");
        m.i();
        ASTCall funCall = new ASTCall(name);
        match(LPAREN);
        while (lookahead != RPAREN) {
          funCall.addParameter(relExpr());
          if (lookahead == COMMA) {
            match(COMMA);
          }
        }
        match(RPAREN);
        e = funCall;
        m.d();
        m.verbose("< function call");
      } else {
        e = new ASTVariable(name);
      }
      break;
    default:
      throw new RuntimeException("unexpected token "
          + tokenImage[lookahead] + " was expecting "
          + "{LPAREN, INTEGER, IDENTIFIER}");
    }
    m.d();
    m.verbose("< primitive expression");
    return e;
  }
}
