package gm.utg.fw.main;

import gm.utg.fw.Type;
import gm.utg.fw.parser.Parser;
import gm.utg.fw.tree.Node;
import gm.utg.fw.util.Message;

/**
 * 
 * @author blj2
 *
 *         This is a small interpreter for for and while languages
 *         according to chapter 5 of the lecture notes
 *         "Theory of Computing II". The languages are using following
 *         grammars:
 * 
 *         whileProgram ::= statement (";" statement)*; statement ::=
 *         assignmentStatement | whileStatement; assignmentStatement
 *         ::= identifier = expression; expression ::= term (("+"|"-")
 *         term)?; term ::= <IDENTIFIER> | <NUMBER>; whileStatement
 *         ::= "while" <IDENTIFIER> "do" statement (";" statement)*
 *         "od";
 * 
 *         forProgram ::= statement (";" statement)*; statement ::=
 *         assignmentStatement | whileStatement; assignmentStatement
 *         ::= identifier = expression; expression ::= term (("+"|"-")
 *         term)?; term ::= <IDENTIFIER> | <NUMBER>; forStatement ::=
 *         "for" <IDENTIFIER> "do" statement (";" statement)* "od";
 * 
 */

public class Main implements Type {

  public static boolean running = true;
  public static Message m = new Message("rec");

  /**
   * @param args
   */
  public static void main(String[] args) {
    Message.setDebug(false);
    Message.setVerbose(false);
    m.message("parsing input file");
    Parser parser = new Parser("nthPrime", A); // F For, W While, // A For
                                          // and While
    parser.setInput("20"); //2 3 5 7 11
    Node node = parser.parse();
    m.message("parsing successfull");
    m.message("input file\n");
    System.out.println(node);
    m.message("interpreting");
    m.message("result: " + node.eval());
  }

}
