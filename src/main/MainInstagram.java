package main;

import controller.Controller;
import controller.IController;
import java.io.InputStreamReader;

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
    Readable in = new InputStreamReader(System.in);
    Appendable out = System.out;
    IController controller = new Controller(in, out);
    controller.go();
  }

}
