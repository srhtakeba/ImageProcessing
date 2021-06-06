package model.TransformOperation;

import model.Pixel.Pixel;

/**
 * Class to represent a operation that can be performed on an image which uses a 2DArray of
 * {@code Pixel} to represent the image. A transformation operation represents a color transformation,
 * and will change the hue of each pixel in the image.
 */
public interface TransformOperation {

  /**
   * Apply this color transformation to the given image by using the transform matrix specific to this color
   * transformation, and multiplying it against the given {@code Pixel[][]} image.
   * @param image the image to be transformed.
   * @return the transformed image.
   */
  public Pixel[][] apply(Pixel[][] image);

}
