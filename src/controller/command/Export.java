package controller.command;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import model.InstagramLayerModel;

/**
 * Class to send a command of exporting the current a layer to a {code InstagramLayerModel}.
 */
public class Export implements InstagramLayerCommand {

  private final String str;

  /**
   * Constructs {@code Export} object.
   *
   * @param str name for the layer which is being exported
   */
  public Export(String str) {
    this.str = str;
  }

  /**
   * This go() method for Export will convert a BufferedImage, sent from the model, to a file.
   *
   * @param model {code InstagramLayerModel} which holds image processing functions
   * @throws IllegalArgumentException if the fileName given is invalid
   * @throws IllegalStateException    if writing to the file fails
   */
  @Override
  public void dispatchCommand(InstagramLayerModel model) {
    String[] fileName = this.str.split("\\.");
    // check that there was a dot in the file path
    if (fileName.length < 2) {
      throw new IllegalArgumentException("Invalid file. Must include '.--' extension");
    }
    BufferedImage currentImage = model.exportImage();
    try {
      ImageIO.write(currentImage, fileName[1], new File(this.str));
    } catch (IOException ioe) {
      throw new IllegalStateException("Writing to the file failed.");
    }
  }

}