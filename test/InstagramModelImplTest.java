import static org.junit.Assert.assertEquals;

import model.InstagramModel;
import model.InstagramModelImpl;
import model.image.ImageImpl;
import model.image.InstaImage;
import model.pixel.Pixel;
import model.pixel.PixelImpl;
import org.junit.Before;
import org.junit.Test;

/**
 * Class to hold tests for {@code InstagramModelImpl}.
 */
public class InstagramModelImplTest {


  private InstagramModel modelCheckerBoard;

  private InstagramModel modelRainbow;

  private InstagramModel modelRainbowSingleFile;

  @Before
  public void setup() {
    InstaImage checkerBoard = new ImageImpl().makeCheckerBoard(3);
    modelCheckerBoard = new InstagramModelImpl(checkerBoard);

    InstaImage rainbow = new ImageImpl().makeRainbow(3, 1);
    modelRainbow = new InstagramModelImpl(rainbow);

    InstaImage rainbowSingleFile = new ImageImpl().makeRainbow(1, 1);
    modelRainbowSingleFile = new InstagramModelImpl(rainbowSingleFile);
  }

  @Test
  public void testGreyscaleCheckerboard() {
    modelCheckerBoard.transform("GREYSCALE");
    InstaImage expected = new ImageImpl().makeCheckerBoard(3);
    assertEquals(expected.toString(),
        modelCheckerBoard.exportAsInstaImage().toString());
  }

  @Test
  public void testSepiaCheckerboard() {
    modelCheckerBoard.transform("Sepia");
    Pixel[][] expectedPixelGrid = new Pixel[3][3];
    expectedPixelGrid[0][0] = new PixelImpl(345, 307, 239);
    expectedPixelGrid[0][1] = new PixelImpl(0, 0, 0);
    expectedPixelGrid[0][2] = new PixelImpl(345, 307, 239);

    expectedPixelGrid[1][0] = new PixelImpl(0, 0, 0);
    expectedPixelGrid[1][1] = new PixelImpl(345, 307, 239);
    expectedPixelGrid[1][2] = new PixelImpl(0, 0, 0);

    expectedPixelGrid[2][0] = new PixelImpl(345, 307, 239);
    expectedPixelGrid[2][1] = new PixelImpl(0, 0, 0);
    expectedPixelGrid[2][2] = new PixelImpl(345, 307, 239);
    InstaImage expected = new ImageImpl(expectedPixelGrid, 3, 3);

    assertEquals(expected.toString(), modelCheckerBoard.exportAsInstaImage().toString());
  }

  @Test
  public void testBlurCheckerboard() {
    modelCheckerBoard.filter("blur");
    Pixel[][] expectedPixelGrid = new Pixel[3][3];
    expectedPixelGrid[0][0] = new PixelImpl(80, 80, 80);
    expectedPixelGrid[0][1] = new PixelImpl(96, 96, 96);
    expectedPixelGrid[0][2] = new PixelImpl(80, 80, 80);

    expectedPixelGrid[1][0] = new PixelImpl(96, 96, 96);
    expectedPixelGrid[1][1] = new PixelImpl(128, 128, 128);
    expectedPixelGrid[1][2] = new PixelImpl(96, 96, 96);

    expectedPixelGrid[2][0] = new PixelImpl(80, 80, 80);
    expectedPixelGrid[2][1] = new PixelImpl(96, 96, 96);
    expectedPixelGrid[2][2] = new PixelImpl(80, 80, 80);
    InstaImage expected = new ImageImpl(expectedPixelGrid, 3, 3);

    assertEquals(expected.toString(), modelCheckerBoard.exportAsInstaImage().toString());
  }

  @Test
  public void testSharpenCheckerboard() {
    modelCheckerBoard.filter("sharpen");
    Pixel[][] expectedPixelGrid = new Pixel[3][3];
    expectedPixelGrid[0][0] = new PixelImpl(223, 223, 223);
    expectedPixelGrid[0][1] = new PixelImpl(128, 128, 128);
    expectedPixelGrid[0][2] = new PixelImpl(223, 223, 223);

    expectedPixelGrid[1][0] = new PixelImpl(128, 128, 128);
    expectedPixelGrid[1][1] = new PixelImpl(255, 255, 255);
    expectedPixelGrid[1][2] = new PixelImpl(128, 128, 128);

    expectedPixelGrid[2][0] = new PixelImpl(223, 223, 223);
    expectedPixelGrid[2][1] = new PixelImpl(128, 128, 128);
    expectedPixelGrid[2][2] = new PixelImpl(223, 223, 223);
    InstaImage expected = new ImageImpl(expectedPixelGrid, 3, 3);

    assertEquals(expected.toString(), modelCheckerBoard.exportAsInstaImage().toString());
  }

  @Test
  public void testGreyscaleRainbow() {
    modelRainbow.transform("greyscale");
    Pixel[][] expectedPixelGrid = new Pixel[7][3];
    // red row
    expectedPixelGrid[0][0] = new PixelImpl(54, 54, 54);
    expectedPixelGrid[0][1] = new PixelImpl(54, 54, 54);
    expectedPixelGrid[0][2] = new PixelImpl(54, 54, 54);
    // orange row
    expectedPixelGrid[1][0] = new PixelImpl(172, 172, 172);
    expectedPixelGrid[1][1] = new PixelImpl(172, 172, 172);
    expectedPixelGrid[1][2] = new PixelImpl(172, 172, 172);
    // yellow row
    expectedPixelGrid[2][0] = new PixelImpl(237, 237, 237);
    expectedPixelGrid[2][1] = new PixelImpl(237, 237, 237);
    expectedPixelGrid[2][2] = new PixelImpl(237, 237, 237);
    // green row
    expectedPixelGrid[3][0] = new PixelImpl(182, 182, 182);
    expectedPixelGrid[3][1] = new PixelImpl(182, 182, 182);
    expectedPixelGrid[3][2] = new PixelImpl(182, 182, 182);
    // blue row
    expectedPixelGrid[4][0] = new PixelImpl(18, 18, 18);
    expectedPixelGrid[4][1] = new PixelImpl(18, 18, 18);
    expectedPixelGrid[4][2] = new PixelImpl(18, 18, 18);
    // indigo row
    expectedPixelGrid[5][0] = new PixelImpl(25, 25, 25);
    expectedPixelGrid[5][1] = new PixelImpl(25, 25, 25);
    expectedPixelGrid[5][2] = new PixelImpl(25, 25, 25);
    // violet row
    expectedPixelGrid[6][0] = new PixelImpl(161, 161, 161);
    expectedPixelGrid[6][1] = new PixelImpl(161, 161, 161);
    expectedPixelGrid[6][2] = new PixelImpl(161, 161, 161);
    InstaImage expected = new ImageImpl(expectedPixelGrid, 3, 7);

    assertEquals(expected.toString(), modelRainbow.exportAsInstaImage().toString());
  }

  @Test
  public void testGreyscaleTwiceRainbow() {
    modelRainbowSingleFile.transform("greyscale");
    modelRainbowSingleFile.transform("greyscale");
    Pixel[][] expectedPixelGrid = new Pixel[7][1];
    // red row
    expectedPixelGrid[0][0] = new PixelImpl(54, 54, 54);
    // orange row
    expectedPixelGrid[1][0] = new PixelImpl(172, 172, 172);
    // yellow row
    expectedPixelGrid[2][0] = new PixelImpl(237, 237, 237);
    // green row
    expectedPixelGrid[3][0] = new PixelImpl(182, 182, 182);
    // blue row
    expectedPixelGrid[4][0] = new PixelImpl(18, 18, 18);
    // indigo row
    expectedPixelGrid[5][0] = new PixelImpl(25, 25, 25);
    // violet row
    expectedPixelGrid[6][0] = new PixelImpl(161, 161, 161);
    InstaImage expected = new ImageImpl(expectedPixelGrid, 1, 7);

    assertEquals(expected.toString(), modelRainbowSingleFile.exportAsInstaImage().toString());
  }

  @Test
  public void testSepiaRainbow() {
    modelRainbow.transform("Sepia");
    Pixel[][] expectedPixelGrid = new Pixel[7][3];
    // red row
    expectedPixelGrid[0][0] = new PixelImpl(100, 89, 69);
    expectedPixelGrid[0][1] = new PixelImpl(100, 89, 69);
    expectedPixelGrid[0][2] = new PixelImpl(100, 89, 69);
    // orange row
    expectedPixelGrid[1][0] = new PixelImpl(227, 202, 157);
    expectedPixelGrid[1][1] = new PixelImpl(227, 202, 157);
    expectedPixelGrid[1][2] = new PixelImpl(227, 202, 157);
    // yellow row
    expectedPixelGrid[2][0] = new PixelImpl(296, 264, 206);
    expectedPixelGrid[2][1] = new PixelImpl(296, 264, 206);
    expectedPixelGrid[2][2] = new PixelImpl(296, 264, 206);
    // green row
    expectedPixelGrid[3][0] = new PixelImpl(196, 175, 136);
    expectedPixelGrid[3][1] = new PixelImpl(196, 175, 136);
    expectedPixelGrid[3][2] = new PixelImpl(196, 175, 136);
    // blue row
    expectedPixelGrid[4][0] = new PixelImpl(48, 43, 33);
    expectedPixelGrid[4][1] = new PixelImpl(48, 43, 33);
    expectedPixelGrid[4][2] = new PixelImpl(48, 43, 33);
    // indigo row
    expectedPixelGrid[5][0] = new PixelImpl(54, 48, 37);
    expectedPixelGrid[5][1] = new PixelImpl(54, 48, 37);
    expectedPixelGrid[5][2] = new PixelImpl(54, 48, 37);
    // violet row
    expectedPixelGrid[6][0] = new PixelImpl(238, 212, 165);
    expectedPixelGrid[6][1] = new PixelImpl(238, 212, 165);
    expectedPixelGrid[6][2] = new PixelImpl(238, 212, 165);
    InstaImage expected = new ImageImpl(expectedPixelGrid, 3, 7);

    assertEquals(expected.toString(), modelRainbow.exportAsInstaImage().toString());
  }

  @Test
  public void testSepiaTwiceRainbow() {
    modelRainbowSingleFile.transform("sepia");
    modelRainbowSingleFile.transform("sepia");
    Pixel[][] expectedPixelGrid = new Pixel[7][1];
    // red row
    expectedPixelGrid[0][0] = new PixelImpl(121, 108, 84);
    // orange row
    expectedPixelGrid[1][0] = new PixelImpl(255, 244, 190);
    // yellow row
    expectedPixelGrid[2][0] = new PixelImpl(255, 255, 233);
    // green row
    expectedPixelGrid[3][0] = new PixelImpl(237, 211, 165);
    // blue row
    expectedPixelGrid[4][0] = new PixelImpl(58, 52, 40);
    // indigo row
    expectedPixelGrid[5][0] = new PixelImpl(65, 58, 45);
    // violet row
    expectedPixelGrid[6][0] = new PixelImpl(255, 255, 200);
    InstaImage expected = new ImageImpl(expectedPixelGrid, 1, 7);

    assertEquals(expected.toString(), modelRainbowSingleFile.exportAsInstaImage().toString());
  }

  @Test
  public void testSepiaSharpenRainbow() {
    modelRainbowSingleFile.transform("sepia");
    modelRainbowSingleFile.filter("sharpen");
    Pixel[][] expectedPixelGrid = new Pixel[7][1];
    // red row
    expectedPixelGrid[0][0] = new PixelImpl(125, 108, 83);
    // orange row
    expectedPixelGrid[1][0] = new PixelImpl(255, 255, 209);
    // yellow row
    expectedPixelGrid[2][0] = new PixelImpl(255, 255, 255);
    // green row
    expectedPixelGrid[3][0] = new PixelImpl(237, 218, 172);
    // blue row
    expectedPixelGrid[4][0] = new PixelImpl(49, 40, 30);
    // indigo row
    expectedPixelGrid[5][0] = new PixelImpl(101, 90, 70);
    // violet row
    expectedPixelGrid[6][0] = new PixelImpl(246, 219, 170);
    InstaImage expected = new ImageImpl(expectedPixelGrid, 1, 7);

    assertEquals(expected.toString(), modelRainbowSingleFile.exportAsInstaImage().toString());
  }

  @Test
  public void testBlurRainbow() {
    modelRainbowSingleFile.filter("blur");
    Pixel[][] expectedPixelGrid = new Pixel[7][1];
    // red row
    expectedPixelGrid[0][0] = new PixelImpl(96, 21, 0);
    // orange row
    expectedPixelGrid[1][0] = new PixelImpl(128, 73, 0);
    // yellow row
    expectedPixelGrid[2][0] = new PixelImpl(96, 116, 0);
    // green row
    expectedPixelGrid[3][0] = new PixelImpl(32, 96, 32);
    // blue row
    expectedPixelGrid[4][0] = new PixelImpl(9, 32, 80);
    // indigo row
    expectedPixelGrid[5][0] = new PixelImpl(49, 16, 94);
    // violet row
    expectedPixelGrid[6][0] = new PixelImpl(69, 33, 76);
    InstaImage expected = new ImageImpl(expectedPixelGrid, 1, 7);

    assertEquals(expected.toString(), modelRainbowSingleFile.exportAsInstaImage().toString());
  }

  @Test
  public void testBlurTwiceRainbow() {
    modelRainbowSingleFile.filter("blur");
    modelRainbowSingleFile.filter("blur");
    Pixel[][] expectedPixelGrid = new Pixel[7][1];
    // red row
    expectedPixelGrid[0][0] = new PixelImpl(40, 14, 0);
    // orange row
    expectedPixelGrid[1][0] = new PixelImpl(56, 35, 0);
    // yellow row
    expectedPixelGrid[2][0] = new PixelImpl(44, 50, 4);
    // green row
    expectedPixelGrid[3][0] = new PixelImpl(21, 43, 18);
    // blue row
    expectedPixelGrid[4][0] = new PixelImpl(12, 22, 36);
    // indigo row
    expectedPixelGrid[5][0] = new PixelImpl(22, 12, 43);
    // violet row
    expectedPixelGrid[6][0] = new PixelImpl(23, 10, 31);
    InstaImage expected = new ImageImpl(expectedPixelGrid, 1, 7);

    assertEquals(expected.toString(), modelRainbowSingleFile.exportAsInstaImage().toString());
  }

  @Test
  public void testBlurAndGreyscaleRainbow() {
    modelRainbowSingleFile.filter("blur");
    modelRainbowSingleFile.transform("greyscale");
    Pixel[][] expectedPixelGrid = new Pixel[7][1];
    // red row
    expectedPixelGrid[0][0] = new PixelImpl(35, 35, 35);
    // orange row
    expectedPixelGrid[1][0] = new PixelImpl(79, 79, 79);
    // yellow row
    expectedPixelGrid[2][0] = new PixelImpl(103, 103, 103);
    // green row
    expectedPixelGrid[3][0] = new PixelImpl(78, 78, 78);
    // blue row
    expectedPixelGrid[4][0] = new PixelImpl(31, 31, 31);
    // indigo row
    expectedPixelGrid[5][0] = new PixelImpl(29, 29, 29);
    // violet row
    expectedPixelGrid[6][0] = new PixelImpl(44, 44, 44);
    InstaImage expected = new ImageImpl(expectedPixelGrid, 1, 7);

    assertEquals(expected.toString(), modelRainbowSingleFile.exportAsInstaImage().toString());
  }

  @Test
  public void testSharpenRainbow() {
    modelRainbowSingleFile.filter("sharpen");
    Pixel[][] expectedPixelGrid = new Pixel[7][1];
    // red row
    expectedPixelGrid[0][0] = new PixelImpl(255, 9, 0);
    // orange row
    expectedPixelGrid[1][0] = new PixelImpl(255, 197, 0);
    // yellow row
    expectedPixelGrid[2][0] = new PixelImpl(255, 255, 0);
    // green row
    expectedPixelGrid[3][0] = new PixelImpl(23, 255, 48);
    // blue row
    expectedPixelGrid[4][0] = new PixelImpl(0, 16, 255);
    // indigo row
    expectedPixelGrid[5][0] = new PixelImpl(135, 1, 253);
    // violet row
    expectedPixelGrid[6][0] = new PixelImpl(255, 130, 239);
    InstaImage expected = new ImageImpl(expectedPixelGrid, 1, 7);

    assertEquals(expected.toString(), modelRainbowSingleFile.exportAsInstaImage().toString());
  }

  @Test
  public void testSharpenTwiceRainbow() {
    modelRainbowSingleFile.filter("sharpen");
    modelRainbowSingleFile.filter("sharpen");
    Pixel[][] expectedPixelGrid = new Pixel[7][1];
    // red row
    expectedPixelGrid[0][0] = new PixelImpl(255, 26, 0);
    // orange row
    expectedPixelGrid[1][0] = new PixelImpl(255, 231, 0);
    // yellow row
    expectedPixelGrid[2][0] = new PixelImpl(255, 255, 0);
    // green row
    expectedPixelGrid[3][0] = new PixelImpl(38, 255, 80);
    // blue row
    expectedPixelGrid[4][0] = new PixelImpl(0, 32, 255);
    // indigo row
    expectedPixelGrid[5][0] = new PixelImpl(196, 6, 255);
    // violet row
    expectedPixelGrid[6][0] = new PixelImpl(255, 128, 255);
    InstaImage expected = new ImageImpl(expectedPixelGrid, 1, 7);

    assertEquals(expected.toString(), modelRainbowSingleFile.exportAsInstaImage().toString());
  }

  @Test
  public void testSharpenRainbowRetrieveOriginal() {
    modelRainbowSingleFile.save();
    modelRainbowSingleFile.filter("sharpen");
    modelRainbowSingleFile.retrieve();
    Pixel[][] expectedPixelGrid = new Pixel[7][1];
    // red row
    expectedPixelGrid[0][0] = new PixelImpl(255, 0, 0);
    // orange row
    expectedPixelGrid[1][0] = new PixelImpl(255, 165, 0);
    // yellow row
    expectedPixelGrid[2][0] = new PixelImpl(255, 255, 0);
    // green row
    expectedPixelGrid[3][0] = new PixelImpl(0, 255, 0);
    // blue row
    expectedPixelGrid[4][0] = new PixelImpl(0, 0, 255);
    // indigo row
    expectedPixelGrid[5][0] = new PixelImpl(75, 0, 130);
    // violet row
    expectedPixelGrid[6][0] = new PixelImpl(238, 130, 238);
    InstaImage expected = new ImageImpl(expectedPixelGrid, 1, 7);

    assertEquals(expected.toString(), modelRainbowSingleFile.exportAsInstaImage().toString());
  }

  @Test(expected = IllegalStateException.class)
  public void testRetrieveNoImageException() {
    InstagramModel model = new InstagramModelImpl();
    model.retrieve();
  }

  @Test(expected = IllegalStateException.class)
  public void testSaveNoImageException() {
    InstagramModel model = new InstagramModelImpl();
    model.save();
  }

  @Test(expected = IllegalStateException.class)
  public void testBlurNoImageException() {
    InstagramModel model = new InstagramModelImpl();
    model.filter("bLuR");
  }

  @Test(expected = IllegalStateException.class)
  public void testSharpenNoImageException() {
    InstagramModel model = new InstagramModelImpl();
    model.filter("SHARPEN");
  }

  @Test(expected = IllegalStateException.class)
  public void testGreyscaleNoImageException() {
    InstagramModel model = new InstagramModelImpl();
    model.transform("greyscale");
  }

  @Test(expected = IllegalStateException.class)
  public void testSepiaNoImageException() {
    InstagramModel model = new InstagramModelImpl();
    model.transform("sepia");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testFilterInvalidArgument() {
    modelCheckerBoard.transform("mattify");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTransformInvalidArgument() {
    modelRainbow.transform("vibrant");
  }

  // tests for importing and exporting images
  @Test
  public void testReadExportPPM() {
    InstagramModel model = new InstagramModelImpl();
    modelCheckerBoard.exportAsPPM("checkerboardReadTest");
    model.readPPM("checkerboardReadTest.ppm");
    assertEquals(model.exportAsInstaImage().toString(),
        modelCheckerBoard.exportAsInstaImage().toString());
  }

  @Test
  public void testReadExportInstaImage() {
    InstaImage image = new ImageImpl().makeRainbow(4, 5);
    InstagramModel model = new InstagramModelImpl();
    model.readInstaImage(image);
    model.save();
    model.transform("greyscale");
    model.retrieve();
    assertEquals(image.toString(), model.exportAsInstaImage().toString());
  }
}