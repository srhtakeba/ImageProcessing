import static org.junit.Assert.assertEquals;

import model.image.ImageImpl;
import model.image.InstaImage;
import model.pixel.Pixel;
import model.pixel.PixelImpl;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for ImageImpl: unit tests to ensure that image can correctly represent expected pixels
 * inside the image.
 */
public class ImageImplTest {

  private InstaImage image;

  @Before
  public void setup() {
    image = new ImageImpl();
  }

  @Test
  public void testMakeCheckerBoardOdd() {
    Pixel[][] expectedPixelGrid = new Pixel[3][3];
    expectedPixelGrid[0][0] = new PixelImpl(255, 255, 255);
    expectedPixelGrid[0][1] = new PixelImpl(0, 0, 0);
    expectedPixelGrid[0][2] = new PixelImpl(255, 255, 255);

    expectedPixelGrid[1][0] = new PixelImpl(0, 0, 0);
    expectedPixelGrid[1][1] = new PixelImpl(255, 255, 255);
    expectedPixelGrid[1][2] = new PixelImpl(0, 0, 0);

    expectedPixelGrid[2][0] = new PixelImpl(255, 255, 255);
    expectedPixelGrid[2][1] = new PixelImpl(0, 0, 0);
    expectedPixelGrid[2][2] = new PixelImpl(255, 255, 255);
    InstaImage expected = new ImageImpl(expectedPixelGrid, 3, 3);

    image.makeCheckerBoard(3);

    assertEquals(expected.toString(), image.toString());
  }

  @Test
  public void testMakeRainbowOdd() {
    Pixel[][] expectedPixelGrid = new Pixel[7][3];
    // red row
    expectedPixelGrid[0][0] = new PixelImpl(255, 0, 0);
    expectedPixelGrid[0][1] = new PixelImpl(255, 0, 0);
    expectedPixelGrid[0][2] = new PixelImpl(255, 0, 0);
    // orange row
    expectedPixelGrid[1][0] = new PixelImpl(255, 165, 0);
    expectedPixelGrid[1][1] = new PixelImpl(255, 165, 0);
    expectedPixelGrid[1][2] = new PixelImpl(255, 165, 0);
    // yellow row
    expectedPixelGrid[2][0] = new PixelImpl(255, 255, 0);
    expectedPixelGrid[2][1] = new PixelImpl(255, 255, 0);
    expectedPixelGrid[2][2] = new PixelImpl(255, 255, 0);
    // green row
    expectedPixelGrid[3][0] = new PixelImpl(0, 255, 0);
    expectedPixelGrid[3][1] = new PixelImpl(0, 255, 0);
    expectedPixelGrid[3][2] = new PixelImpl(0, 255, 0);
    // blue row
    expectedPixelGrid[4][0] = new PixelImpl(0, 0, 255);
    expectedPixelGrid[4][1] = new PixelImpl(0, 0, 255);
    expectedPixelGrid[4][2] = new PixelImpl(0, 0, 255);
    // indigo row
    expectedPixelGrid[5][0] = new PixelImpl(75, 0, 130);
    expectedPixelGrid[5][1] = new PixelImpl(75, 0, 130);
    expectedPixelGrid[5][2] = new PixelImpl(75, 0, 130);
    // violet row
    expectedPixelGrid[6][0] = new PixelImpl(238, 130, 238);
    expectedPixelGrid[6][1] = new PixelImpl(238, 130, 238);
    expectedPixelGrid[6][2] = new PixelImpl(238, 130, 238);

    InstaImage expected = new ImageImpl(expectedPixelGrid, 3, 7);

    image.makeRainbow(3, 1);

    assertEquals(expected.toString(), image.toString());
  }
}
