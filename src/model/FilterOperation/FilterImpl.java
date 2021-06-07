package model.FilterOperation;

import model.Pixel.Channel;
import model.Pixel.Pixel;

public abstract class FilterImpl implements FilterOperation {

  private final Double[][] kernel; // the kernel of this specific Filter operation

  FilterImpl(Double[][] kernel) {
    this.kernel = kernel;
  }

  /**
   * Apply this filter operation to the given image by using the kernel specific to this filter
   * operation, and 'layering' it over the given image.
   * @param image the image to be filtered.
   * @return the filtered image.
   */
  @Override
  public Pixel[][] apply(Pixel[][] image) {
    Channel[][] imageR = new Channel[image.length][image[0].length];
    Channel[][] imageG = new Channel[image.length][image[0].length];
    Channel[][] imageB = new Channel[image.length][image[0].length];
    for(int i=0;i<image.length;i++) {
      for(int j=0; j<image[i].length;j++) {
        imageR[i][j] = image[i][j].getR();
        imageG[i][j] = image[i][j].getG();
        imageB[i][j] = image[i][j].getB();
      }
    }
    process(imageR);
    process(imageG);
    process(imageB);

    return image;
  }

  /**
   * Given a matrix of just a specific channel (R,G, or B), use this filters kernel to process
   * the filter calculation for that specific channel. EFFECT: Alters the channel values.
   * @param channelMatrix the channel to be processed.
   */
  private void process(Channel[][] channelMatrix) {
    // iterate through the channelMatrix
    for (int i = 0; i < channelMatrix.length; i++) {
      for (int j = 0; j < channelMatrix[0].length; j++) {
        double appliedResult = 0;

        for (int k = 0; k < kernel.length; k++) {
          for (int d = 0; d < kernel[0].length; d++) {
            /*
            if (k < channelMatrix.length && j < channelMatrix[0].length && k >=0 && d>= 0) {
              appliedResult += (channelMatrix[i][j].getValue()) * kernel[k][d];
            }*/
            if ( ((kernel.length-i)-k<kernel.length) && ((kernel.length-i)-k >= 0)
                && ((kernel[i].length-j)-d<kernel[i].length) && ((kernel[i].length-j)-d>=0)) {
              appliedResult += (channelMatrix[i][j].getValue()) * kernel[k][d];
            }
          }

        }
        channelMatrix[i][j].setValue(appliedResult);
      }
    }
  }

}