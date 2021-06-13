package main;

import controller.Controller;
import controller.IController;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainInstagram {
  /**
   *
   * @param args command line input.
   */
  public static void main(String[] args) {
    //Readable in = new InputStreamReader(System.in);
    Readable in;
    try {
      File script = new File("script.txt");
      InputStream inStream = new FileInputStream(script);
      in = new InputStreamReader(inStream);
    } catch (IOException e) {
      throw new IllegalStateException("Invalid script to read");
    }
    IController controller = new Controller(in);
    controller.go();

  }

}
