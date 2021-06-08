package model.FilterOperation;

/**
 * Class to represent a Blur kernel filter.
 */
public class SharpenOperation extends FilterImpl {

  /**
   * Constructs {@CODE BlurOperation} kernel object.
   */
  public SharpenOperation() {
    super(new Double[][]{{-0.125, -0.125, -0.125, -0.125, -0.125},
        {-0.125, 0.25, 0.25, 0.25, -0.125},
        {-0.125, 0.25, 1.0, 0.25, -0.125},
        {-0.125, 0.25, 0.25, 0.25, -0.125},
        {-0.125, -0.125, -0.125, -0.125, -0.125}});
  }

}
