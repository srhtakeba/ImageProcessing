package model;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * Interface to represent Read Only model.
 */
public interface ROInstagramModel {

  /**
   * Exports the visible image in this model and exports as a BufferedImage. Since only the visible
   * layers will be included in the 'visible image', this techinically returns the image that
   * represents the top most visible layer in the model.
   *
   * @return the top most visible layer in this model
   */
  BufferedImage exportImage();

  /**
   * Returns the name of the current layer in the model.
   *
   * @return the name of the layer currently being worked on.
   */
  String currentLayer();

  /**
   * Returns the names of all layers in the model.
   *
   * @return a list containing the names of all layers in this model, in order
   */
  List<String> getLayerNames();

}
