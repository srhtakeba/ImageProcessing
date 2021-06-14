package main;

import controller.Controller;
import controller.IController;
import java.io.InputStreamReader;

/**
 * Class to represents
 */
public class MainInstagram {
  /**
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
