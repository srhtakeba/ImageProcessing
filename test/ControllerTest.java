import static org.junit.Assert.*;

import controller.Controller;
import controller.IController;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.StringReader;
import org.junit.Test;

public class ControllerTest {

//  Readable in = new StringReader("new first\n" + "new second\n"
//      + "current first\n" + "read images/originals/canyonLowest.jpg\n"
//      + "transform greyscale\n" + "current second\n"
//      + "read images/originals/canyonTransparent.png\n"
//      + "export blurrcaaaaa.png");

  Readable in = new StringReader("new first\n" + "new second\n" + "current first\n"
      + "read images/originals/canyonLowest.jpg\n" + "transform greyscale\n"
      + "current second\n" + "read images/originals/canyonLowest.jpg\n"
      + "save dirrrrrrr");

  @Test
  public void test() {
    //Readable in = new FileInputStream("x.txt");
    IController testOne = new Controller(in);
    testOne.go();

    StringBuilder logModel = new StringBuilder();
    StringBuilder logImage = new StringBuilder();


  }


}