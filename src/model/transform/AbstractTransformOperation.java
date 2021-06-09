package model.transform;

import model.image.ImageImpl;
import model.image.InstaImage;
import model.pixel.Pixel;
import model.pixel.PixelImpl;

/**
 * A class to represent the operations of a color transformation of an image. A color transformation
 * only changes the hue of each pixel according to the specific transformation to be applied.
 */
public abstract class AbstractTransformOperation implements TransformOperation {

  Double[][] transformMatrix;

  public AbstractTransformOperation(Double[][] matrix) {
    this.transformMatrix = matrix;
  }

  /**
   * Apply this color transformation to the given image by using the transform matrix specific to
   * this color transformation, and multiplying it against the given {@code Pixel[][]} image.
   *
   * @param pixelGrid the pixel grid to be transformed.
   * @return the transformed image.
   */
  @Override
  public InstaImage apply(Pixel[][] pixelGrid) {
    Double[][] resultMatrix;
    Pixel[][] resultGrid = new Pixel[pixelGrid.length][pixelGrid[0].length];
    for (int i = 0; i < pixelGrid.length; i++) {
      for (int j = 0; j < pixelGrid[i].length; j++) {
        Double[][] rgbMatrix = new Double[][]{
            {Double.valueOf(pixelGrid[i][j].getR().getValue())},
            {Double.valueOf(pixelGrid[i][j].getG().getValue())},
            {Double.valueOf(pixelGrid[i][j].getB().getValue())}};

        resultMatrix = multiply(rgbMatrix);

//        pixelGrid[i][j].setR(resultMatrix[0][0]);
//        pixelGrid[i][j].setG(resultMatrix[1][0]);
//        pixelGrid[i][j].setB(resultMatrix[2][0]);
        resultGrid[i][j] = new PixelImpl(resultMatrix[0][0],
            resultMatrix[1][0], resultMatrix[2][0]);
      }
    }

    InstaImage resultImage = new ImageImpl(resultGrid, resultGrid.length, resultGrid[0].length);
    return resultImage;
  }

  /**
   * Multiply the given {@code Double[][]} matrix with this {@code TranformOperation}'s transform
   * matrix.
   *
   * @param rgbMatrix the matrix to represent the three channel values of a pixel in an image.
   * @return the transformed three channel values in a matrix.
   */
  private Double[][] multiply(Double[][] rgbMatrix) {
    Double[][] result = new Double[3][1];
    for (int i = 0; i < this.transformMatrix.length; i++) {
      result[i][0] = 0.0;
      for (int j = 0; j < rgbMatrix.length; j++) {
        result[i][0] += this.transformMatrix[i][j] * rgbMatrix[j][0];
      }
    }
    return result;
  }

}
