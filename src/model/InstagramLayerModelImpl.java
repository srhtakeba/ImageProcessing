package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.NavigableMap;
import java.util.TreeMap;
import javax.imageio.ImageIO;
import model.image.ImageImpl;
import model.image.InstaImage;
import model.pixel.Pixel;
import model.pixel.PixelImpl;

/**
 * This is a class to represent an Image Processing application that works similarly to {@code
 * InstagramModelImpl} but adds layer functionality. Users can add layers and work on them
 * individually as well export them as individual image files. This model supports exporting not
 * just as PPM files but also png and jpg files. As well exporting png and jpg files, this model can
 * also read png and jpg files. Projects are saved as 'multi-layered images', where there is a main
 * folder/directory, holding the images for each layer as a png image and a 'main.txt' script file
 * that can rebuild a project.
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
   * @throws IllegalArgumentException if the layer of the given title does not exist
   */
  @Override
  public void removeLayer(String layerName) throws IllegalArgumentException {
    if (!layerMap.containsKey(layerName)) {
      throw new IllegalArgumentException("The layer with the provided name does not exist.");
    }
    layerMap.remove(layerName);
  }

  /**
   * Makes the layer with the given name invisible. If the layer is already invisible, no change is
   * made.
   *
   * @param layerName title of the layer to be made invisible.
   * @throws IllegalArgumentException if the layer of the given title does not exist
   */
  @Override
  public void makeLayerInvisible(String layerName) throws IllegalArgumentException {
    if (!layerMap.containsKey(layerName)) {
      throw new IllegalArgumentException("The layer with the provided name does not exist.");
    }
    InstaImage layer = layerMap.get(layerName);
    // layer.makeInvisible();
  }

  /**
   * Makes the layer with the given name visible. If the layer is already visible, no change is
   * made.
   *
   * @param layerName title of the layer to be made visible.
   * @throws IllegalArgumentException if the layer of the given title does not exist
   */
  @Override
  public void makeLayerVisible(String layerName) {
    if (!layerMap.containsKey(layerName)) {
      throw new IllegalArgumentException("The layer with the provided name does not exist.");
    }
    InstaImage layer = layerMap.get(layerName);
    // layer.makeVisible();
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
    //BufferedImage currentImage = convert(this.image);
    BufferedImage base = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_RGB);
    Graphics2D g = base.createGraphics();
    BufferedImage currentImage = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_RGB);
    for (String key : layerMap.navigableKeySet()) {
      InstaImage imgTemp = layerMap.get(key);

      if(!(imgTemp == null)) { // and if the layer is visible...
        currentImage = convert(imgTemp);

        g.drawImage(currentImage, 0, 0, null);
      }
    }
    g.dispose();
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
   * Reads the given image to the current layer, converting it to an {@code InstaImage}.
   *
   * @param imported the image to be imported
   * @throws IllegalStateException    if reading from the image fails
   * @throws IllegalArgumentException if the proportions of the given image are not compatible with
   *                                  the current model.
   */
  @Override
  public void read(BufferedImage imported) throws IllegalStateException, IllegalArgumentException {
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
   * Saves this model's multi-layered image into a new folder with the exports for each image, plus
   * a text file that organizes those images. If a directory with the name already exists, this will
   * override that directory with the contents of this model.
   *
   * @param dirName the name for the directory of this project.
   * @throws IllegalStateException
   */
  @Override
  public void save(String dirName) throws IllegalStateException {
    // check if the directory/project with the same name already exists
    Path path = Paths.get(dirName);
    if (Files.exists(path) && Files.isDirectory(path)) {
      File exists = new File(dirName);
      deleteDirectory(exists);
    }
    File directory = new File(dirName);
    // make the new directory
    boolean creationSuccess = directory.mkdir();
    if (!creationSuccess) {
      throw new IllegalStateException("Making the new directory failed.");
    }
    File mainText = new File(dirName + "/main.txt");
    // writing to the main file
    try {
      BufferedWriter writer = new BufferedWriter(new FileWriter(mainText));
      writer.write(getMainTextString(dirName));
      writer.close();
    } catch (IOException ioe) {
      throw new IllegalStateException("Writing to the file failed.");
    }

    // export each layer to the new directory
    String curTemp = new String(currentLayer);
    for (String key : layerMap.navigableKeySet()) {
      setCurrentLayer(key);
      if (!(this.image == null)) {
        exportImage(dirName + "/" + key + ".png");
      }
    }
  }

  /**
   * Returns the content to be written in the main text file when this project is saved.
   *
   * @param dirName the name of the project directory
   * @return the string content for the main file
   */
  private String getMainTextString(String dirName) {
    // writing the script for the main file
    StringBuilder mainSB = new StringBuilder();
    for (String key : layerMap.navigableKeySet()) {
      InstaImage imgTemp = layerMap.get(key);
      mainSB.append("new ").append(key).append("\n");
      mainSB.append("current ").append(key).append("\n");

      if (!(imgTemp == null)) {
        mainSB.append("read ");
        mainSB.append(dirName).append("/");
        mainSB.append(key).append(".png").append("\n");
      }
    }
    return mainSB.toString();
  }

  /**
   * Empties and deletes the existing directory.
   *
   * @param dir the directory to be emptied
   */
  private void deleteDirectory(File dir) {
    File[] files = dir.listFiles();
    // if the file is just a file, not a directory, it will not have contents
    if (files != null) {
      for (File f : files) {
        deleteDirectory(f);
      }
    }
    // delete the directory
    dir.delete();
  }
}
