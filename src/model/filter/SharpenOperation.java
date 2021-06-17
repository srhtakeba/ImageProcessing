package model.filter;

/**
 * Class to represent a Sharpen kernel filter. Application of this filter will cause the image to
 * look more 'loud' and vibrant. This could also be described as adding definition to edges in the
 * image, making them more harsh.
 */
public class SharpenOperation extends FilterImpl {

  /**
   * Constructs {@code SharpenOperation} kernel object.
   */
  public SharpenOperation() {
    super(new Double[][]{{-0.125, -0.125, -0.125, -0.125, -0.125},
        {-0.125, 0.25, 0.25, 0.25, -0.125},
        {-0.125, 0.25, 1.0, 0.25, -0.125},
        {-0.125, 0.25, 0.25, 0.25, -0.125},
        {-0.125, -0.125, -0.125, -0.125, -0.125}});
  }

}
