package gm.utg.fw.tree;

import gm.utg.fw.parser.Parser;

import java.util.LinkedHashMap;

public abstract class Node {
  
  public static String indent = "";
  public Parser parser;
  public int value;
  
  public abstract int eval();
}
