import static org.junit.Assert.*;

import controller.Controller;
import controller.IController;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.StringReader;
import org.junit.Test;

public class ControllerTest {

  Readable in = new StringReader("new first\n"
      + "read examplesPPM/canyon.ppm\n" + "filter blur\n" + "export blurrcaaaaa");

  @Test
  public void test() {
    //Readable in = new FileInputStream("x.txt");
    IController testOne = new Controller(in);
    testOne.go();

    StringBuilder logModel = new StringBuilder();
    StringBuilder logImage = new StringBuilder();


  }


}