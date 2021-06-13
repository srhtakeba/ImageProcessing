package model;



/*
ASSIGNMENT 6 NOTES:
- To WRITE to files:
  - Use ImageIO.write(RenderedImage im, String formatName, File output)
    - Use BufferedImage(int width, int height, TYPE_INT_RGB).setRGB(int x, int y, int rgb)
      Where BufferedImage is a child class of RenderedImage and int rgb is a merged integer.
      We would use ((red<<16)|(green<<8)|blue) to convert our rgb values into a merged int.
        - or we could use new Color(int r, int g, int b).getRGB(), which would do the same
    - Use getWriterFormatNames() to get the list of string format names. Seems like Strings such as
      "jpeg" can be used.
    - Use new File(String pathname)
- to READ files:
  - Use ImageIO.read(File input) which returns a BufferedImage
    - Again, use new File(String pathname)
  - From the resulting BufferedImage, .getHeight(), .getWidth(), .getRGB(int x, int y)
  - Construct a new Color(img.getRGB(int x, int y)).getR() etc to get the individual rgb values
    as ints.
- Possible view SYNTAX codes:
  - 'new' + 'layer_name' to create a new layer
  - 'remove' + 'layer_name' to remove a layer
  - 'read' + 'file_name' + '.' + 'formatName' to load images
  - 'export' + 'file_name' + '.' + 'formatName' to save as a file
  - 'filter' + 'blur'/'sharpen' to filter
  - 'transform' + 'sepia'/'greyscale' to transform
  - 'current' + 'layer_name' to work on a specific layer
- GOOD LINKS:
  - https://docs.oracle.com/javase/7/docs/api/javax/imageio/ImageIO.html#write(java.awt.image.RenderedImage,%20java.lang.String,%20java.io.File)
  - https://docs.oracle.com/javase/7/docs/api/java/io/File.html#File(java.lang.String)
  - https://docs.oracle.com/javase/7/docs/api/java/awt/image/BufferedImage.html#getRGB(int,%20int)
  - https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html
  - https://docs.oracle.com/javase/7/docs/api/java/io/FileInputStream.html
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.NavigableMap;
import java.util.TreeMap;
import javax.imageio.ImageIO;
import model.image.ImageImpl;
import model.image.InstaImage;
import model.pixel.Pixel;
import model.pixel.PixelImpl;

/**
 * TO DO: - Make a controller that can pass data onto the model for file writing and stuff - Make a
 * view (only has to be text-based) that can do like choose between 1) Write interactively 2) Load a
 * command file And then it would read the commands from the user and make layers and all this stuff
 * - We need to be careful about separating all of these things - Make a new model interface that
 * extends InstagramModel that adds the layer feature.
 */

public class InstagramLayerModelImpl extends InstagramModelImpl implements InstagramLayerModel {

  NavigableMap<String, InstaImage> layerMap;
  String currentLayer;
  private Integer width;
  private Integer height;

  public InstagramLayerModelImpl() {
    super();
    this.layerMap = new TreeMap<String, InstaImage>();
    this.currentLayer = "";
  }

  /**
   * Adds a new layer to this model, with the given name.
   *
   * @param layerName title for the new layer
   */
  @Override
  public void addLayer(String layerName) {
    //InstaImage layer = new ImageImpl();
    layerMap.put(layerName, null);
  }

  /**
   * Removes the layer with the given name in this model.
   *
   * @param layerName title of the layer to be removed.
   */
  @Override
  public void removeLayer(String layerName) throws IllegalArgumentException {
    if (!layerMap.containsKey(layerName)) {
      throw new IllegalArgumentException("The layer with the provided name does not exist.");
    }
    layerMap.remove(layerName);
  }

  /**
   * Sets the current working layer to the one with the given name in this model.
   *
   * @param layerName title of the current layer to be working on.
   */
  @Override
  public void setCurrentLayer(String layerName) throws IllegalArgumentException {
    if (!layerMap.containsKey(layerName)) {
      throw new IllegalArgumentException("The layer with the provided name does not exist.");
    }
    this.image = layerMap.get(layerName);
    this.currentLayer = layerName;
  }

  /**
   * Exports the final image, blending the layers, to a file with the given filepath name. If the
   * file already exists, this method will over write it. The file path must also include the '.---'
   * extensions to specify the file format.
   *
   * @param filepath the file path for the export.
   */
  @Override
  public void exportImage(String filepath) throws IllegalStateException {
//    BufferedImage base = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_RGB);
//    Graphics2D g = base.createGraphics();
//
//    for (String key : layerMap.navigableKeySet()) {
//      InstaImage imgTemp = layerMap.get(key);
//
//      if (!(imgTemp == null)) {
//        BufferedImage currentImage = convert(imgTemp);
//
//        g.drawImage(currentImage, 0, 0, null);
//      }
//    }
//    g.dispose();
    BufferedImage currentImage = convert(this.image);
    String[] fileName = filepath.split("\\.");
    // check that there was a dot in the file path
    if (fileName.length < 2) {
      throw new IllegalArgumentException("Invalid file. Must include '.--' extension");
    }
    try {
      ImageIO.write(currentImage, fileName[1], new File(filepath));
    } catch (IOException ioe) {
      throw new IllegalStateException("Writing to the file failed.");
    }
  }

  /**
   * Converts the given InstaImage to a BufferedImage.
   *
   * @param img the {@code InstaImage} to be converted
   * @return a {@code BufferedImage} object that represents the given InstaImage
   */
  private BufferedImage convert(InstaImage img) {
    BufferedImage result = new BufferedImage(img.getWidth(), img.getHeight(),
        BufferedImage.TYPE_INT_RGB);
    Pixel[][] currentPixelGrid = img.getPixelGrid();

    for (int i = 0; i < img.getHeight(); i++) {
      for (int j = 0; j < img.getWidth(); j++) {
        Pixel currentPixel = currentPixelGrid[i][j];
        Color currentColor = new Color(currentPixel.getR().getValue(),
            currentPixel.getG().getValue(), currentPixel.getB().getValue());

        result.setRGB(j, i, currentColor.getRGB());
      }
    }
    return result;
  }

  @Override
  public void read(String filepath) throws IllegalStateException, IllegalArgumentException {
    String[] fileParts = filepath.split("\\.");
    if (fileParts[1].equals("ppm")) {
      this.readPPM(filepath);
      return;
    }
    BufferedImage imported;
    try {
      imported = ImageIO.read(new File(filepath));
    } catch (IOException ioe) {
      throw new IllegalStateException("Reading from the file failed.");
    }
    int width = imported.getWidth();
    int height = imported.getHeight();
    Pixel[][] importedPGrid = new Pixel[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        Color currentColor = new Color(imported.getRGB(j, i));
        int r = currentColor.getRed();
        int g = currentColor.getGreen();
        int b = currentColor.getBlue();
        importedPGrid[i][j] = new PixelImpl(r, g, b);
      }
    }
    // check if this is the first image to be imported
    if (this.width == null) {
      this.width = width;
    }
    if (this.height == null) {
      this.height = height;
    }
    // if it isn't, check that the new imported image is of the same proportions as the current
    // proportions of this model
    if (this.height != height || this.width != width) {
      throw new IllegalArgumentException("The given image is of invalid proportions.");
    }
    this.image = new ImageImpl(importedPGrid, width, height);

    layerMap.put(this.currentLayer, this.image);
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
    super.filter(operation);
    this.layerMap.replace(currentLayer, this.image);
  }

  /**
   * Given a String token to represent an operation, perform that color transformation on this
   * InstagramModel's image.
   *
   * @param operation String token to represent operation
   * @throws IllegalStateException if the model holds no image to be processed.
   */
  @Override
  public void transform(String operation) throws IllegalStateException {
    super.transform(operation);
    this.layerMap.replace(currentLayer, this.image);
  }

//  /**
//   * Helper method to calcurate the average.
//   *
//   * @return a PixelImpl containing the average RGB value.
//   */
//  private InstaImage rgbAverage() {
//    int rVal = 0;
//    int gVal = 0;
//    int bVal = 0;
//    int count = 0;
//
////    Pixel rgb;
//
////    Channel[][] imageR = new Channel[height][width];
////    Channel[][] imageG = new Channel[height][width];
////    Channel[][] imageB = new Channel[height][width];
//    for (String key: layerMap.keySet()) {
//      InstaImage imgTemp = layerMap.get(key);
//      Pixel[][] pixcelGridTemp = imgTemp.getPixelGrid();
//
//      for (int i = 0; i < imgTemp.getHeight(); i++) {
//        for (int j = 0; j < imgTemp.getWidth(); j++) {
//          rVal += pixcelGridTemp[i][j].getR().getValue();
//          gVal += pixcelGridTemp[i][j].getG().getValue();
//          bVal += pixcelGridTemp[i][j].getB().getValue();
//        }
//      }
//      count += 1;
//    }
//    Pixel rgbAveraged = new PixelImpl((rVal / count), (gVal / count), (bVal / count));
//
//    return new ImageImpl(rgbAveraged, 0, 0);

  /**
   * Saves this model's multi-layered image into a new folder with the exports for each image,
   * plus a text file that organizes those images.
   * @param dirName the name for the directory of this project.
   * @throws IllegalStateException
   */
  @Override
  public void save(String dirName) throws IllegalStateException {
    File directory = new File(dirName);
    // make the new directory
    boolean creationSuccess = directory.mkdir();
    if(!creationSuccess) {
      throw new IllegalStateException("Making the new directory failed.");
    }
    File mainText = new File(dirName + "/main.txt");
    // writing the script for the main file
    StringBuilder mainSB = new StringBuilder();
    for (String key : layerMap.navigableKeySet()) {
      InstaImage imgTemp = layerMap.get(key);
      mainSB.append("new ").append(key).append("\n");

      if (!(imgTemp == null)) {
        mainSB.append("read ");
        mainSB.append(dirName).append("/");
        mainSB.append(key).append(".png").append("\n");
      }
    }
    // writing to the main file
    try {
      BufferedWriter writer = new BufferedWriter(new FileWriter(mainText));
      writer.write(mainSB.toString());
      writer.close();
    }
    catch (IOException ioe) {
      throw new IllegalStateException("Writing to the file failed.");
    }

    // export each layer to the new directory
    String curTemp = new String(currentLayer);
    for (String key : layerMap.navigableKeySet()) {
      setCurrentLayer(key);
      exportImage(dirName+"/"+key+".png");
    }
    // set the current layer back to what it was before the iteration
    setCurrentLayer(curTemp);
  }


}
