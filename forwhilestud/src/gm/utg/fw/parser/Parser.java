package gm.utg.fw.parser;


import gm.utg.fw.Type;
import gm.utg.fw.scanner.Scanner;
import gm.utg.fw.scanner.ScannerConstants;
import gm.utg.fw.scanner.Token;
import gm.utg.fw.tree.AssignmentStatement;
import gm.utg.fw.tree.ForStatement;
import gm.utg.fw.tree.FunctionCall;
import gm.utg.fw.tree.Identifier;
import gm.utg.fw.tree.Minus;
import gm.utg.fw.tree.Node;
import gm.utg.fw.tree.Num;
import gm.utg.fw.tree.Plus;
import gm.utg.fw.tree.StatementList;
import gm.utg.fw.tree.WhileStatement;
import gm.utg.fw.util.Message;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedHashMap;

public class Parser implements ScannerConstants, Type {

  static Parser parser = null;
  static Scanner scanner = null;
  static Token lookahead;
  private final Message m = new Message("parser");
  public static boolean system;
  private int type = W;
  private long time = 0;
  public LinkedHashMap<String,Identifier> instances = new LinkedHashMap<String,Identifier>();
  public Identifier first;

  public Parser(String input, int type) {
    this.type = type;
    first = new Identifier("x1".intern(),this);
    first.setValue(0);
    instances.put("x1".intern(),first);
    try {
      File file = new File(input + ".fw");
      time = file.lastModified();
      scanner = new Scanner(new FileReader(file));
      lookahead = scanner.getNextToken();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  public Identifier get(String name) {
    Identifier identifier = instances.get(name.intern());
    if (identifier == null) {
      identifier = new Identifier(name.intern(), this);
      identifier.setValue(0);
      instances.put(name.intern(), identifier);
    } 
    return identifier;
  }

  
  public long getTime() {
    return time;
  }
  
  void match(int kind) {
    if (lookahead.kind == kind) {
      m.debug("matching " + tokenImage[kind]);
      lookahead = scanner.getNextToken();
    } else {
      throw new RuntimeException(lc(lookahead) + " Unexpected token "
          + tokenImage[lookahead.kind] + " was expecting " + tokenImage[kind]);
    }
  }

  public void setInput (String input) {
    java.util.Scanner in = new java.util.Scanner(input);
    int i = 1;
    while (in.hasNextInt()) {
      Identifier id = get("x" + i);
      id.setValue(in.nextInt());
      i++;
    }
  }
  
  public Node parse() {
    StatementList statementList = new StatementList(this);
    statementList.add(statement());
    while (lookahead.kind == END) {
      match(END);
      statementList.add(statement());
    }
    match(EOF);
    return statementList;
  }

  Node statement() {
    m.debug("> statement ");
    m.i();
    Node statement = null;
    switch (lookahead.kind) {
    case ID:
      statement = assignmentOrFunctionCallStatement();
      break;
    case WHILE:
      if (type == F) {
        throw new RuntimeException(lc(lookahead) + "token <WHILE> forbidden in " +
        		"For programs");
      }
      statement = whileStatement();
      break;
    case FOR:
      if (type == W) {
        throw new RuntimeException(lc(lookahead) + "token <FOR> forbidden in " +
            "While programs");
      }
      statement = forStatement();
      break;
    default:
      throw new RuntimeException(lc(lookahead) + " illegal token " + tokenImage[lookahead.kind] + 
          " was expecting <ID>, <FOR> or <WHILE>");
    }
    m.d();
    m.debug("< statement");
    return statement;
  }

  Node expression() {
    m.debug("> expression");
    m.i();
    Node res = term();
    if (lookahead.kind == PLUS) {
      match(PLUS);
      Node arg2 = term();
      res = new Plus(res,arg2);
    } else if (lookahead.kind == MINUS){
      match(MINUS);
      Node arg2 = term();
      res = new Minus(res,arg2);
    } 
    m.d();
    m.debug("< expression");
    return res;
  }

  Node term() {
    m.debug("> term");
    m.i();
    Node node = null;
    if (lookahead.kind == ID) {
      node = get(lookahead.image);
      match(ID);
    } else {
      node = new Num(lookahead.image);
    match(NUM); }
    m.d();
    m.debug("< term");
    return node;
  }

  Node whileStatement() {
    m.debug("> while");
    m.i();
    match(WHILE);
    Identifier identifier = get(lookahead.image);
    match(ID);
    match(DO);
    StatementList statementList = new StatementList(this);
    Node statement = statement();
    statementList.add(statement);
    while (lookahead.kind == END) {
      match(END);
      statement = statement();
      statementList.add(statement);
    }
    match(OD);
    WhileStatement whileStatement = new WhileStatement(identifier,statementList, this);
    m.d();
    m.debug("< while");
    return whileStatement;
  }
  
  Node forStatement() {
    m.debug("> for");
    m.i();
    match(FOR);
    Identifier identifier = get(lookahead.image);
    match(ID);
    match(DO);
    StatementList statementList = new StatementList(this);
    Node statement = statement();
    statementList.add(statement);
    while (lookahead.kind == END) {
      match(END);
      statement = statement();
      statementList.add(statement);
    }
    match(OD);
    ForStatement forStatement = new ForStatement(identifier,statementList, this);
    m.d();
    m.debug("< for");
    return forStatement;
  }
  
  Node assignmentOrFunctionCallStatement() {
    m.debug("> assign");
    m.i();
    Identifier id = get(lookahead.image);
    match(ID);
    match(SET);
    if (lookahead.kind == FUNC) {
      String function = lookahead.image;
      FunctionCall functionCall = new FunctionCall(function, id, type, time, this); 
      match(FUNC);
      match(LPAR);
      while (lookahead.kind == ID || lookahead.kind == NUM) { 
        if (lookahead.kind == ID) {
          functionCall.arguments.add(get(lookahead.image));
          match(ID);
        } else {
          functionCall.arguments.add(new Num(lookahead.image));
          match(NUM);
        }
        if (lookahead.kind != RPAR) {
          match(COMMA);
        }
      }
      match(RPAR);
      m.d();
      m.debug("< assign");
      return functionCall;
    } else {
      Node res = expression();
      m.d();
      m.debug("< assign");
      return new AssignmentStatement(id,res, this);
    }
  }

  String lc(Token token) {
    return "at line " + token.beginLine + ", column "
        + token.beginColumn + ":";
  }
}
