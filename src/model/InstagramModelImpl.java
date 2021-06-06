package model;

import model.FilterOperation.BlurOperation;
import model.FilterOperation.SharpenOperation;
import model.Pixel.Pixel;
import model.TransformOperation.GreyscaleOperation;
import model.TransformOperation.SepiaToneOperation;

public class InstagramModelImpl implements InstagramModel{

  Pixel[][] pixelGrid;

  public InstagramModelImpl(Pixel[][] pixelGrid) {
    this.pixelGrid = pixelGrid;
  }

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
    pixelGrid = new BlurOperation().apply(pixelGrid);
  }

  private void sharpenImage() {
    pixelGrid = new SharpenOperation().apply(pixelGrid);
  }

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
    pixelGrid = new GreyscaleOperation().apply(pixelGrid);
  }

  private void sepiaToneImage() {
    pixelGrid = new SepiaToneOperation().apply(pixelGrid);
  }

  @Override
  public void readPPM(String filename) {
    this.pixelGrid = ImageUtil.readPPM(filename);
  }
}
