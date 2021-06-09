package model.filter;

/**
 * Class to represent a Blur kernel filter.
 */
public class BlurOperation extends FilterImpl {

  /**
   * Constructs {@CODE BlurOperation} kernel object.
   */
  public BlurOperation() {
    super(new Double[][]{{0.0625, 0.125, 0.0625},
        {0.125, 0.25, 0.125},
        {0.0625, 0.125, 0.0625}});
  }
}
