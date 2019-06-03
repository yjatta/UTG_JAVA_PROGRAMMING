package gm.utg.fw.tree;

import gm.utg.fw.parser.Parser;

import java.util.LinkedHashMap;
import java.util.Vector;

public class Identifier extends Node {
  
  private String name;
  private Parser parser;
  
  public Identifier(String name, Parser parser) {
    this.name = name;
    this.parser = parser;
  }
 
  @Override
  public int eval() {
    return value;
  }

  public String toString() {
    return name;
  }

  public void setValue(int value) {
    this.value = value;
  }
  
  public int getValue() {
    return value;
  }
  
  public int getResult() {
    return parser.first.eval();
  }
}
