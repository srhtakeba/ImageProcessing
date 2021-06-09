package model;

import model.image.InstaImage;

/**
 * Interface to represent a model that can take in images and process them by changing quality or
 * colors patterns.
 */
public interface InstagramModel {

  /**
   * Given a String token to represent an operation, perform that filter operation on this
   * InstagramModel's image.
   *
   * @param operation String token to represent operation
   * @throws IllegalStateException if the model holds no image to be processed.
   */
  void filter(String operation) throws IllegalStateException;

  /**
   * Given a String token to represent an operation, perform that color transformation on this
   * InstagramModel's image.
   *
   * @param operation String token to represent operation
   * @throws IllegalStateException if the model holds no image to be processed.
   */
  void transform(String operation) throws IllegalStateException;

  /**
   * Given the file name of the PPM, read that into this InstagramModel so that it can be used in
   * image processing.
   *
   * @param filename of the PPM file
   * @throws IllegalArgumentException if the given filename or file is invalid.
   */
  void readPPM(String filename) throws IllegalArgumentException;

  /**
   * Export the image as a PPM file. Holding the width, height, maximum pixel capacity, and the rgb
   * values for each pixel.
   *
   * @param title the desired name for the resulting exported ppm file
   * @throws IllegalStateException if the file creation, export did not work, or if there is no
   *                               image to be exported.
   */
  void exportAsPPM(String title) throws IllegalStateException;

  /**
   * Export the image as a {@code InstaImage} object which holds the pixel grid of the image and the
   * proportions of the image in pixels.
   *
   * @return the {@code InstaImage} data of the image.
   * @throws IllegalStateException if there is no image to be exported.
   */
  InstaImage exportAsInstaImage() throws IllegalStateException;

  /**
   * Given the {@code InstaImage} object, read the image into the model for processing.
   *
   * @param image the image to be read into this model
   * @throws IllegalArgumentException if the given image is null
   */
  void readInstaImage(InstaImage image) throws IllegalArgumentException;

  /**
   * Save the current image to this model's log.
   *
   * @throws IllegalStateException if there is no image to be saved
   */
  void save() throws IllegalStateException;//

  /**
   * Return to the last saved image in this model's log. You can not undo a retrieve.
   *
   * @throws IllegalStateException if there is no image to be retrieved
   */
  void retrieve() throws IllegalStateException;
}
