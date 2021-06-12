package model;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import javax.imageio.ImageIO;
import model.image.ImageImpl;
import model.image.InstaImage;
import model.pixel.Pixel;
import model.pixel.PixelImpl;

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
 */

/** TO DO:
 * - Make a controller that can pass data onto the model for file writing and stuff
 * - Make a view (only has to be text-based) that can do like choose between
 *    1) Write interactively
 *    2) Load a command file
 *   And then it would read the commands from the user and make layers and all this stuff
 * - We need to be careful about separating all of these things
 * - Make a new model interface that extends InstagramModel that adds the layer feature.
 */

public class InstagramLayerModelImpl extends InstagramModelImpl implements InstagramLayerModel {

  HashMap<String, InstaImage> layerMap;
  String currentLayer;

  public InstagramLayerModelImpl() {
    super();
    this.layerMap = new HashMap<>();
    this.currentLayer = "";
  }

  /**
   * Adds a new layer to this model, with the given name.
   *
   * @param layerName title for the new layer
   */
  @Override
  public void addLayer(String layerName) {
    Pixel[][] emptyGrid = new PixelImpl[0][0];
    InstaImage layer = new ImageImpl(emptyGrid, 0, 0);
    layerMap.put(layerName, layer);
  }

  /**
   * Removes the layer with the given name in this model.
   *
   * @param layerName title of the layer to be removed.
   */
  @Override
  public void removeLayer(String layerName) throws IllegalArgumentException {
    if(!layerMap.containsKey(layerName)) {
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
    if(!layerMap.containsKey(layerName)) {
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
    BufferedImage exportImage = new BufferedImage(this.image.getWidth(), this.image.getHeight(),
        BufferedImage.TYPE_INT_RGB);
    Pixel[][] exportPixelGrid = this.image.getPixelGrid();
    for(int i=0;i<this.image.getHeight();i++) {
      for(int j=0;j<this.image.getWidth();j++) {
        Pixel currentPixel = exportPixelGrid[i][j];
        Color currentColor = new Color(currentPixel.getR().getValue(),
            currentPixel.getG().getValue(), currentPixel.getB().getValue());
        exportImage.setRGB(j, i, currentColor.getRGB());
      }
    }
    String[] fileName = filepath.split(".");
    // check that there was a dot in the file path
    if(fileName.length<2) {
      throw new IllegalArgumentException("Invalid file. Must include '.--' extension");
    }
    try {
      ImageIO.write(exportImage, fileName[1], new File(fileName[0]));
    }
    catch (IOException ioe) {
      throw new IllegalStateException("Writing to the file failed.");
    }
  }

  @Override
  public void read(String filepath) throws IllegalStateException {
    BufferedImage imported;
    try {
      imported = ImageIO.read(new File(filepath));
    }
    catch (IOException ioe) {
      throw new IllegalStateException("Reading from the file failed.");
    }
    int width = imported.getWidth();
    int height = imported.getHeight();
    Pixel[][] importedPGrid = new Pixel[height][width];
    for(int i=0;i<height;i++) {
      for(int j=0;j<width;j++) {
        Color currentColor = new Color(imported.getRGB(j,i));
        int r = currentColor.getRed();
        int g = currentColor.getGreen();
        int b = currentColor.getBlue();
        importedPGrid[i][j] = new PixelImpl(r,g,b);
      }
    }
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
}
