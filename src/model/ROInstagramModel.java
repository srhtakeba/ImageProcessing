package model;

import java.awt.image.BufferedImage;

public interface ROInstagramModel {

  /**
   * Exports the visible image in this model and exports as a BufferedImage. Since only the visible
   * layers will be included in the 'visible image', this techinically returns the image that
   * represents the top most visible layer in the model.
   *
   * @return the top most visible layer in this model
   */
  BufferedImage exportImage();

}
