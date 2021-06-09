package model.transform;

import model.image.InstaImage;
import model.pixel.Pixel;

/**
 * Class to represent a operation that can be performed on an image which uses a 2DArray of {@code
 * Pixel} to represent the image. A transformation operation represents a color transformation, and
 * will change the hue of each pixel in the image.
 */
public interface TransformOperation {

  /**
   * Apply this color transformation to the given image by using the transform matrix specific to
   * this color transformation, and multiplying it against the given {@code Pixel[][]} image.
   *
   * @param pixelGrid the pixel grid to be transformed.
   * @return the transformed image.
   */
  InstaImage apply(Pixel[][] pixelGrid);

}
