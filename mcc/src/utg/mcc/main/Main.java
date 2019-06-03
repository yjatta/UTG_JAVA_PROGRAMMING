package utg.mcc.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import utg.mcc.bytecode.Exec;
import utg.mcc.interpreter.Interpreter;
import utg.mcc.parser.ParseException;
import utg.mcc.parser.Parser;
import utg.mcc.parser.ast.ASTNode;
import utg.mcc.parser.util.ASTIdentityTransformation;
import utg.mcc.semantic.ComputeAddresses;
import utg.mcc.semantic.ComputeReachableCode;
import utg.mcc.semantic.TypeChecker;
import utg.mcc.translate.TFByteCodeGenerator;
import utg.utils.Message;

public class Main {

	/**
	 * @param args
	 * @throws FileNotFoundException
	 * @throws ParseException
	 */
	public static void main(String[] args) throws FileNotFoundException,
	    ParseException {
		Message m = new Message("main");
		Message.setVerbose(true);
		m.message("parsing");
		new Parser(new FileReader(new File(args[0])));
		ASTNode n = Parser.program();
		m.message("identity transformation");
		System.out.println("\n" + n.accept(new ASTIdentityTransformation(), ""));
		m.message("compute addresses");
		n.accept(new ComputeAddresses(), true);
		m.message("type checking");
		n.accept(new TypeChecker(), true);
		m.message("compute reachable");
		n.accept(new ComputeReachableCode(), true);
		m.message("interpreting");
		Interpreter interpreter = new Interpreter();
		n.accept(interpreter, null);
		m.message("memory");
		m.message("" + interpreter.memory);
		m.message("byte code generation");
		n.accept(new TFByteCodeGenerator(args[0]), null);
		m.message("executing byte code");
		Exec.exec();
		m.message("done");
		// INTNode i = (INTNode) n.accept(new IntermediateCodeGenerator(),
		// null);
		// m.message("computing parents");
		// i.accept(new SetParent(), null);
		// m.message("identity transformation");
		// System.out.println("\n"
		// + i.accept(new INTIdentityTransformation(), ""));
	}
}
