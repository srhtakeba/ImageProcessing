package model.filter;

/**
 * Class to represent a Blur kernel filter. When this filter is applied, the resulting image will
 * appear to be less in-focus, and lower in quality.
 */
public class BlurOperation extends FilterImpl {

  /**
   * Constructs {@code BlurOperation} kernel object.
   */
  public BlurOperation() {
    super(new Double[][]{{0.0625, 0.125, 0.0625},
        {0.125, 0.25, 0.125},
        {0.0625, 0.125, 0.0625}});
  }
}
