package model.mosaic;

import model.image.InstaImage;

/**
 * Interface to represent {code Mosaic}.
 */
public interface Mosaic {

  /**
   * Apply this mosaic operation to the given image by using the seed method.
   *
   * @param image the {@code InstaImage} image to be mosaic-ed.
   * @param seed  the random seed to be used for the mosaic process.
   * @return the mosaic image.
   */
  InstaImage apply(InstaImage image, int seed);
}
