import static org.junit.Assert.assertEquals;

import controller.Controller;
import controller.IController;
import java.io.StringReader;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests to ensure the functionality of {@code Controller}.
 */
public class ControllerTest {

  Readable in;
  Appendable out;

  @Before
  public void setup() {
    in = new StringReader("");
    out = new StringBuffer();
  }

  @Test
  public void testMessageNonExistentPastProjectFile() {
    in = new StringReader("Y\n" + "fakepackagename\n" + "1\n" + "q\n");
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
    IController testOne = new Controller(in, out);
    testOne.dispatchController();

    assertEquals(expected.toString(), out.toString());
  }

  @Test
  public void testMessageNonExistentLayerNameCurrent() {
    Readable input = new StringReader(
        "N\n 1\n new first\n new second\n new third\n current fourth\n q\n");
    StringBuilder expected = new StringBuilder();
    expected.append("Welcome to OOD Instagram.\n");
    expected.append("Would you like to open an existing project?\n");
    expected.append("Type Y or N.\n");
    expected.append("Would you like to\n 1) Use interactive. \n 2) Use a script.\n");
    expected.append("Please type choice 1 or 2\n");
    expected.append("Begin interaction. Refer to USEME.md for detailed instructions. "
        + "Type Q/q to quit at anytime.\n");
    expected.append("Bad input: The layer with the provided name does not exist.\n");
    expected.append("Program has been quit.\n");
    IController testOne = new Controller(input, out);
    testOne.dispatchController();

    assertEquals(expected.toString(), out.toString());
  }

  @Test
  public void testMessageNonExistentLayerNameRemove() {
    Readable input = new StringReader(
        "N\n 1\n new first\n new second\n new third\n remove fourth\n q\n");
    StringBuilder expected = new StringBuilder();
    expected.append("Welcome to OOD Instagram.\n");
    expected.append("Would you like to open an existing project?\n");
    expected.append("Type Y or N.\n");
    expected.append("Would you like to\n 1) Use interactive. \n 2) Use a script.\n");
    expected.append("Please type choice 1 or 2\n");
    expected.append("Begin interaction. Refer to USEME.md for detailed instructions. "
        + "Type Q/q to quit at anytime.\n");
    expected.append("Bad input: The layer with the provided name does not exist.\n");
    expected.append("Program has been quit.\n");
    IController testOne = new Controller(input, out);
    testOne.dispatchController();

    assertEquals(expected.toString(), out.toString());
  }

  @Test
  public void testMessageNonExistentLayerNameInvisible() {
    Readable input = new StringReader(
        "N\n 1\n new first\n new second\n new third\n invisible fourth\n q\n");
    StringBuilder expected = new StringBuilder();
    expected.append("Welcome to OOD Instagram.\n");
    expected.append("Would you like to open an existing project?\n");
    expected.append("Type Y or N.\n");
    expected.append("Would you like to\n 1) Use interactive. \n 2) Use a script.\n");
    expected.append("Please type choice 1 or 2\n");
    expected.append("Begin interaction. Refer to USEME.md for detailed instructions. "
        + "Type Q/q to quit at anytime.\n");
    expected.append("Bad input: The layer with the provided name does not exist.\n");
    expected.append("Program has been quit.\n");
    IController testOne = new Controller(input, out);
    testOne.dispatchController();

    assertEquals(expected.toString(), out.toString());
  }

  @Test
  public void testMessageNonExistentLayerNameVisible() {
    Readable input = new StringReader(
        "N\n 1\n new first\n new second\n new third\n visible fourth\n q\n");
    StringBuilder expected = new StringBuilder();
    expected.append("Welcome to OOD Instagram.\n");
    expected.append("Would you like to open an existing project?\n");
    expected.append("Type Y or N.\n");
    expected.append("Would you like to\n 1) Use interactive. \n 2) Use a script.\n");
    expected.append("Please type choice 1 or 2\n");
    expected.append("Begin interaction. Refer to USEME.md for detailed instructions. "
        + "Type Q/q to quit at anytime.\n");
    expected.append("Bad input: The layer with the provided name does not exist.\n");
    expected.append("Program has been quit.\n");
    IController testOne = new Controller(input, out);
    testOne.dispatchController();

    assertEquals(expected.toString(), out.toString());
  }

  @Test
  public void testMessageInvalidProportions() {
    Readable input = new StringReader("N\n 2\n res/scriptInvalidProportions.txt\n");
    StringBuilder expected = new StringBuilder();
    expected.append("Welcome to OOD Instagram.\n");
    expected.append("Would you like to open an existing project?\n");
    expected.append("Type Y or N.\n");
    expected.append("Would you like to\n 1) Use interactive. \n 2) Use a script.\n");
    expected.append("Please type choice 1 or 2\n");
    expected.append("Please input the filepath to the script.\n");
    expected.append("Bad input: The given image is of invalid proportions.\n");
    expected.append("Program has been quit.\n");
    IController testOne = new Controller(input, out);
    testOne.dispatchController();

    assertEquals(expected.toString(), out.toString());
  }

  @Test
  public void testMessageEmptyLayerFilter() {
    Readable input = new StringReader(
        "N\n 1\n new first\n new second\n new third\n current second\n "
            + "filter blur\n q\n");
    StringBuilder expected = new StringBuilder();
    expected.append("Welcome to OOD Instagram.\n");
    expected.append("Would you like to open an existing project?\n");
    expected.append("Type Y or N.\n");
    expected.append("Would you like to\n 1) Use interactive. \n 2) Use a script.\n");
    expected.append("Please type choice 1 or 2\n");
    expected.append("Begin interaction. Refer to USEME.md for detailed instructions. "
        + "Type Q/q to quit at anytime.\n");
    expected.append("System error: There is no image to be filtered.\n");
    expected.append("Program has been quit.\n");
    IController testOne = new Controller(input, out);
    testOne.dispatchController();

    assertEquals(expected.toString(), out.toString());
  }

  @Test
  public void testMessageEmptyLayerTransform() {
    Readable input = new StringReader(
        "N\n 1\n new first\n new second\n new third\n current second\n "
            + "transform sepia\n q\n");
    StringBuilder expected = new StringBuilder();
    expected.append("Welcome to OOD Instagram.\n");
    expected.append("Would you like to open an existing project?\n");
    expected.append("Type Y or N.\n");
    expected.append("Would you like to\n 1) Use interactive. \n 2) Use a script.\n");
    expected.append("Please type choice 1 or 2\n");
    expected.append("Begin interaction. Refer to USEME.md for detailed instructions. "
        + "Type Q/q to quit at anytime.\n");
    expected.append("System error: There is no image to be transformed.\n");
    expected.append("Program has been quit.\n");
    IController testOne = new Controller(input, out);
    testOne.dispatchController();

    assertEquals(expected.toString(), out.toString());
  }

  @Test
  public void testMessageBadTransform() {
    Readable input = new StringReader(
        "N\n 1\n new first\n new second\n new third\n current first\n "
            + "read res/images/originals/canyonLowest.jpg\n transform blue\n q\n");
    StringBuilder expected = new StringBuilder();
    expected.append("Welcome to OOD Instagram.\n");
    expected.append("Would you like to open an existing project?\n");
    expected.append("Type Y or N.\n");
    expected.append("Would you like to\n 1) Use interactive. \n 2) Use a script.\n");
    expected.append("Please type choice 1 or 2\n");
    expected.append("Begin interaction. Refer to USEME.md for detailed instructions. "
        + "Type Q/q to quit at anytime.\n");
    expected.append("Bad input: Given transform operation is invalid.\n");
    expected.append("Program has been quit.\n");
    IController testOne = new Controller(input, out);
    testOne.dispatchController();

    assertEquals(expected.toString(), out.toString());
  }

  @Test
  public void testMessageBadFilter() {
    Readable input = new StringReader(
        "N\n 1\n new first\n new second\n new third\n current first\n "
            + "read res/images/originals/canyonLowest.jpg\n filter blue\n q\n");
    StringBuilder expected = new StringBuilder();
    expected.append("Welcome to OOD Instagram.\n");
    expected.append("Would you like to open an existing project?\n");
    expected.append("Type Y or N.\n");
    expected.append("Would you like to\n 1) Use interactive. \n 2) Use a script.\n");
    expected.append("Please type choice 1 or 2\n");
    expected.append("Begin interaction. Refer to USEME.md for detailed instructions. "
        + "Type Q/q to quit at anytime.\n");
    expected.append("Bad input: Given filter operation is invalid.\n");
    expected.append("Program has been quit.\n");
    IController testOne = new Controller(input, out);
    testOne.dispatchController();

    assertEquals(expected.toString(), out.toString());
  }

  @Test
  public void testMessageEmptyLayerExportAsPPM() {
    Readable input = new StringReader(
        "N\n 1\n new first\n new second\n new third\n export foo.ppm\n q\n");
    StringBuilder expected = new StringBuilder();
    expected.append("Welcome to OOD Instagram.\n");
    expected.append("Would you like to open an existing project?\n");
    expected.append("Type Y or N.\n");
    expected.append("Would you like to\n 1) Use interactive. \n 2) Use a script.\n");
    expected.append("Please type choice 1 or 2\n");
    expected.append("Begin interaction. Refer to USEME.md for detailed instructions. "
        + "Type Q/q to quit at anytime.\n");
    expected.append("System error: No images to be exported.\n");
    expected.append("Program has been quit.\n");
    IController testOne = new Controller(input, out);
    testOne.dispatchController();

    assertEquals(expected.toString(), out.toString());
  }

  @Test
  public void testMessageBadExportName() {
    Readable input = new StringReader(
        "N\n 1\n new first\n new second\n new third\n current first\n "
            + "read res/images/originals/canyonLowest.jpg\n export foofoofoo\n q\n");
    StringBuilder expected = new StringBuilder();
    expected.append("Welcome to OOD Instagram.\n");
    expected.append("Would you like to open an existing project?\n");
    expected.append("Type Y or N.\n");
    expected.append("Would you like to\n 1) Use interactive. \n 2) Use a script.\n");
    expected.append("Please type choice 1 or 2\n");
    expected.append("Begin interaction. Refer to USEME.md for detailed instructions. "
        + "Type Q/q to quit at anytime.\n");
    expected.append("Bad input: Invalid file. Must include '.--' extension\n");
    expected.append("Program has been quit.\n");
    IController testOne = new Controller(input, out);
    testOne.dispatchController();

    assertEquals(expected.toString(), out.toString());
  }


}