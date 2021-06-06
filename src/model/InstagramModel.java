package model;

import model.InstaImage.InstaImage;

public interface InstagramModel {

  /**
   * Given a String token to represent an operation, perform that filter operation on this
   * InstagramModel's image.
   * @param operation String token to represent operation
   */
  void filter(String operation);

  /**
   * Given a String token to represent an operation, perform that color transformation on this
   * InstagramModel's image.
   * @param operation String token to represent operation
   */
  void transform(String operation);

  /**
   * Given the file name of the PPM, read that into this InstagramModel so that it can be used
   * in image processing.
   * @param filename of the PPM file
   */
  void readPPM(String filename);

  /**
   * Export the image as a {@code InstaImage} object which holds the pixel grid of the image and
   * the proportions of the image in pixels.
   * @return the {@code InstaImage} data of the image.
   */
  InstaImage exportAsInstaImage();

  /**
   * Export the image as a PPM file in string format. Holding the width, height, maximum pixel
   * capacity, and the rgb values for each pixel.
   * @return the String that holds the PPM file content
   */
  String exportAsPPM();
}
