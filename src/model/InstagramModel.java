package model;

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

}
