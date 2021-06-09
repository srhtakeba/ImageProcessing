package model.transform;

/**
 * Class to represent a greyscale color transformation on an image. A Greyscale transformation will
 * change the images hue so it is comprised solely of black and white shades.
 */
public class GreyscaleOperation extends AbstractTransformOperation {

  /**
   * Constructs {@code GreyscaleOperation} object.
   */
  public GreyscaleOperation() {
    super(new Double[][]{{0.2126, 0.7152, 0.0722},
        {0.2126, 0.7152, 0.0722},
        {0.2126, 0.7152, 0.0722}});
  }
}
