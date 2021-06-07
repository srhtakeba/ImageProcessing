package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import model.FilterOperation.BlurOperation;
import model.FilterOperation.SharpenOperation;
import model.InstaImage.InstaImage;
import model.TransformOperation.GreyscaleOperation;
import model.TransformOperation.SepiaToneOperation;

public class InstagramModelImpl implements InstagramModel {

  InstaImage image;

  public InstagramModelImpl(InstaImage image) {
    this.image = image;
  }

  // convenience constructor
  public InstagramModelImpl() {
    this(null);
  }

  /**
   * Given a String token to represent an operation, perform that filter operation on this
   * InstagramModel's image.
   *
   * @param operation String token to represent operation
   * @throws IllegalStateException if the model holds no image to be processed.
   */
  @Override
  public void filter(String operation) throws IllegalStateException {
    if(this.image == null) {
      throw new IllegalStateException("There is no image to be filtered.");
    }
    switch (operation.toUpperCase()) {
      case "BLUR":
        blurImage();
        break;
      case "SHARPEN":
        sharpenImage();
        break;
      default:
        break;
    }
  }

  private void blurImage() {
    new BlurOperation().apply(image.getPixelGrid());
  }

  private void sharpenImage() {
    new SharpenOperation().apply(image.getPixelGrid());
  }

  /**
   * Given a String token to represent an operation, perform that color transformation on this
   * InstagramModel's image.
   *
   * @param operation String token to represent operation
   * @throws IllegalStateException if the model holds no image to be processed
   */
  @Override
  public void transform(String operation) throws IllegalStateException {
    if(this.image == null) {
      throw new IllegalStateException("There is no image to be transformed.");
    }
    switch (operation.toUpperCase()) {
      case "GREYSCALE":
        greyscaleImage();
        break;
      case "SEPIA":
        sepiaToneImage();
        break;
      default:
        break;
    }
  }

  private void greyscaleImage() { new GreyscaleOperation().apply(image.getPixelGrid());
  }

  private void sepiaToneImage() {
    new SepiaToneOperation().apply(image.getPixelGrid());
  }

  /**
   * Given the file name of the PPM, read that into this InstagramModel so that it can be used in
   * image processing.
   *
   * @param filename of the PPM file
   * @throws IllegalArgumentException if the given filename or file is invalid.
   */
  @Override
  public void readPPM(String filename) throws IllegalArgumentException {
    try {
      this.image = ImageUtil.readPPM(filename);
    }
    catch (IllegalArgumentException e) {
      throw new IllegalArgumentException(e.getMessage());
    }
  }

  /**
   * Export the image as a PPM file. Holding the width, height, maximum pixel capacity, and the rgb
   * values for each pixel. Creates a new file, naming it instaImage.ppm, and writes the image
   * content to it for this image.
   *
   * @return the String that holds the PPM file content
   * @throws IllegalStateException if the file creation, export did not work, or if there is no
   *                               image to be exported.
   */
  @Override
  public void exportAsPPM() throws IllegalStateException {
    if(this.image == null) {
      throw new IllegalStateException("There is not image to be exported.");
    }
    String filename = "instaImage.ppm";
    int fileNo = 1;
    // create the file
    try {
      File export = new File(filename);
      // if the file already exists, add a number at the end of the file name to create a unique one
      // e.g. 'instaImage(1).ppm'
      boolean creationSuccess = export.createNewFile();
      while (!creationSuccess) {
        filename = "instaImage(" + fileNo + ").ppm";
        export = new File(filename);
        creationSuccess = export.createNewFile();
        fileNo++;
      }
    } catch (IOException e) {
      throw new IllegalStateException("Unsuccessful file creation.");
    }
    // write the ppm content to the file
    try {
      FileWriter writer = new FileWriter(filename);
      writer.write(instaImageToPPMFormat());
      writer.close();
    } catch (IOException e) {
      throw new IllegalStateException("Unsuccessful write to file.");
    }

  }

  /**
   * Convert the image into PPM content in string format. Holding the width, height, maximum pixel
   * capacity, and the rgb values for each pixel.
   *
   * @return the String that holds the PPM file content
   */
  private String instaImageToPPMFormat() {
    StringBuilder sb = new StringBuilder();
    sb.append("P3\n");
    sb.append(image.getWidth() + " " + image.getHeight() + "\n" + "255\n");
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        String r = image.getPixelGrid()[i][j].getR().toString();
        String g = image.getPixelGrid()[i][j].getG().toString();
        String b = image.getPixelGrid()[i][j].getB().toString();
        sb.append(r + " " + g + " " + b + "\n");
      }
    }

    return sb.toString();
  }

  /**
   * Export the image as a {@code InstaImage} object which holds the pixel grid of the image and the
   * proportions of the image in pixels.
   *
   * @return the {@code InstaImage} data of the image.
   * @throws IllegalStateException if there is no image to be exported.
   */
  @Override
  public InstaImage exportAsInstaImage() {
    if (this.image == null) {
      throw new IllegalStateException("There is not image to be exported.");
    }
    return this.image;
  }

  /**
   * Given the {@code InstaImage} object, read the image into the model for processing
   *
   * @param image the image to be read into this model
   * @throws IllegalArgumentException if the given image is null
   */
  @Override
  public void readInstaImage(InstaImage image) throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Can not process a null image.");
    }
    this.image = image;
  }

}
