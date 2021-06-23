package controller;

/**
 * Interface to represent features and receive user input from the Interactive mode.
 */
public interface Features {

  /**
   * Set the current working layer in the model.
   *
   * @param layerName the title of the layer to be set to current
   */
  void setCurrent(String layerName);

  /**
   * Save this entire Instagram project.
   *
   * @param dirName the path of the directory for it to be saved to
   */
  void saveProject(String dirName);

  /**
   * Open an entire Instagram project.
   *
   * @param dirpath the path of the project to be opened.
   */
  void openProject(String dirpath);

  /**
   * Read a script that contains commands for this model.
   *
   * @param filepath the path of the script to be read.
   */
  void importScript(String filepath);

  /**
   * Import an image into the current working layer of this model.
   *
   * @param filepath the path of the image to be imported.
   */
  void importImage(String filepath);

  /**
   * Export the current projects top most visible layer.
   *
   * @param filepath the destination of this export, including the file type extension.
   */
  void exportImage(String filepath);

  /**
   * Blur the image on the current layer.
   */
  void blur();

  /**
   * Sharpen the image on the current layer.
   */
  void sharpen();

  /**
   * Greyscale the image on the current layer.
   */
  void greyscale();

  /**
   * Sepia the image on the current layer.
   */
  void sepia();

  /**
   * Mosaic the image on the current layer by a certain seed.
   */
  void mosaic(String seed);

  /**
   * Make the given layer visible. If already visible, no change is made.
   *
   * @param layerName the name of the layer
   */
  void makeVisible(String layerName);

  /**
   * Make the given layer invisible. If already invisible, no change is made.
   *
   * @param layerName the name of the layer
   */
  void makeInvisible(String layerName);

  /**
   * Add a layer with the given name.
   *
   * @param layerName the name of the new layer
   * @return whether the addition was successful. If a layer with the name already exists, this
   *         layer can not be added.
   */
  boolean addLayer(String layerName);

  /**
   * Remove the layer with the given name.
   *
   * @param layerName the name of the layer to be removed.
   * @return whether the removal was successful. If a layer with the given name does not exist, this
   *         layer can not be removed.
   */
  boolean removeLayer(String layerName);
}
