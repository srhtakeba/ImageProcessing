package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Objects;
import java.util.TreeMap;
import model.image.ImageImpl;
import model.image.InstaImage;
import model.layer.Layer;
import model.layer.LayerImpl;
import model.mosaic.Mosaic;
import model.mosaic.MosaicImpl;
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

  private final NavigableMap<String, Layer> layerMap;
  private String currentLayer;
  private Integer width;
  private Integer height;

  /**
   * Constructs a new {@code InstagramLayerModelImpl} object.
   */
  public InstagramLayerModelImpl() {
    super();
    this.layerMap = new TreeMap<>();
    this.currentLayer = "";
  }

  /**
   * Adds a new layer to this model, with the given name.
   *
   * @param layerName title for the new layer
   * @throws IllegalArgumentException if the layer of the given title already exist
   */
  @Override
  public void addLayer(String layerName) {
    NavigableSet keySet = layerMap.navigableKeySet();
    List<String> strList = new ArrayList<String>(keySet);
    if (strList.contains(layerName)) {
      throw new IllegalArgumentException("The layer with the provided name already exists.");
    }

    layerMap.put(layerName, new LayerImpl());
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
    layerMap.get(layerName).makeInvisible();
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
    layerMap.get(layerName).makeVisible();
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
    if (layerMap.get(layerName).getImage() != null) {
      this.image = layerMap.get(layerName).getImage();
    } else {
      this.image = null;
    }
    this.currentLayer = layerName;
  }


  /**
   * Exports the visible image in this model and exports as a BufferedImage. Since only the visible
   * layers will be included in the 'visible image', this technically returns the image that
   * represents the top most visible layer in the model.
   *
   * @return the top most visible layer in this model
   * @throws IllegalStateException if there are no images imported yet to export
   */
  @Override
  public BufferedImage exportImage() throws IllegalStateException {
    boolean allNull = true;
    for (String key : layerMap.navigableKeySet()) {
      if (layerMap.get(key).getImage() != null) {
        allNull = false;
        break;
      }
    }
    if (allNull) {
      throw new IllegalStateException("No images to be exported.");
    }
    BufferedImage base = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_RGB);
    Graphics2D g = base.createGraphics();
    BufferedImage currentImage = new BufferedImage(this.width, this.height,
        BufferedImage.TYPE_INT_RGB);
    for (String key : layerMap.navigableKeySet()) {
      Layer currentLayer = layerMap.get(key);
      InstaImage imgTemp = currentLayer.getImage();

      if ((imgTemp != null) && currentLayer.getVisibility()) {
        currentImage = convert(imgTemp);

        g.drawImage(currentImage, 0, 0, null);
      }
    }
    g.dispose();
    return currentImage;
  }

  @Override
  public String currentLayer() {
    return currentLayer;
  }

  @Override
  public List<String> getLayerNames() {
    List<String> result = new ArrayList<>();
    for (String key : layerMap.navigableKeySet()) {
      result.add(key);
    }
    return result;
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

  /**
   * Reads the given image to the current layer, converting it to an {@code InstaImage}.
   *
   * @param imported the image to be imported
   * @throws IllegalArgumentException if the proportions of the given image are not compatible with
   *                                  the current model. or current layer is not selected.
   */
  @Override
  public void read(BufferedImage imported) throws IllegalArgumentException {
    if (this.currentLayer.equals("")) {
      throw new IllegalArgumentException("Select the current layer then read.");
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
    layerMap.get(this.currentLayer).setImage(this.image);
  }

  /**
   * Given a String token to represent an operation, perform that filter operation on this
   * InstagramModel's image.
   *
   * @param operation String token to represent operation
   * @throws IllegalStateException    if the model holds no image to be processed.
   * @throws IllegalArgumentException if the current layer is not selected.
   */
  @Override
  public void filter(String operation) throws IllegalStateException, IllegalArgumentException {
    if (this.currentLayer.equals("")) {
      throw new IllegalArgumentException("Select a layer to filter");
    }
    super.filter(operation);
    this.layerMap.get(currentLayer).setImage(this.image);
  }

  /**
   * Given a String token to represent an operation, perform that color transformation on this
   * InstagramModel's image.
   *
   * @param operation String token to represent operation
   * @throws IllegalStateException    if the model holds no image to be processed.
   * @throws IllegalArgumentException if the current layer is not selected.
   */
  @Override
  public void transform(String operation) throws IllegalStateException, IllegalArgumentException {
    if (this.currentLayer.equals("")) {
      throw new IllegalArgumentException("Select a layer to transform");
    }
    super.transform(operation);
    this.layerMap.get(currentLayer).setImage(this.image);
  }

  /**
   * Returns the content to be written in the main text file when this project is saved.
   *
   * @param dirName the name of the project directory
   * @return the string content for the main file
   */
  @Override
  public String getMainTextString(String dirName) {
    // writing the script for the main file
    StringBuilder mainSB = new StringBuilder();
    for (String key : layerMap.navigableKeySet()) {
      InstaImage imgTemp = layerMap.get(key).getImage();
      mainSB.append("new ").append(key).append("\n");
      mainSB.append("current ").append(key).append("\n");

      if (imgTemp != null) {
        mainSB.append("read ");
        mainSB.append(dirName).append("/");
        mainSB.append(key).append(".png").append("\n");
      }
    }
    return mainSB.toString();
  }

  /**
   * Returns a navigable map of all layers in this model to be saved as {@code BufferedImage}s and
   * their corresponding filepath.
   *
   * @param dirName the directory that these files will end up in.
   * @return the map containing all layers and their file paths
   */
  @Override
  public NavigableMap<String, BufferedImage> allLayersSave(String dirName) {
    NavigableMap<String, BufferedImage> allLayers = new TreeMap<>();
    // export each layer to the new directory
    for (String key : layerMap.navigableKeySet()) {
      if (layerMap.get(key).getImage() != null) {
        BufferedImage currentImage = convert(layerMap.get(key).getImage());
        allLayers.put(dirName + "/" + key + ".png", currentImage);
      }
    }
    return allLayers;
  }

  @Override
  public NavigableMap<String, Layer> getAllLayer() {
    return new TreeMap<>(this.layerMap);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof InstagramLayerModelImpl)) {
      return false;
    }
    InstagramLayerModelImpl that = (InstagramLayerModelImpl) o;
    return ((that.width.equals(this.width))
        && (that.height.equals(this.height))
        && (that.layerMap.equals(this.layerMap))
        && (that.currentLayer.equals(this.currentLayer)));
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.width + this.height, 2);
  }

  @Override
  public String toString() {
    return "" + this.currentLayer;
  }

  /**
   * Perform a mosaic transformation on this InstagramModel's image.
   *
   * @param seed the random seed of the mosaic operation.
   * @throws IllegalStateException if the model holds no image to be processed.
   */
  @Override
  public void mosaic(int seed) throws IllegalStateException {
    if (this.image == null) {
      throw new IllegalStateException("There is no image to be mosaic-ed.");
    }
    Mosaic m = new MosaicImpl();
    this.image = m.apply(this.image, seed);
    this.layerMap.get(currentLayer).setImage(this.image);
  }
}
