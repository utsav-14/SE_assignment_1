package com.cleancoder.args;

public class ArgsMain {
  public static void main(String[] args) {
    try {
      Args arg = new Args("l,p#,d*, k##", args);
      boolean logging = arg.getBoolean('l');
      int port = arg.getInt('p');
      String directory = arg.getString('d');
      Double cost = arg.getDouble('k');
      executeApplication(logging, port, directory, cost);
    } catch (ArgsException e) {
      System.out.printf("Argument error: %s\n", e.errorMessage());
    }
  }

  private static void executeApplication(boolean logging, int port, String directory, Double cost) {
    System.out.printf("logging is %s, port:%d, directory:%s, Cost: %f\n",logging, port, directory, cost);
  }
}