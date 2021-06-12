package model;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import javax.imageio.ImageIO;
import model.image.ImageImpl;
import model.image.InstaImage;
import model.pixel.Pixel;
import model.pixel.PixelImpl;

public class InstagramLayerModelImpl extends InstagramModelImpl implements InstagramLayerModel {

  HashMap<String, InstaImage> layerMap;
  String currentLayer;

  InstagramLayerModelImpl() {
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
  public void exportImage(String filepath) throws IOException {
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
    ImageIO.write(exportImage, fileName[1], new File(fileName[0]));
  }

  @Override
  public void read(String filepath) throws IOException {
    BufferedImage imported = ImageIO.read(new File(filepath));
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
