package main;

import controller.Controller;
import controller.GUIController;
import controller.IController;
import java.io.InputStreamReader;
import java.io.StringReader;

/**
 * Class to represent the OOD Instagram application. It will run the application with {@code
 * InstagramLayerModel} and {@code IController}. This will recieve input from the console, and send
 * it to the console as well.
 */
public class MainInstagram {

  /**
   * This will run the OOD instagram application.
   *
   * @param args command line input.
   */
  public static void main(String[] args) {
    if (args[0].equals("-interactive")) {
      IController guiController = new GUIController();
    } else if (args[0].equals("-text")) {
      Readable in = new InputStreamReader(System.in);
      Appendable out = System.out;
      IController controller = new Controller(in, out);
      controller.dispatchController();
    } else if (args[0].equals("-script")) {
      StringBuilder sb = new StringBuilder();
      sb.append("N\n2\n");
      String filePath = args[1].substring(1);
      sb.append(args[1]);
      Readable in = new StringReader(sb.toString());
      Appendable out = System.out;
      IController controller = new Controller(in, out);
      controller.dispatchController();
    } else {
      throw new IllegalArgumentException("Please type -interactive, -text, or -script.");
    }
  }

}
