package model.InstaImage;

import model.ImageUtil;
import model.InstaImage.InstaImage;
import model.InstagramModelImpl;
import model.Pixel.Pixel;
import model.Pixel.PixelImpl;

public class ImageImpl implements InstaImage {

  Pixel[][] pixelGrid;
  int width;
  int height;

  public ImageImpl(Pixel[][] pixelGrid, int width, int height) {
    this.pixelGrid = pixelGrid;
    this.width = width;
    this.height = height;
  }

  public ImageImpl() {
    // don't make anything
  }

//  @Override
//  public InstagramModelImpl loadImage(String fileName) {
//    Pixel[][] pixel = ImageUtil.readPPM(fileName);
//    return pixel;
//  }

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
   * @param width  width of the image
   * @param height height of the image
   * @return Rainbow image represented in pixels
   * @throws IllegalArgumentException if the number of rows(height) is less than 7.
   */
  public ImageImpl makeRainbow(int width, int height) throws IllegalArgumentException {
    if (height < 7) {
      throw new IllegalArgumentException(
          "Can not construct rainbow with less than 7 rows of pixels.");
    }

    Pixel[][] pixel = new Pixel[height][width];

    int range = height / 7;

    for (int i = 0; i < height; i++) {
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
    this.height = height;
    ImageImpl image = new ImageImpl(pixel, width, height);
    return image;
  }

  public Pixel[][] getPixelGrid() {
    return this.pixelGrid;
  }

  public int getWidth() {
    return this.width;
  }

  public int getHeight() {
    return this.height;
  }

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
