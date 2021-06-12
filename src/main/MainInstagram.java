package main;

import controller.Controller;
import controller.IController;
import java.io.InputStreamReader;

public class MainInstagram {
  /**
   *
   * @param args command line input.
   */
  public static void main(String[] args) {
    Readable in = new InputStreamReader(System.in);
    //Readable in = new FileInputStream("script.txt");
    IController controller = new Controller(in);
    controller.go();
  }

}
