package model.transform;

import model.image.InstaImage;

/**
 * A class to represent the operations of a color transformation of an image. A color transformation
 * only changes the hue of each pixel according to the specific transformation to be applied.
 */
public abstract class AbstractTransformOperation implements TransformOperation {

  private final Double[][] transformMatrix;

  public AbstractTransformOperation(Double[][] matrix) {
    this.transformMatrix = matrix;
  }

  /**
   * Apply this color transformation to the given image by using the transform matrix specific to
   * this color transformation, and multiplying it against the given {@code InstaImage} image.
   *
   * @param image the {@code InstaImage} to be transformed.
   * @return the transformed image.
   */
  @Override
  public InstaImage apply(InstaImage image) {
    return image.transform(this.transformMatrix);
  }


}
