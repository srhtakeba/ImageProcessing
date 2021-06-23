import static org.junit.Assert.assertArrayEquals;

import model.filter.BlurOperation;
import model.filter.FilterOperation;
import model.filter.SharpenOperation;
import model.image.ImageImpl;
import model.image.InstaImage;
import model.pixel.Pixel;
import model.pixel.PixelImpl;
import org.junit.Before;
import org.junit.Test;


/**
 * Test class for FilterOperation: testing the performance on an image representation of a 2DArray
 * of {@code Pixel}.
 */
public class FilterOperationTest {


  private FilterOperation blur;
  private FilterOperation sharpen;

  private Pixel[][] grid;
  private Pixel[][] grid2;

  @Before
  public void setup() {
    blur = new BlurOperation();
    sharpen = new SharpenOperation();

    // make a 3x3 image of the same colors
    grid = new Pixel[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        grid[i][j] = new PixelImpl(100.0, 20.0, 50.0);
      }
    }

    // make a 3x1 image of different colors
    grid2 = new Pixel[3][1];
    grid2[0][0] = new PixelImpl(25, 25, 25);
    grid2[1][0] = new PixelImpl(50, 50, 50);
    grid2[2][0] = new PixelImpl(75, 75, 75);
  }

  @Test
  public void testBlurAllSame() {
    Pixel[][] expected = new Pixel[3][3];
    expected[0][0] = new PixelImpl(56, 11, 28);
    expected[0][1] = new PixelImpl(75, 15, 38);
    expected[0][2] = new PixelImpl(56, 11, 28);

    expected[1][0] = new PixelImpl(75, 15, 38);
    expected[1][1] = new PixelImpl(100, 20, 50);
    expected[1][2] = new PixelImpl(75, 15, 38);

    expected[2][0] = new PixelImpl(56, 11, 28);
    expected[2][1] = new PixelImpl(75, 15, 38);
    expected[2][2] = new PixelImpl(56, 11, 28);
    InstaImage image = new ImageImpl(grid, 3, 3);
    image = blur.apply(image);
    grid = image.getPixelGrid();
    assertArrayEquals(expected, grid);
  }

  @Test
  public void testBlurDifferent() {
    Pixel[][] expected = new Pixel[3][1];
    expected[0][0] = new PixelImpl(13, 13, 13);
    expected[1][0] = new PixelImpl(25, 25, 25);
    expected[2][0] = new PixelImpl(25, 25, 25);
    InstaImage image2 = new ImageImpl(grid2, 1, 3);
    image2 = blur.apply(image2);
    grid2 = image2.getPixelGrid();
    assertArrayEquals(expected, grid2);
  }

  @Test
  public void testSharpenAllSame() {
    Pixel[][] expected = new Pixel[3][3];
    expected[0][0] = new PixelImpl(113, 23, 56);
    expected[0][1] = new PixelImpl(188, 38, 94);
    expected[0][2] = new PixelImpl(113, 23, 56);

    expected[1][0] = new PixelImpl(188, 38, 94);
    expected[1][1] = new PixelImpl(255, 60, 150);
    expected[1][2] = new PixelImpl(188, 38, 94);

    expected[2][0] = new PixelImpl(113, 23, 56);
    expected[2][1] = new PixelImpl(188, 38, 94);
    expected[2][2] = new PixelImpl(113, 23, 56);

    InstaImage image = new ImageImpl(grid, 3, 3);
    image = sharpen.apply(image);
    grid = image.getPixelGrid();
    assertArrayEquals(expected, grid);
  }

  @Test
  public void testSharpenDifferent() {
    Pixel[][] expected = new Pixel[3][1];
    expected[0][0] = new PixelImpl(28, 28, 28);
    expected[1][0] = new PixelImpl(75, 75, 75);
    expected[2][0] = new PixelImpl(84, 84, 84);
    InstaImage image2 = new ImageImpl(grid2, 1, 3);
    image2 = sharpen.apply(image2);
    grid2 = image2.getPixelGrid();
    assertArrayEquals(expected, grid2);
  }


}