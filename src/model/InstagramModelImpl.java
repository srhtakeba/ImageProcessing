package model;

import model.FilterOperation.BlurOperation;
import model.FilterOperation.SharpenOperation;
import model.InstaImage.InstaImage;
import model.TransformOperation.GreyscaleOperation;
import model.TransformOperation.SepiaToneOperation;

public class InstagramModelImpl implements InstagramModel{

  InstaImage image;

  public InstagramModelImpl(InstaImage image) {
    this.image = image;
  }

  /**
   * Given a String token to represent an operation, perform that filter operation on this
   * InstagramModel's image.
   * @param operation String token to represent operation
   */
  @Override
  public void filter(String operation) {
    switch(operation.toUpperCase()) {
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
    /*image = new ImageImpl(new BlurOperation().apply(image.getPixelGrid()),
        image.getWidth(), image.getHeight());*/
  }

  private void sharpenImage() {
    new SharpenOperation().apply(image.getPixelGrid());
    /*image = new ImageImpl(new SharpenOperation().apply(image.getPixelGrid()),
        image.getWidth(), image.getHeight());*/
  }

  /**
   * Given a String token to represent an operation, perform that color transformation on this
   * InstagramModel's image.
   * @param operation String token to represent operation
   */
  @Override
  public void transform(String operation) {
    switch(operation.toUpperCase()) {
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

  private void greyscaleImage() {
    new GreyscaleOperation().apply(image.getPixelGrid());
    /*image = new ImageImpl(new GreyscaleOperation().apply(image.getPixelGrid()),
        image.getWidth(), image.getHeight());*/
  }

  private void sepiaToneImage() {
    new SepiaToneOperation().apply(image.getPixelGrid());
    /*image = new ImageImpl(new SepiaToneOperation().apply(image.getPixelGrid()),
        image.getWidth(), image.getHeight());*/
  }

  /**
   * Given the file name of the PPM, read that into this InstagramModel so that it can be used
   * in image processing.
   * @param filename of the PPM file
   */
  @Override
  public void readPPM(String filename) {
    this.image = ImageUtil.readPPM(filename);
  }

  /**
   * Export the image as a PPM file in string format. Holding the width, height, maximum pixel
   * capacity, and the rgb values for each pixel.
   *
   * @return the String that holds the PPM file content
   */
  @Override
  public String exportAsPPM() {
    StringBuilder sb = new StringBuilder();
    sb.append("p3\n");
    sb.append(image.getWidth() + " " + image.getHeight() + "\n" + "255\n");
    for(int i=0; i<image.getHeight(); i++) {
      for (int j=0; j<image.getWidth(); j++) {
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
   */
  @Override
  public InstaImage exportAsInstaImage() {
    return this.image;
  }

}
