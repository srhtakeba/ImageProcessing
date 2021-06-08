package model.FilterOperation;

import model.Pixel.Channel;
import model.Pixel.Pixel;

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
      for (int j = 0; j < channelMatrix[i].length; j++) {
        double appliedResult = 0;

        // iterate through the kernel
        for (int k = 0; k < kernel.length; k++) {
          for (int d = 0; d < kernel[k].length; d++) {
            int kDiff = i;
            int dDiff = j;
            if(k<kernelHalf) {
              kDiff = i - k;
            }
            else if(k > kernelHalf) {
              kDiff = k + i;
            }

            if(d<kernelHalf) {
              dDiff = j - d;
            }
            else if(d > kernelHalf) {
              dDiff = j + d;
            }


            // checking the bounds
            if ((kDiff < channelMatrix.length)
                && (kDiff >= 0)
                && (dDiff < channelMatrix[i].length)
                && (dDiff >= 0)) {

              // Kernel needs to centred
              //appliedResult += (channelMatrix[kDiff][dDiff].getValue()) * kernel[k][d];
              appliedResult += (copy[kDiff][dDiff]) * kernel[k][d];
            }
          }
        }
        channelMatrix[i][j].setValue(appliedResult);
      }
    }

  }

}