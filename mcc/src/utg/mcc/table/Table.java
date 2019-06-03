package utg.mcc.table;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;

public class Table<T> {

  /** the table */
  private final Dictionary<Symbol, Binder<T>> table = new Hashtable<Symbol, Binder<T>>();

  /** the last symbol defined until the last beginScope */
  private Symbol top = null;

  /** remembers all last top symbols */
  private Binder<T> marks = null;

  public Table() {
  }

  /**
   * Gets the object associated with the specified symbol in the Table.
   */
  public T resolve(String name) {
    Binder<T> e = table.get(Symbol.symbol(name));
    if (e == null)
      return null;
    else
      return e.value;
  }

  /**
   * Gets the object associated with the specified symbol in the Table.
   */
  public T resolve(Symbol key) {
    Binder<T> e = table.get(key);
    if (e == null)
      return null;
    else
      return e.value;
  }

  /**
   * Puts the specified value into the Table, bound to the specified Symbol.
   */
  public void define(Symbol key, T value) {
    table.put(key, new Binder<T>(value, top, table.get(key)));
    top = key;
  }

  /**
   * Puts the specified value into the Table, bound to the specified Symbol.
   */
  public void define(String name, T value) {
    Symbol symbol = Symbol.symbol(name);
    table.put(symbol, new Binder<T>(value, top, table.get(symbol)));
    top = symbol;
  }

  /**
   * Remembers the current state of the Table.
   */
  public void beginScope() {
    marks = new Binder<T>(null, top, marks);
    top = null;
  }

  /**
   * Restores the table to what it was at the most recent beginScope that has
   * not already been ended.
   */
  public void endScope() {
    while (top != null) {
      Binder<T> e = table.get(top);
      if (e.tail != null)
        table.put(top, e.tail);
      else
        table.remove(top);
      top = e.prevtop;
    }
    top = marks.prevtop;
    marks = marks.tail;
  }

  /**
   * Returns an enumeration of the Table's symbols.
   */
  public Enumeration<Symbol> keys() {
    return table.keys();
  }
}