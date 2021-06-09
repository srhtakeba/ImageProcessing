package model.filterOperation;

import model.pixel.Pixel;

/**
 * Filter operation holds filters and applies to images.
 */
public interface FilterOperation {

  /**
   * Apply this filter operation to the given image by using the kernel specific to this filter
   * operation, and 'layering' it over the given image.
   *
   * @param image the image to be filtered.
   * @return the filtered image.
   */
  public Pixel[][] apply(Pixel[][] image);
}
