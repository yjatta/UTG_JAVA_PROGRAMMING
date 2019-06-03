package utg.mcc.bytecode;

import java.lang.reflect.Method;

import utg.utils.Message;

public class Exec {

  public static Message m = new Message("bytecode execution");

  public static void exec() {
    System.out.println("> assembling");
    String jasmin_args[] = new String[3];
    jasmin_args[0] = new String("-d");
    jasmin_args[1] = new String("../../bin");
    jasmin_args[2] = new String("A.j");
    jasmin.Main.main(jasmin_args);
    m.message("running");
    try {
      Class<?> a = Exec.class.getClassLoader().loadClass(
          "utg.mcc.bytecode.A");
      Method m = a.getMethod("exec", (Class<?>[]) null);
      m.invoke(null, (Object[]) null);
    } catch (Exception e) {
      e.printStackTrace();
    }
    m.message("done");
  }
}
