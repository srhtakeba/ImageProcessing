package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;
import model.filter.BlurOperation;
import model.filter.FilterOperation;
import model.filter.SharpenOperation;
import model.image.ImageImpl;
import model.image.InstaImage;
import model.pixel.Pixel;
import model.transform.GreyscaleOperation;
import model.transform.SepiaToneOperation;
import model.transform.TransformOperation;

/**
 * An implementation of {@code InstagramModel} that uses the {@code InstaImage} object type to
 * represent its images. Clients are able to save and retrieve their images as they process them
 * using the given processing methods filter and transform. Currently images can be exported as PPM
 * files or {@code InstaImage} objects. Same types apply for importing images. This particular model
 * uses the {@code ImageImpl} subclass of {@code InstaImage} for exporting images.
 */
public class InstagramModelImpl implements InstagramModel {

  protected InstaImage image;
  protected Stack<InstaImage> log;

  public InstagramModelImpl(InstaImage image) {
    this.image = image;
    this.log = new Stack<>();
  }

  // convenience constructor
  public InstagramModelImpl() {
    this(null);
  }

  /**
   * Given a String token to represent an operation, perform that filter operation on this
   * InstagramModel's image.
   *
   * @param operation String token to represent operation.
   * @throws IllegalStateException    if the model holds no image to be processed.
   * @throws IllegalArgumentException if the given filter operation is invalid.
   */
  @Override
  public void filter(String operation) throws IllegalStateException, IllegalArgumentException {
    FilterOperation filterOperation;
    if (this.image == null) {
      throw new IllegalStateException("There is no image to be filtered.");
    }
    switch (operation.toUpperCase()) {
      case "BLUR":
        filterOperation = new BlurOperation();
        break;
      case "SHARPEN":
        filterOperation = new SharpenOperation();
        break;
      default:
        throw new IllegalArgumentException("Given filter operation is invalid.");
    }
    this.image = filterOperation.apply(image);
  }

  /**
   * Given a String token to represent an operation, perform that color transformation on this
   * InstagramModel's image.
   *
   * @param operation String token to represent operation.
   * @throws IllegalStateException    if the model holds no image to be processed.
   * @throws IllegalArgumentException if the given transform operation is invalid.
   */
  @Override
  public void transform(String operation) throws IllegalStateException, IllegalArgumentException {
    TransformOperation transformOperation;
    if (this.image == null) {
      throw new IllegalStateException("There is no image to be transformed.");
    }
    switch (operation.toUpperCase()) {
      case "GREYSCALE":
        transformOperation = new GreyscaleOperation();
        break;
      case "SEPIA":
        transformOperation = new SepiaToneOperation();
        break;
      default:
        throw new IllegalArgumentException("Given transform operation is invalid.");
    }
    this.image = transformOperation.apply(image);
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
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException(e.getMessage());
    }
  }

  /**
   * Export the image as a PPM file. Holding the width, height, maximum pixel capacity, and the rgb
   * values for each pixel. Creates a new file, naming it instaImage.ppm, and writes the image
   * content to it for this image.
   *
   * @param title the desired name for the resulting exported ppm file
   * @throws IllegalStateException if the file creation, export did not work, or if there is no
   *                               image to be exported.
   */
  @Override
  public void exportAsPPM(String title) throws IllegalStateException {
    if (this.image == null) {
      throw new IllegalStateException("There is no image to be exported.");
    }
    String filename = title + ".ppm";
    int fileNo = 1;
    // Create the file
    try {
      File export = new File(filename);
      // if the file already exists, add a number at the end of the file name to Create a unique one
      // e.g. 'instaImage(1).ppm'
      boolean creationSuccess = export.createNewFile();
      while (!creationSuccess) {
        filename = title + "(" + fileNo + ").ppm";
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
    Pixel[][] pixelGrid = image.getPixelGrid();
    int height = image.getHeight();
    int width = image.getWidth();
    StringBuilder sb = new StringBuilder();
    sb.append("P3\n");
    sb.append(width).append(" ").append(height).append("\n").append("255\n");
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        String r = pixelGrid[i][j].getR().toString();
        String g = pixelGrid[i][j].getG().toString();
        String b = pixelGrid[i][j].getB().toString();
        sb.append(r).append(" ").append(g).append(" ").append(b).append("\n");
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
    int copyHeight = this.image.getHeight();
    int copyWidth = this.image.getWidth();
    Pixel[][] copy = this.image.getPixelGrid();
    return new ImageImpl(copy, copyWidth, copyHeight);
  }

  /**
   * Given the {@code InstaImage} object, read the image into the model for processing.
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

  /**
   * Save the current image to this model's log.
   *
   * @throws IllegalStateException if there is no image to be saved
   */
  @Override
  public void save() throws IllegalStateException {
    if (this.image == null) {
      throw new IllegalStateException("No image to be saved.");
    }
    int copyHeight = this.image.getHeight();
    int copyWidth = this.image.getWidth();
    Pixel[][] copy = this.image.getPixelGrid();
    this.log.push(new ImageImpl(copy, copyWidth,
        copyHeight));
  }

  /**
   * Return to the last saved image in this model's log. You can not undo a retrieve.
   *
   * @throws IllegalStateException if there is no image to be retrieved
   */
  @Override
  public void retrieve() {
    if (this.log.size() <= 0) {
      throw new IllegalStateException("No image to be retrieved.");
    }
    InstaImage prev = exportAsInstaImage();
    this.image = this.log.peek();
    this.log.pop();
  }

}
