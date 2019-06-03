package interpreter;

import java.util.Hashtable;

public class Context {

	private final Hashtable <String , Boolean> t = new Hashtable <
			String , Boolean >() ;
	
			public boolean lookup ( String name ) {
			if ( t . get (name ) == null ) {
			throw new RuntimeException ( "variable " + name + "notdeclared");
			}
			return t.get (name).booleanValue( ) ;
			}
			
			public void assign (String name , Boolean i) {
				t.put(name , i) ;
}
}
