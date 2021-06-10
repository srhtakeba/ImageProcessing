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
 * FilterImpl holds kernels and applies kernel to images in order to alter their appearance. Kernel
 * application will change the RGB values of the pixels to do so.
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
   * @param image the {@code InstaImage} to be filtered.
   * @return the filtered image.
   */
  @Override
  public InstaImage apply(InstaImage image) {
    return image.filter(this.kernel);
  }



}