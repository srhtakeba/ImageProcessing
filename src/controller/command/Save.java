package controller.command;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.NavigableMap;
import javax.imageio.ImageIO;
import model.InstagramLayerModel;

/**
 * Class to send a command of saving the existing images of the {@code InstagramLayerModel}.
 */
public class Save implements InstagramLayerCommand {

  private final String str;

  /**
   * Constructs {@code Save} object.
   *
   * @param str name of the directory
   */
  public Save(String str) {
    this.str = str;
  }

  @Override
  public void dispatchCommand(InstagramLayerModel model) {
    constructDirectory(this.str);
    // writing to the main file
    File mainText = new File(this.str + "/main.txt");
    try {
      BufferedWriter writer = new BufferedWriter(new FileWriter(mainText));
      writer.write(model.getMainTextString(this.str));
      writer.close();
    } catch (IOException ioe) {
      throw new IllegalStateException("Writing to the file failed.");
    }

    // write each image to the new directory
    NavigableMap<String, BufferedImage> allLayers = model.allLayersSave(this.str);
    for (String filepath : allLayers.navigableKeySet()) {
      BufferedImage currentImage = allLayers.get(filepath);
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
  }

  /**
   * Check if the directory with the given name already exists, empty it if it does. If not,
   * construct a new directory with the given name.
   *
   * @param dirName the name of the directory.
   */
  private void constructDirectory(String dirName) {
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
