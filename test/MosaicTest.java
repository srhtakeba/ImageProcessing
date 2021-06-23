import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotEquals;

import model.image.ImageImpl;
import model.image.InstaImage;
import model.mosaic.Mosaic;
import model.mosaic.MosaicImpl;
import model.pixel.Pixel;
import model.pixel.PixelImpl;
import org.junit.Before;
import org.junit.Test;

/**
 * Class to test {@code MosaicImpl} functionality.
 */
public class MosaicTest {

  private Mosaic mosaic;

  private InstaImage checkerBoard;

  private Pixel[][] grid;

  private Pixel[][] grid2;

  private Pixel[][] grid3;

  @Before
  public void setup() {
    mosaic = new MosaicImpl();

    // make a 3x3 image of the same colors
    grid = new Pixel[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        grid[i][j] = new PixelImpl(100.0, 20.0, 50.0);
      }
    }

    // make a 3x1 image of different colors
    grid2 = new Pixel[3][3];
    grid2[0][0] = new PixelImpl(25, 25, 25);
    grid2[0][1] = new PixelImpl(50, 50, 50);
    grid2[0][2] = new PixelImpl(75, 75, 75);

    grid2[1][0] = new PixelImpl(15, 15, 20);
    grid2[1][1] = new PixelImpl(5, 50, 5);
    grid2[1][2] = new PixelImpl(35, 35, 35);

    grid2[2][0] = new PixelImpl(25, 25, 25);
    grid2[2][1] = new PixelImpl(50, 50, 50);
    grid2[2][2] = new PixelImpl(75, 75, 75);

    // same as grid2
    grid3 = new Pixel[3][3];
    grid3[0][0] = new PixelImpl(25, 25, 25);
    grid3[0][1] = new PixelImpl(50, 50, 50);
    grid3[0][2] = new PixelImpl(75, 75, 75);

    grid3[1][0] = new PixelImpl(15, 15, 20);
    grid3[1][1] = new PixelImpl(5, 50, 5);
    grid3[1][2] = new PixelImpl(35, 35, 35);

    grid3[2][0] = new PixelImpl(25, 25, 25);
    grid3[2][1] = new PixelImpl(50, 50, 50);
    grid3[2][2] = new PixelImpl(75, 75, 75);
  }

  @Test
  public void testMosaicReturnsOriginal() {
    Pixel[][] expected = new Pixel[3][3];
    expected[0][0] = new PixelImpl(100, 20, 50);
    expected[0][1] = new PixelImpl(100, 20, 50);
    expected[0][2] = new PixelImpl(100, 20, 50);

    expected[1][0] = new PixelImpl(100, 20, 50);
    expected[1][1] = new PixelImpl(100, 20, 50);
    expected[1][2] = new PixelImpl(100, 20, 50);

    expected[2][0] = new PixelImpl(100, 20, 50);
    expected[2][1] = new PixelImpl(100, 20, 50);
    expected[2][2] = new PixelImpl(100, 20, 50);

    InstaImage image = new ImageImpl(grid, 3, 3);
    image = mosaic.apply(image, 2);

    assertArrayEquals(expected, image.getPixelGrid());
  }

  @Test
  public void testMosaicReturnsOriginalBigSeed() {
    Pixel[][] expected = new Pixel[3][3];
    expected[0][0] = new PixelImpl(25, 25, 25);
    expected[0][1] = new PixelImpl(50, 50, 50);
    expected[0][2] = new PixelImpl(75, 75, 75);

    expected[1][0] = new PixelImpl(15, 15, 20);
    expected[1][1] = new PixelImpl(5, 50, 5);
    expected[1][2] = new PixelImpl(35, 35, 35);

    expected[2][0] = new PixelImpl(25, 25, 25);
    expected[2][1] = new PixelImpl(50, 50, 50);
    expected[2][2] = new PixelImpl(75, 75, 75);

    InstaImage image = new ImageImpl(grid2, 3, 3);
    image = mosaic.apply(image, 100);

    assertArrayEquals(expected, image.getPixelGrid());
  }

  @Test
  public void testMosaic2() {
    InstaImage image = new ImageImpl(grid2, 3, 3);
    image = mosaic.apply(image, 2);

    InstaImage image1 = new ImageImpl(grid3, 3, 3);
    image1 = mosaic.apply(image1, 2);

    assertNotEquals(image1.getPixelGrid(), image.getPixelGrid());
  }

  @Test
  public void testMosaic3() {
    InstaImage image = new ImageImpl(grid2, 3, 3);
    image = mosaic.apply(image, 2);
    InstaImage image2 = mosaic.apply(image, 2);

    assertNotEquals(image2.getPixelGrid(), image.getPixelGrid());
  }

}
