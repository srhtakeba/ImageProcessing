package model.transform;

/**
 * Class to represent a sepia color transformation on an image. A Sepia transformation will change
 * the images hue so it mimics that of an older image with a red-brown tone.
 */
public class SepiaToneOperation extends AbstractTransformOperation {

  /**
   * Constructs {@code SepiaToneOperation} object.
   */
  public SepiaToneOperation() {
    super(new Double[][]{{0.393, 0.769, 0.189},
        {0.349, 0.686, 0.168},
        {0.272, 0.534, 0.131}});
  }
}
