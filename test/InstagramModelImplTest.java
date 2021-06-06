import static org.junit.Assert.assertEquals;

import model.InstaImage.ImageImpl;
import model.InstaImage.InstaImage;
import model.InstagramModel;
import model.InstagramModelImpl;
import model.Pixel.Pixel;
import model.Pixel.PixelImpl;
import org.junit.Before;
import org.junit.Test;

public class InstagramModelImplTest {

  private InstagramModel modelCheckerBoard;
  private InstaImage checkerBoard;

  private InstagramModel modelRainbow;
  private InstaImage rainbow;

  @Before
  public void setup() {
    checkerBoard = new ImageImpl().makeCheckerBoard(2);
    modelCheckerBoard = new InstagramModelImpl(checkerBoard);

    rainbow = new ImageImpl().makeRainbow(3,7);
    modelRainbow = new InstagramModelImpl(rainbow);
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
    expectedPixelGrid[0][0] = new PixelImpl(345,307,239);
    expectedPixelGrid[0][1] = new PixelImpl(0,0,0);
    expectedPixelGrid[0][2] = new PixelImpl(345,307,239);

    expectedPixelGrid[1][0] = new PixelImpl(0,0,0);
    expectedPixelGrid[1][1] = new PixelImpl(345,307,239);
    expectedPixelGrid[1][2] = new PixelImpl(0,0,0);

    expectedPixelGrid[2][0] = new PixelImpl(345,307,239);
    expectedPixelGrid[2][1] = new PixelImpl(0,0,0);
    expectedPixelGrid[2][2] = new PixelImpl(345,307,239);
    InstaImage expected = new ImageImpl(expectedPixelGrid, 3, 3);

    assertEquals(expected.toString(), modelCheckerBoard.exportAsInstaImage().toString());
  }

  @Test
  public void testBlurCheckerboard() {
    modelCheckerBoard.filter("blur");
    Pixel[][] expectedPixelGrid = new Pixel[3][3];
    expectedPixelGrid[0][0] = new PixelImpl(80,80,80);
    expectedPixelGrid[0][1] = new PixelImpl(96,96,96);
    expectedPixelGrid[0][2] = new PixelImpl(80,80,80);

    expectedPixelGrid[1][0] = new PixelImpl(96,96,96);
    expectedPixelGrid[1][1] = new PixelImpl(128,128,128);
    expectedPixelGrid[1][2] = new PixelImpl(96,96,96);

    expectedPixelGrid[2][0] = new PixelImpl(80,80,80);
    expectedPixelGrid[2][1] = new PixelImpl(96,96,96);
    expectedPixelGrid[2][2] = new PixelImpl(80,80,80);
    InstaImage expected = new ImageImpl(expectedPixelGrid, 3, 3);

    assertEquals(expected.toString(), modelCheckerBoard.exportAsInstaImage().toString());
  }

  @Test
  public void testSharpenCheckerboard() {
    modelCheckerBoard.filter("sharpen");
    Pixel[][] expectedPixelGrid = new Pixel[3][3];
    expectedPixelGrid[0][0] = new PixelImpl(223,223,223);
    expectedPixelGrid[0][1] = new PixelImpl(128,128,128);
    expectedPixelGrid[0][2] = new PixelImpl(80,80,80);

    expectedPixelGrid[1][0] = new PixelImpl(128,128,128);
    expectedPixelGrid[1][1] = new PixelImpl(255,255,255);
    expectedPixelGrid[1][2] = new PixelImpl(128,128,128);

    expectedPixelGrid[2][0] = new PixelImpl(223,223,223);
    expectedPixelGrid[2][1] = new PixelImpl(128,128,128);
    expectedPixelGrid[2][2] = new PixelImpl(223,223,223);
    InstaImage expected = new ImageImpl(expectedPixelGrid, 3, 3);

    assertEquals(expected.toString(), modelCheckerBoard.exportAsInstaImage().toString());
  }

  @Test
  public void testGreyscaleRainbow() {
    modelRainbow.transform("greyscale");
    Pixel[][] expectedPixelGrid = new Pixel[7][3];
    // red row
    expectedPixelGrid[0][0] = new PixelImpl(54,54,54);
    expectedPixelGrid[0][1] = new PixelImpl(54,54,54);
    expectedPixelGrid[0][2] = new PixelImpl(54,54,54);
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
  public void testSepiaRainbow() {
    modelRainbow.transform("Sepia");
    Pixel[][] expectedPixelGrid = new Pixel[7][3];
    // red row
    expectedPixelGrid[0][0] = new PixelImpl(100,89,69);
    expectedPixelGrid[0][1] = new PixelImpl(100,89,69);
    expectedPixelGrid[0][2] = new PixelImpl(100,89,69);
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



}