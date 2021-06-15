import static org.junit.Assert.*;

import controller.Controller;
import controller.IController;
import java.io.IOException;
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
    in = new StringReader("");
    out = new StringBuffer();
  }

  @Test
  public void test() {
    in = new StringReader("N\n"+ "1\n"+"new first\n" + "new second\n" + "current first\n"
        + "read images/originals/canyonLowest.jpg\n" + "transform greyscale\n"
        + "current second\n" + "read images/originals/canyonLowest.jpg\n"
        + "save dirrrrrrr");
    IController testOne = new Controller(in,out);
    testOne.go();

    assertEquals("", out.toString());
  }

  @Test
  public void testMessageNonExistentPastProjectFile() throws IOException {
    in = new StringReader("Y\n"+ "fakepackagename\n" +"1\n"+"q\n");
    StringBuilder expected = new StringBuilder();
    expected.append("Welcome to OOD Instagram.\n");
    expected.append("Would you like to open an existing project?\n");
    expected.append("Type Y or N.\n");
    expected.append("Please type the directory path of the project.\n");
    expected.append("The given file was not found. A new project will be created.\n");
    expected.append("Would you like to\n 1) Use interactive. \n 2) Use a script.\n");
    expected.append("Please type choice 1 or 2\n");
    expected.append("Begin interaction. Refer to USEME.md for detailed instructions. "
        + "Type Q/q to quit at anytime.\n");
    expected.append("Program has been quit.\n");
    IController testOne = new Controller(in,out);
    testOne.go();

    assertEquals(expected.toString(), out.toString());
  }


}