package gm.utg.fw.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * This class implements some method for messaging.
 * 
 * @author jeb Nov 28, 2006
 */
public class Message {

  /** the name of the message sender. */
  private String name = null;
  /** debug flag. */
  private static boolean debug = false;
  /** verbose flag. */
  private static boolean verbose = false;
  /** indentation */
  private static String indent = "";

  /**
   * Constructor.
   * 
   * @param name
   *          the name
   */
  public Message(String name) {
    super();
    this.name = "[" + name + "]";
  }

  /**
   * indent
   */
  public void i() {
    indent += "  ";
  }

  /**
   * dedent
   */
  public void d() {
    indent = indent.substring(2);
  }

  /**
   * Message.
   * 
   * @param message
   *          normal message
   */
  public void message(String message) {
    System.out.printf("%1$-20s " + indent + "%2$s\n", name, message);
  }

  /**
   * Message.
   * 
   * @param message
   *          normal message
   */
  public void prompt(String message) {
    System.out.printf("%1$-20s " + indent + "%2$s", name, message);
    System.out.flush();
  }

  /**
   * Error.
   * 
   * @param message
   *          error message. will exit after message
   */
  public void error(String message) {
    System.out.printf("%1$-20s " + indent + "%2$s\n", name, message);
    System.exit(1);
  }

  /**
   * Exception.
   * 
   * @param e
   *          the exception
   */
  public void exception(Exception e) {
    StringWriter w = new StringWriter();
    PrintWriter p = new PrintWriter(w);
    System.out.printf("%1$-20s " + indent + "exception\n", name);
    e.printStackTrace(p);
    indent = "";
  }

  /**
   * Warning.
   * 
   * @param message
   *          warning message
   */
  public void warning(String message) {
    System.out.printf("%1$-20s " + indent + "%2$s\n", name, message);
    indent = "";
  }

  /**
   * Verbose.
   * 
   * @param message
   *          supplementary message for verbose mode
   */
  public void verbose(String message) {
    if (verbose) {
      System.out
          .printf("%1$-20s " + indent + "%2$s\n", name, message);
    }
  }

  /**
   * Debug.
   * 
   * @param message
   *          supplementary message for debug mode
   */
  public void debug(String message) {
    if (debug) {
      System.out
          .printf("%1$-20s " + indent + "%2$s\n", name, message);
    }
  }

  /**
   * Source.
   * 
   * @param message
   *          source file message
   */
  public void source(String message) {
    System.out.printf("file %1$-20s " + indent + "%2$s\n", name,
        message);
  }

  /**
   * Setter for debug flag.
   * 
   * @param debug
   *          the debug
   */
  public static void setDebug(boolean debug) {
    Message.debug = debug;
  }

  /**
   * Setter for verbose flag.
   * 
   * @param verbose
   *          the verbose
   */
  public static void setVerbose(boolean verbose) {
    Message.verbose = verbose;
  }
}
