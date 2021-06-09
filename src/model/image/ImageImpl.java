package model.image;

import model.pixel.Pixel;
import model.pixel.PixelImpl;

/**
 * A subclass of {@code InstaImage} that uses the {@code Pixel} type to represent the pixels in the
 * image. An {@code ImageImpl} holds a pixel grid and a width and height. Clients can also generate
 * programmatic images using the built-in methods which will override the current pixelGrid, width,
 * and height of the {@code ImageImpl}.
 */
public class ImageImpl implements InstaImage {

  Pixel[][] pixelGrid;
  int width;
  int height;

  /**
   * Constructs a {@code ImageImpl} object.
   *
   * @param pixelGrid a {@code 2DArray} of {@code Pixel} holding all the pixels for the image.
   * @param width     the width of the image in pixels.
   * @param height    the height of the image in pixels.
   */
  public ImageImpl(Pixel[][] pixelGrid, int width, int height) {
    this.pixelGrid = pixelGrid;
    this.width = width;
    this.height = height;
  }

  /**
   * Convenience constructor for {@code ImageImpl} objects.
   */
  public ImageImpl() {
    // don't make anything
  }


  /**
   * Creates a checkerboard image represented in pixels.
   *
   * @param size size of the board
   * @return checkerboard image represented in pixels
   */
  public ImageImpl makeCheckerBoard(int size) {
    Pixel[][] pixel = new Pixel[size][size];

    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        if ((j + i) % 2 == 1) {
          pixel[i][j] = new PixelImpl(0, 0, 0);
        } else {
          pixel[i][j] = new PixelImpl(255, 255, 255);
        }
      }
    }
    this.pixelGrid = pixel;
    this.width = size;
    this.height = size;
    ImageImpl image = new ImageImpl(pixel, size, size);
    return image;
  }

  /**
   * Creates a Rainbow image represented in pixels.
   *
   * @param width       width of the image, in pixels
   * @param colorHeight height of each color in the resulting rainbow image, in pixels
   * @return Rainbow image represented in pixels
   */
  @Override
  public ImageImpl makeRainbow(int width, int colorHeight) {
    Pixel[][] pixel = new Pixel[colorHeight * 7][width];

    int range = colorHeight;

    for (int i = 0; i < pixel.length; i++) {
      for (int j = 0; j < width; j++) {
        // red
        if (0 <= i && i < range) {
          pixel[i][j] = new PixelImpl(255, 0, 0);
          // orange
        } else if (range <= i && i < 2 * range) {
          pixel[i][j] = new PixelImpl(255, 165, 0);
          // yellow
        } else if (2 * range <= i && i < 3 * range) {
          pixel[i][j] = new PixelImpl(255, 255, 0);
          // green
        } else if (3 * range <= i && i < 4 * range) {
          pixel[i][j] = new PixelImpl(0, 255, 0);
          // blue
        } else if (4 * range <= i && i < 5 * range) {
          pixel[i][j] = new PixelImpl(0, 0, 255);
          // indigo
        } else if (5 * range <= i && i < 6 * range) {
          pixel[i][j] = new PixelImpl(75, 0, 130);
          // violet
        } else if (6 * range <= i && i < 7 * range) {
          pixel[i][j] = new PixelImpl(238, 130, 238);
        }

      }
    }
    this.pixelGrid = pixel;
    this.width = width;
    this.height = colorHeight * 7;
    ImageImpl image = new ImageImpl(pixel, width, height);
    return image;
  }

  /**
   * Getter method for the pixel grid of this image.
   *
   * @return the pixel grid.
   */
  public Pixel[][] getPixelGrid() {
    return this.pixelGrid;
  }

  /**
   * Getter method for the width of this image.
   *
   * @return int value width of this image in pixels.
   */
  public int getWidth() {
    return this.width;
  }

  /**
   * Getter method for the height of this image.
   *
   * @return int value height of this image in pixels.
   */
  public int getHeight() {
    return this.height;
  }

  /**
   * Produces a {@code String} representation for this {@code InstaImage}.
   *
   * @return the {@code String} representation
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Width: " + this.width + "\n");
    sb.append("Height: " + this.height + "\n");
    for (int i = 0; i < this.pixelGrid.length; i++) {
      for (int j = 0; j < this.pixelGrid[i].length; j++) {
        sb.append(i + "," + j + ": " + this.pixelGrid[i][j] + "\n");
      }
    }
    return sb.toString();
  }


}
