package model;

import java.awt.image.BufferedImage;
import java.util.NavigableMap;
import model.layer.Layer;

/**
 * Interface to represent InstagramLayerModel which holds layer functionality.
 */
public interface InstagramLayerModel extends InstagramModel, ROInstagramModel {

  /**
   * Adds a new layer to this model, with the given name.
   *
   * @param layerName title for the new layer
   * @throws IllegalArgumentException if the layer of the given title already exist
   */
  void addLayer(String layerName);

  /**
   * Removes the layer with the given name in this model.
   *
   * @param layerName title of the layer to be removed.
   * @throws IllegalArgumentException if the layer of the given title does not exist
   */
  void removeLayer(String layerName);

  /**
   * Makes the layer with the given name invisible. If the layer is already invisible, no change is
   * made.
   *
   * @param layerName title of the layer to be made invisible.
   * @throws IllegalArgumentException if the layer of the given title does not exist
   */
  void makeLayerInvisible(String layerName) throws IllegalArgumentException;

  /**
   * Makes the layer with the given name visible. If the layer is already visible, no change is
   * made.
   *
   * @param layerName title of the layer to be made visible.
   * @throws IllegalArgumentException if the layer of the given title does not exist
   */
  void makeLayerVisible(String layerName) throws IllegalArgumentException;

  /**
   * Sets the current working layer to the one with the given name in this model.
   *
   * @param layerName title of the current layer to be working on.
   */
  void setCurrentLayer(String layerName);

  /**
   * Exports the visible image in this model and exports as a BufferedImage. Since only the visible
   * layers will be included in the 'visible image', this techinically returns the image that
   * represents the top most visible layer in the model.
   *
   * @return the top most visible layer in this model
   */
  BufferedImage exportImage();

  /**
   * Reads the given image to the current layer, converting it to an {@code InstaImage}.
   *
   * @param imported the image to be imported
   * @throws IllegalStateException    if reading from the image fails
   * @throws IllegalArgumentException if the proportions of the given image are not compatible with
   *                                  the current model. or current layer is not selected.
   */
  void read(BufferedImage imported) throws IllegalStateException, IllegalArgumentException;

  /**
   * Returns the content to be written in the main text file when this project is saved.
   *
   * @param dirName the name of the project directory
   * @return the string content for the main file
   */
  String getMainTextString(String dirName);

  /**
   * Returns a navigable map of all layers in this model to be saved as {@code BufferedImage}s and
   * their corresponding filepaths.
   *
   * @param dirName the directory that these files will end up in.
   * @return the map containing all layers and their file paths
   */
  NavigableMap<String, BufferedImage> allLayersSave(String dirName);

  /**
   * Returns a deep copy of all layers stored in the NavigableMap.
   *
   * @return all layers stored in the NavigableMap
   */
  NavigableMap<String, Layer> getAllLayer();

  @Override
  boolean equals(Object o);

  @Override
  int hashCode();

  @Override
  String toString();

  /**
   * Perform a mosaic transformation on this InstagramModel's image.
   *
   * @param seed the random seed of the mosaic operation.
   * @throws IllegalStateException if the model holds no image to be processed.
   */
  void mosaic(int seed) throws IllegalStateException;

}
