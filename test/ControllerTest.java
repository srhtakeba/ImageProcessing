import static org.junit.Assert.*;

import controller.Controller;
import controller.IController;
import java.io.StringReader;
import org.junit.Before;
import org.junit.Test;

public class ControllerTest {

//  Readable in = new StringReader("new first\n" + "new second\n"
//      + "current first\n" + "read images/originals/canyonLowest.jpg\n"
//      + "transform greyscale\n" + "current second\n"
//      + "read images/originals/canyonTransparent.png\n"
//      + "export blurrcaaaaa.png");

  Readable in;
  Appendable out;

  @Before
  public void setup() {
    in = new StringReader("N\n"+ "1\n"+"new first\n" + "new second\n" + "current first\n"
        + "read images/originals/canyonLowest.jpg\n" + "transform greyscale\n"
        + "current second\n" + "read images/originals/canyonLowest.jpg\n"
        + "save dirrrrrrr");
    out = new StringBuffer();
  }

  @Test
  public void test() {
    IController testOne = new Controller(in,out);
    testOne.go();

    assertEquals("", out.toString());
  }


}