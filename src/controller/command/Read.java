package controller.command;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import model.InstagramLayerModel;

/**
 * Class to send a command of reading an image to a {@code InstagramLayerModel}.
 */
public class Read implements InstagramLayerCommand {

  private final String str;

  /**
   * Constructs {@code Read} object.
   *
   * @param str name of the filepath
   */
  public Read(String str) {
    this.str = str;
  }

  /**
   * This go() method for Read will convert a file path to a BufferedImage, and then send that to
   * the model for processing.
   *
   * @param model {@code InstagramLayerModel} which holds image processing functions
   * @throws IllegalStateException if reading from the file fails
   */
  @Override
  public void dispatchCommand(InstagramLayerModel model) throws IllegalStateException {
    String[] fileParts = this.str.split("\\.");
    if (fileParts.length <= 1) {
      throw new IllegalStateException("Reading from the file failed.");
    }
    if (fileParts[1].equals("ppm")) {
      model.readPPM(this.str);
      return;
    }
    BufferedImage imported;
    try {
      imported = ImageIO.read(new File(this.str));
    } catch (IOException ioe) {
      throw new IllegalStateException("Reading from the file failed.");
    }
    model.read(imported);
  }

}