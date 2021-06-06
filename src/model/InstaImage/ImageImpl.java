package model.Pixel;

import model.ImageUtil;
import model.InstaImage.InstaImage;
import model.InstagramModelImpl;

public class ImageImpl implements InstaImage {

  Pixel[][] pixelGrid;
  int width;
  int height;

  public ImageImpl(Pixel[][] pixelGrid, int width, int height) {
    this.pixelGrid = pixelGrid;
    this.width = width;
    this.height = height;
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
        if (j + 1 % 2 == 1) {
          pixel[i][j] = new PixelImpl(0, 0, 0);
        } else {
          pixel[i][j] = new PixelImpl(255, 255, 255);
        }
      }
    }
    ImageImpl image = new ImageImpl(pixel, size, size);
    return image;
  }

  /**
   * Creates a Rainbow image represented in pixels.
   *
   * @param width  width of the image
   * @param height height of the image
   * @return Rainbow image represented in pixels
   */
  public ImageImpl makeRainbow(int width, int height) {
    Pixel[][] pixel = new Pixel[width][height];

    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        // red, orange yellow
        if (0 <= j && j >= height / 7) {
          pixel[i][j] = new PixelImpl(250, 0, 0);
        } else if (0 <= j && j >= height / 6) {
          pixel[i][j] = new PixelImpl(255, 165, 0);
        } else if (height / 6 <= j && j >= height / 5) {
          pixel[i][j] = new PixelImpl(255, 255, 0);
        } else if (height / 5 <= j && j >= height / 4) {
          pixel[i][j] = new PixelImpl(0, 255, 0);
        } else if (height / 4 <= j && j >= height / 3) {
          pixel[i][j] = new PixelImpl(0, 0, 255);
        } else if (height / 3 <= j && j >= height / 2) {
          pixel[i][j] = new PixelImpl(0, 0, 255);
        } else if (height / 2 <= j && j >= height / 1) {
          pixel[i][j] = new PixelImpl(238,130,238);
        }

      }
    }
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



}
