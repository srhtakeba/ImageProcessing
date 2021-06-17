package model.image;

import model.pixel.Pixel;

/**
 * Interface to represent a Image.
 */
public interface InstaImage {

  /**
   * Creates a checkerboard image represented in pixels.
   *
   * @param size size of the board
   * @return checkerboard image represented in pixels
   */
  ImageImpl makeCheckerBoard(int size);

  /**
   * Creates a Rainbow image represented in pixels.
   *
   * @param width       width of the image, in pixels
   * @param colorHeight height of each color in the resulting rainbow image, in pixels
   * @return Rainbow image represented in pixels
   */
  ImageImpl makeRainbow(int width, int colorHeight);

  /**
   * Getter method for the pixel grid of this image. Returns a copy of the pixel grid, not a
   * reference to the pixel grid itself.
   *
   * @return the pixel grid.
   */
  Pixel[][] getPixelGrid();

  /**
   * Getter method for the width of this image.
   *
   * @return int value width of this image in pixels.
   */
  int getWidth();

  /**
   * Getter method for the height of this image.
   *
   * @return int value height of this image in pixels.
   */
  int getHeight();

  /**
   * Produces a {@code String} representation for this {@code InstaImage}.
   *
   * @return the {@code String} representation
   */
  @Override
  String toString();

  /**
   * Produces a filtered version of this {@code InstaImage} using the given filter kernel.
   *
   * @param kernel the kernel to be used to filter over this image.
   * @return the filtered version of this image.
   */
  InstaImage filter(Double[][] kernel);

  /**
   * Produces a color transformed version of this {@code InstaImage} using the given transformation
   * matrix.
   *
   * @param tMatrix the matrix to be multiplied over this images pixel matrix to color transform
   *                it.
   * @return the transformed version of this image
   */
  InstaImage transform(Double[][] tMatrix);

}
