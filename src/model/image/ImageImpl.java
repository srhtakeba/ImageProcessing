package model.image;


import model.pixel.Channel;
import model.pixel.ChannelB;
import model.pixel.ChannelG;
import model.pixel.ChannelR;
import model.pixel.Pixel;
import model.pixel.PixelImpl;

/**
 * A subclass of {@code InstaImage} that uses the {@code Pixel} type to represent the pixels in the
 * image. An {@code ImageImpl} holds a pixel grid and a width and height. Clients can also generate
 * programmatic images using the built-in methods which will override the current pixelGrid, width,
 * and height of the {@code ImageImpl}.
 */
public class ImageImpl implements InstaImage {

  private Pixel[][] pixelGrid;
  private int width;
  private int height;

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
   * Copy constructor for {@code ImageImpl} objects.
   *
   * @param source the source of this copy {@code ImageImpl}.
   */
  public ImageImpl(InstaImage source) {
    this(source.getPixelGrid(), source.getWidth(), source.getHeight());
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
    return new ImageImpl(pixel, size, size);
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

    for (int i = 0; i < pixel.length; i++) {
      for (int j = 0; j < width; j++) {
        // red
        if (0 <= i && i < colorHeight) {
          pixel[i][j] = new PixelImpl(255, 0, 0);
          // orange
        } else if (colorHeight <= i && i < 2 * colorHeight) {
          pixel[i][j] = new PixelImpl(255, 165, 0);
          // yellow
        } else if (2 * colorHeight <= i && i < 3 * colorHeight) {
          pixel[i][j] = new PixelImpl(255, 255, 0);
          // green
        } else if (3 * colorHeight <= i && i < 4 * colorHeight) {
          pixel[i][j] = new PixelImpl(0, 255, 0);
          // blue
        } else if (4 * colorHeight <= i && i < 5 * colorHeight) {
          pixel[i][j] = new PixelImpl(0, 0, 255);
          // indigo
        } else if (5 * colorHeight <= i && i < 6 * colorHeight) {
          pixel[i][j] = new PixelImpl(75, 0, 130);
          // violet
        } else if (6 * colorHeight <= i && i < 7 * colorHeight) {
          pixel[i][j] = new PixelImpl(238, 130, 238);
        }

      }
    }
    this.pixelGrid = pixel;
    this.width = width;
    this.height = colorHeight * 7;
    return new ImageImpl(pixel, width, height);
  }

  /**
   * Getter method for the pixel grid of this image. Returns a copy of the pixel grid, not a
   * reference to the pixel grid itself.
   *
   * @return the pixel grid.
   */
  public Pixel[][] getPixelGrid() {
    Pixel[][] copy = new Pixel[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        // use PixelImpl copy constructor
        copy[i][j] = new PixelImpl(pixelGrid[i][j]);
      }
    }
    return copy;
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
    sb.append("Width: ").append(this.width).append("\n");
    sb.append("Height: ").append(this.height).append("\n");
    for (int i = 0; i < this.pixelGrid.length; i++) {
      for (int j = 0; j < this.pixelGrid[i].length; j++) {
        sb.append(i).append(",").append(j).append(": ").append(this.pixelGrid[i][j]).append("\n");
      }
    }
    return sb.toString();
  }

  /**
   * Produces a filtered version of this {@code InstaImage} using the given filter kernel.
   *
   * @param kernel the kernel to be used to filter over this image.
   * @return the filtered version of this image.
   */
  @Override
  public InstaImage filter(Double[][] kernel) {
    Channel[][] imageR = new Channel[height][width];
    Channel[][] imageG = new Channel[height][width];
    Channel[][] imageB = new Channel[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        imageR[i][j] = new ChannelR(pixelGrid[i][j].getR().getValue());
        imageG[i][j] = new ChannelG(pixelGrid[i][j].getG().getValue());
        imageB[i][j] = new ChannelB(pixelGrid[i][j].getB().getValue());
      }
    }
    process(imageR, kernel);
    process(imageG, kernel);
    process(imageB, kernel);

    Pixel[][] returnGrid = new PixelImpl[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        returnGrid[i][j] = new PixelImpl(imageR[i][j].getValue(),
            imageG[i][j].getValue(), imageB[i][j].getValue());
      }
    }
    return new ImageImpl(returnGrid, width, height);
  }

  /**
   * Given a matrix of just a specific channel (R,G, or B), use the given filter kernel to process
   * the filter calculation for that specific channel. The kernel will move along each index of the
   * given channel matrix, and set it to a new value obtained from doing kernel multiplication with
   * the old values of the channel matrix. EFFECT: Alters the channel values.
   *
   * @param channelMatrix the channel to be processed.
   * @param kernel        the matrix to be used to filter over all the channel values.
   */
  private void process(Channel[][] channelMatrix, Double[][] kernel) {
    Double[][] copyValues = new Double[channelMatrix.length][channelMatrix[0].length];
    for (int i = 0; i < channelMatrix.length; i++) {
      for (int j = 0; j < channelMatrix[0].length; j++) {
        copyValues[i][j] = (double) channelMatrix[i][j].getValue();
      }
    }

    // get the half length of kernel, casting to int
    int kernelHalf = kernel.length / 2;

    // iterate through the channelMatrix
    for (int i = 0; i < channelMatrix.length; i++) {
      for (int j = 0; j < channelMatrix[i].length; j++) {
        double appliedResult = 0;

        // iterate through the kernel
        for (int k = 0; k < kernel.length; k++) {
          for (int d = 0; d < kernel[k].length; d++) {
            int kBound = i;
            int dBound = j;
            if (k < kernelHalf) {
              kBound = i - (kernelHalf - k);
            } else if (k > kernelHalf) {
              kBound = i + (k - kernelHalf);
            }
            if (d < kernelHalf) {
              dBound = j - (kernelHalf - d);
            } else if (d > kernelHalf) {
              dBound = j + (d - kernelHalf);
            }

            // checking the bounds
            if ((kBound < channelMatrix.length)
                && (kBound >= 0)
                && (dBound < channelMatrix[i].length)
                && (dBound >= 0)) {

              appliedResult += (copyValues[kBound][dBound]) * kernel[k][d];
            }
          }
        }
        channelMatrix[i][j].setValue(appliedResult);
      }
    }
  }

  /**
   * Produces a color transformed version of this {@code InstaImage} using the given transformation
   * matrix.
   *
   * @param tMatrix the matrix to be multiplied over this images pixel matrix to color transform
   *                it.
   * @return the transformed version of this image
   */
  @Override
  public InstaImage transform(Double[][] tMatrix) {
    Double[][] resultMatrix;
    Pixel[][] resultGrid = new Pixel[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        Double[][] rgbMatrix = new Double[][]{
            {(double) pixelGrid[i][j].getR().getValue()},
            {(double) pixelGrid[i][j].getG().getValue()},
            {(double) pixelGrid[i][j].getB().getValue()}};

        resultMatrix = multiply(rgbMatrix, tMatrix);

        resultGrid[i][j] = new PixelImpl(resultMatrix[0][0],
            resultMatrix[1][0], resultMatrix[2][0]);
      }
    }

    return new ImageImpl(resultGrid, width, height);
  }

  /**
   * Multiply the given {@code Double[][]} rgbMatrix with the given {@code Double[][]}
   * transformation matrix in order to transform the colors of the rgbMatrix.
   *
   * @param rgbMatrix the matrix to represent the three channel values of a pixel in an image.
   * @param tMatrix   the matrix to represent the color transformation that can be made to an
   *                  image.
   * @return the transformed three channel values in a matrix.
   */
  private Double[][] multiply(Double[][] rgbMatrix, Double[][] tMatrix) {
    Double[][] result = new Double[3][1];
    for (int i = 0; i < tMatrix.length; i++) {
      result[i][0] = 0.0;
      for (int j = 0; j < rgbMatrix.length; j++) {
        result[i][0] += tMatrix[i][j] * rgbMatrix[j][0];
      }
    }
    return result;
  }

}
