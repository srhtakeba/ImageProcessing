package model.InstaImage;


import model.Pixel.Pixel;

public interface InstaImage {

  /**
   * Creates a checkerboard image represented in pixels.
   *
   * @param size size of the board
   * @return checkerboard image represented in pixels
   */
  public ImageImpl makeCheckerBoard(int size);

  /**
   * Creates a Rainbow image represented in pixels.
   *
   * @param width  width of the image
   * @param height height of the image
   * @return Rainbow image represented in pixels
   */
  public ImageImpl makeRainbow(int width, int height);

  /**
   * Getter method for the pixel grid of this image.
   * @return the pixel grid.
   */
  public Pixel[][] getPixelGrid();

  /**
   * Getter method for the width of this image.
   * @return int value width of this image in pixels.
   */
  public int getWidth();

  /**
   * Getter method for the height of this image.
   * @return int value height of this image in pixels.
   */
  public int getHeight();

}
