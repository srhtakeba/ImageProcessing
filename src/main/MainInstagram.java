package main;

import controller.Controller;
import controller.IController;
import java.io.InputStreamReader;
//
public class MainInstagram {
  /**
   * Runs a game of SimpleFreecellModel using a SimpleFreecellController in the console.
   *
   * @param args command line input.
   */
  public static void main(String[] args) {
    Readable in = new InputStreamReader(System.in);
    IController controller = new Controller(in);
    controller.go();
  }

}
