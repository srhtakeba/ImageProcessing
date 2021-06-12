package model;

import java.io.IOException;

public interface InstagramLayerModel extends InstagramModel {
  /**
   * Adds a new layer to this model, with the given name.
   * @param layerName title for the new layer
   */
  void addLayer(String layerName);

  /**
   * Removes the layer with the given name in this model.
   * @param layerName title of the layer to be removed.
   */
  void removeLayer(String layerName);

  /**
   * Sets the current working layer to the one with the given name in this model.
   * @param layerName title of the current layer to be working on.
   */
  void setCurrentLayer(String layerName);

  /**
   * Exports the final image, blending the layers, to a file with the given filepath name. If the
   * file already exists, this method will over write it. The file path must also include the
   * '.---' extensions to specify the file format.
   * @param filepath the file path for the export.
   */
  void exportImage(String filepath) throws IllegalStateException;

  void read(String filepath) throws IllegalStateException;
}
