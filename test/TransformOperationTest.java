import static org.junit.Assert.assertEquals;

import model.Pixel.Pixel;
import model.Pixel.PixelImpl;
import model.TransformOperation.GreyscaleOperation;
import model.TransformOperation.SepiaToneOperation;
import model.TransformOperation.TransformOperation;
import org.junit.Before;
import org.junit.Test;

/**
 * A class to hold tests for a color transformation to be performed on an image representation
 * of a 2DArray of {@code Pixel}.
 */
public class TransformOperationTest {
  private TransformOperation greyscale;
  private TransformOperation sepia;
  private Pixel[][] image;

  @Before
  public void setup() {
    greyscale = new GreyscaleOperation();
    sepia = new SepiaToneOperation();

    // make a 3x3 image of just red
    image = new Pixel[3][3];
    for(int i=0; i<3; i++) {
      for (int j=0; j<3;j++) {
        image[i][j] = new PixelImpl(100.0,20.0,50.0);
      }
    }
  }

  @Test
  public void testGreyScale() {
    Pixel[][] expected = new Pixel[3][3];
    for(int i=0; i<3; i++) {
      for (int j=0; j<3;j++) {
        expected[i][j] = new PixelImpl(39.174,39.174,39.174);
      }
    }
    image = greyscale.apply(image);
    assertEquals(image, expected);
  }

  @Test
  public void testSepia() {
    Pixel[][] expected = new Pixel[3][3];
    for(int i=0; i<3; i++) {
      for (int j=0; j<3;j++) {
        expected[i][j] = new PixelImpl(64.13,57.02,44.43);
      }
    }
    image = sepia.apply(image);
    assertEquals(image, expected);
  }
}
