

import static org.junit.Assert.assertArrayEquals;

import model.FilterOperation.BlurOperation;
import model.FilterOperation.FilterOperation;
import model.FilterOperation.SharpenOperation;
import model.Pixel.Pixel;
import model.Pixel.PixelImpl;
import org.junit.Before;
import org.junit.Test;


/**
 * Test class for FilterOperation: testing the performance on an image representation of a 2DArray
 * of {@code Pixel}.
 */
public class FilterOperationTest {

  private FilterOperation blur;
  private FilterOperation sharpen;

  private Pixel[][] image;
  private Pixel[][] image2;

  @Before
  public void setup() {
    blur = new BlurOperation();
    sharpen = new SharpenOperation();

    // make a 3x3 image of the same colors
    image = new Pixel[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        image[i][j] = new PixelImpl(100.0, 20.0, 50.0);
      }
    }

    // make a 3x1 image of different colors
    image2 = new Pixel[3][1];
    image2[0][0] = new PixelImpl(25, 25, 25);
    image2[1][0] = new PixelImpl(50, 50, 50);
    image2[2][0] = new PixelImpl(75, 75, 75);
  }

  @Test
  public void testBlurAllSame() {
    Pixel[][] expected = new Pixel[3][3];
    expected[0][0] = new PixelImpl(100, 20, 50);
    expected[0][1] = new PixelImpl(75, 15, 38);
    expected[0][2] = new PixelImpl(75, 15, 38);

    expected[1][0] = new PixelImpl(75, 15, 38);
    expected[1][1] = new PixelImpl(56, 11, 28);
    expected[1][2] = new PixelImpl(56, 11, 28);

    expected[2][0] = new PixelImpl(75, 15, 38);
    expected[2][1] = new PixelImpl(56, 11, 28);
    expected[2][2] = new PixelImpl(56, 11, 28);
    image = blur.apply(image);
    assertArrayEquals(expected, image);
  }

  @Test
  public void testBlurDifferent() {
    Pixel[][] expected = new Pixel[3][1];
    expected[0][0] = new PixelImpl(28, 28, 28);
    expected[1][0] = new PixelImpl(28, 28, 28);
    expected[2][0] = new PixelImpl(42, 42, 42);
    image2 = blur.apply(image2);
    assertArrayEquals(expected, image2);
  }

  @Test
  public void testSharpenAllSame() {
    Pixel[][] expected = new Pixel[3][3];
    expected[0][0] = new PixelImpl(63, 13, 31);
    expected[0][1] = new PixelImpl(75, 15, 38);
    expected[0][2] = new PixelImpl(75, 15, 38);

    expected[1][0] = new PixelImpl(75, 15, 38);
    expected[1][1] = new PixelImpl(113, 23, 56);
    expected[1][2] = new PixelImpl(113, 23, 56);

    expected[2][0] = new PixelImpl(75, 15, 38);
    expected[2][1] = new PixelImpl(113, 23, 56);
    expected[2][2] = new PixelImpl(113, 23, 56);
    image = sharpen.apply(image);
    assertArrayEquals(expected, image);
  }

  @Test
  public void testSharpenDifferent() {
    Pixel[][] expected = new Pixel[3][1];
    expected[0][0] = new PixelImpl(16, 16, 16);
    expected[1][0] = new PixelImpl(34, 34, 34);
    expected[2][0] = new PixelImpl(53, 53, 53);
    image2 = sharpen.apply(image2);
    assertArrayEquals(expected, image2);
  }

}