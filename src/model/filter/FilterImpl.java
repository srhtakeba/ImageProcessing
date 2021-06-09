package model.filter;

import model.image.ImageImpl;
import model.image.InstaImage;
import model.pixel.Channel;
import model.pixel.ChannelB;
import model.pixel.ChannelG;
import model.pixel.ChannelR;
import model.pixel.Pixel;
import model.pixel.PixelImpl;

/**
 * FilterImpl holds kernel and apply kernel to images.
 */
public abstract class FilterImpl implements FilterOperation {

  private final Double[][] kernel;

  FilterImpl(Double[][] kernel) {
    this.kernel = kernel;
  }

  /**
   * Apply this filter operation to the given image by using the kernel specific to this filter
   * operation, and 'layering' it over the given image.
   *
   * @param pixelGrid the pixel grid to be filtered.
   * @return the filtered image.
   */
  @Override
  public InstaImage apply(Pixel[][] pixelGrid) {
    Channel[][] imageR = new Channel[pixelGrid.length][pixelGrid[0].length];
    Channel[][] imageG = new Channel[pixelGrid.length][pixelGrid[0].length];
    Channel[][] imageB = new Channel[pixelGrid.length][pixelGrid[0].length];
    for (int i = 0; i < pixelGrid.length; i++) {
      for (int j = 0; j < pixelGrid[i].length; j++) {
        imageR[i][j] = new ChannelR(pixelGrid[i][j].getR().getValue());
        imageG[i][j] = new ChannelG(pixelGrid[i][j].getG().getValue());
        imageB[i][j] = new ChannelB(pixelGrid[i][j].getB().getValue());
      }
    }
    process(imageR);
    process(imageG);
    process(imageB);

    Pixel[][] returnGrid = new PixelImpl[pixelGrid.length][pixelGrid[0].length];
    for (int i = 0; i < pixelGrid.length; i++) {
      for (int j = 0; j < pixelGrid[i].length; j++) {
//        image[i][j].setR(imageR[i][j].getValue());
//        image[i][j].setG(imageG[i][j].getValue());
//        image[i][j].setB(imageB[i][j].getValue());

        returnGrid[i][j] = new PixelImpl(imageR[i][j].getValue(),
            imageG[i][j].getValue(), imageB[i][j].getValue());
      }
    }
    InstaImage returnImage = new ImageImpl(returnGrid, pixelGrid.length, pixelGrid[0].length);
    return returnImage;
  }

  /**
   * Given a matrix of just a specific channel (R,G, or B), use this filters kernel to process the
   * filter calculation for that specific channel. EFFECT: Alters the channel values.
   *
   * @param channelMatrix the channel to be processed.
   */
  private void process(Channel[][] channelMatrix) {
    Double[][] copyValues = new Double[channelMatrix.length][channelMatrix[0].length];
    for (int i = 0; i < channelMatrix.length; i++) {
      for (int j = 0; j < channelMatrix[0].length; j++) {
        copyValues[i][j] = Double.valueOf(channelMatrix[i][j].getValue());
      }
    }

    int kernelHalf = (int) kernel.length / 2;

    // iterate through the channelMatrix
    for (int i = 0; i < channelMatrix.length; i++) {
      for (int j = 0; j < channelMatrix[i].length; j++) {
        double appliedResult = 0;

        // iterate through the kernel
        for (int k = 0; k < kernel.length; k++) {
          for (int d = 0; d < kernel[k].length; d++) {
            int kDiff = i;
            int dDiff = j;
            if (k < kernelHalf) {
              kDiff = i - k;
            } else if (k > kernelHalf) {
              kDiff = k + i;
            }

            if (d < kernelHalf) {
              dDiff = j - d;
            } else if (d > kernelHalf) {
              dDiff = j + d;
            }

            // checking the bounds
            if ((kDiff < channelMatrix.length)
                && (kDiff >= 0)
                && (dDiff < channelMatrix[i].length)
                && (dDiff >= 0)) {

              appliedResult += (copyValues[kDiff][dDiff]) * kernel[k][d];
            }
          }
        }
        channelMatrix[i][j].setValue(appliedResult);
      }
    }

  }

}