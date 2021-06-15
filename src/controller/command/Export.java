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

  String str;

  /**
   * Constructs {@code Export} object.
   *
   * @param str name for the layer which is being exported
   */
  public Export(String str) {
    this.str = str;
  }

  @Override
  public void go(InstagramLayerModel model) {
    BufferedImage currentImage = model.exportImage();
    String[] fileName = this.str.split("\\.");
    // check that there was a dot in the file path
    if (fileName.length < 2) {
      throw new IllegalArgumentException("Invalid file. Must include '.--' extension");
    }
    try {
      ImageIO.write(currentImage, fileName[1], new File(this.str));
    } catch (IOException ioe) {
      throw new IllegalStateException("Writing to the file failed.");
    }
//    model.exportImage(str);
  }

}