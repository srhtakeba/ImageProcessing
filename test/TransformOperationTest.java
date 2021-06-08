import static org.junit.Assert.assertEquals;

import model.InstaImage.ImageImpl;
import model.InstagramModel;
import model.InstagramModelImpl;
import model.Pixel.Pixel;
import model.Pixel.PixelImpl;
import model.TransformOperation.GreyscaleOperation;
import model.TransformOperation.SepiaToneOperation;
import model.TransformOperation.TransformOperation;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for TransformOperation: testing the performance on an image representation
 * of a 2DArray of {@code Pixel}.
 */
public class TransformOperationTest {
  private TransformOperation greyscale;
  private TransformOperation sepia;
  private Pixel[][] image;
  private Pixel[][] image2;

  @Before
  public void setup() {
    greyscale = new GreyscaleOperation();
    sepia = new SepiaToneOperation();

    // make a 3x3 image of the same colors
    image = new Pixel[3][3];
    for(int i=0; i<3; i++) {
      for (int j=0; j<3;j++) {
        image[i][j] = new PixelImpl(100.0,20.0,50.0);
      }
    }

    // make a 3x1 image of different colors
    image2 = new Pixel[3][1];
    image2[0][0] = new PixelImpl(25, 25, 25);
    image2[1][0] = new PixelImpl(50, 50, 50);
    image2[2][0] = new PixelImpl(75, 75, 75);
  }

  @Test
  public void testGreyScaleAllSame() {
    Pixel[][] expected = new Pixel[3][3];
    for(int i=0; i<3; i++) {
      for (int j=0; j<3;j++) {
        expected[i][j] = new PixelImpl(39.174,39.174,39.174);
      }
    }
    image = greyscale.apply(image);
    assertEquals(expected, image);
  }

  @Test
  public void testGreyScaleDifferent() {
    Pixel[][] expected = new Pixel[3][1];
    expected[0][0] = new PixelImpl(25, 25, 25);
    expected[1][0] = new PixelImpl(50, 50, 50);
    expected[2][0] = new PixelImpl(75, 75, 75);
    image2 = greyscale.apply(image2);
    assertEquals(expected, image2);
  }

  @Test
  public void testSepiaAllSame() {
    Pixel[][] expected = new Pixel[3][3];
    for(int i=0; i<3; i++) {
      for (int j=0; j<3;j++) {
        expected[i][j] = new PixelImpl(64.13,57.02,44.43);
      }
    }
    image = sepia.apply(image);
    assertEquals(expected, image);
  }

  @Test
  public void testSepiaDifferent() {
    Pixel[][] expected = new Pixel[3][1];
    expected[0][0] = new PixelImpl(33.775, 30.075, 23.425);
    expected[1][0] = new PixelImpl(67.55, 60.15, 46.85);
    expected[2][0] = new PixelImpl(101.325, 90.225, 70.275);
    image2 = sepia.apply(image2);
    assertEquals(expected, image2);
  }
}
