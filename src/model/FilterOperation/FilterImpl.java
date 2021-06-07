package model.FilterOperation;

import model.Pixel.Channel;
import model.Pixel.ChannelImpl;
import model.Pixel.Pixel;

public abstract class FilterImpl implements FilterOperation {

  private final Double[][] kernel; // the kernel of this specific Filter operation

  FilterImpl(Double[][] kernel) {
    this.kernel = kernel;
  }

  /**
   * Apply this filter operation to the given image by using the kernel specific to this filter
   * operation, and 'layering' it over the given image.
   *
   * @param image the image to be filtered.
   * @return the filtered image.
   */
  @Override
  public Pixel[][] apply(Pixel[][] image) {
    Channel[][] imageR = new Channel[image.length][image[0].length];
    Channel[][] imageG = new Channel[image.length][image[0].length];
    Channel[][] imageB = new Channel[image.length][image[0].length];
    for (int i = 0; i < image.length; i++) {
      for (int j = 0; j < image[i].length; j++) {
        imageR[i][j] = image[i][j].getR();
        imageG[i][j] = image[i][j].getG();
        imageB[i][j] = image[i][j].getB();
      }
    }
    process(imageR);
    process(imageG);
    process(imageB);

    for (int i = 0; i < image.length; i++) {
      for (int j = 0; j < image[i].length; j++) {
        image[i][j].setR(imageR[i][j].getValue());
        image[i][j].setG(imageG[i][j].getValue());
        image[i][j].setB(imageB[i][j].getValue());
      }
    }

    return image;
  }

  /**
   * Given a matrix of just a specific channel (R,G, or B), use this filters kernel to process the
   * filter calculation for that specific channel. EFFECT: Alters the channel values.
   *
   * @param channelMatrix the channel to be processed.
   */
  private void process(Channel[][] channelMatrix) {
    Double[][] copy = new Double[channelMatrix.length][channelMatrix[0].length];
    for (int i = 0; i < channelMatrix.length; i++) {
      for (int j = 0; j < channelMatrix[0].length; j++) {
        copy[i][j] = Double.valueOf(channelMatrix[i][j].getValue());
      }
    }

    int kernelHalf = (int) kernel.length / 2;
    // iterate through the channelMatrix
    for (int i = 0; i < channelMatrix.length; i++) {
      for (int j = 0; j < channelMatrix[0].length; j++) {
        double appliedResult = 0;
        for (int k = 0; k < kernel.length; k++) {
          for (int d = 0; d < kernel[0].length; d++) {
            int kDiff = (kernel.length - i) - k;
            int dDiff = (kernel[0].length - j) - d;
            if ((kDiff < kernel.length) && (kDiff >= 0) && (dDiff < kernel[0].length) && (dDiff
                >= 0)) {
              int channelSurroundiIndex;
              int channelSurroundjIndex;
              if (k > kernelHalf) {
                channelSurroundiIndex = k - kernelHalf + i;
              } else if (k == kernelHalf) {
                channelSurroundiIndex = i;
              } else {
                channelSurroundiIndex = i - kernelHalf - k;
              }
              if (d > kernelHalf) {
                channelSurroundjIndex = d - kernelHalf + j;
              } else if (d == kernelHalf) {
                channelSurroundjIndex = j;
              } else {
                channelSurroundjIndex = j - kernelHalf - d;
              }

              appliedResult +=
                  (copy[channelSurroundiIndex][channelSurroundjIndex]) * kernel[k][d];
            }
          }

        }
        channelMatrix[i][j].setValue(appliedResult);
      }
    }

  }

}