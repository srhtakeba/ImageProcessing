import controller.Features;
import controller.IController;
import controller.command.InstagramLayerCommand;
import controller.command.InstagramLayerCommandFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.Scanner;
import model.InstagramLayerModel;

/**
 * Class to represent {@code MockGUIController}, which is a mock of {@code GUIController} for test
 * purposes.
 */
public class MockGUIController implements Features, IController {

  private final InstagramLayerModel model;
  private InstagramLayerCommand cmd;
  private final StringBuilder log;

  /**
   * Constructs {@code GUIController} object.
   */
  public MockGUIController(InstagramLayerModel model) {
    this.model = model;
    log = new StringBuilder();
  }

  public String getViewOutput() {
    return log.toString();
  }

  @Override
  public void setCurrent(String layerName) {
    cmd = InstagramLayerCommandFactory.create("current", layerName);
    try {
      cmd.dispatchCommand(model);
    } catch (Exception e) {
      sendMessage(e.getMessage());
    }
  }

  @Override
  public void saveProject(String dirName) {
    cmd = InstagramLayerCommandFactory.create("save", dirName);
    dispatchOrSendMessage(cmd);
  }

  @Override
  public void openProject(String dirpath) {
    this.openProject(dirpath);
  }

  @Override
  public void importScript(String filepath) {
    File script = new File(filepath);
    try {
      InputStream inStream = new FileInputStream(script);
      Scanner scan = new Scanner(inStream);
      readCommands(scan);
    } catch (FileNotFoundException e) {
      sendMessage("The given file was not found.");
    }
  }

  @Override
  public void importImage(String filepath) {
    cmd = InstagramLayerCommandFactory.create("read", filepath);
    dispatchOrSendMessage(cmd);
  }

  @Override
  public void exportImage(String filepath) {
    cmd = InstagramLayerCommandFactory.create("save", filepath);
    dispatchOrSendMessage(cmd);
  }

  @Override
  public void blur() {
    cmd = InstagramLayerCommandFactory.create("filter", "blur");
    dispatchOrSendMessage(cmd);
  }

  @Override
  public void sharpen() {
    cmd = InstagramLayerCommandFactory.create("filter", "sharpen");
    dispatchOrSendMessage(cmd);
  }

  @Override
  public void greyscale() {
    cmd = InstagramLayerCommandFactory.create("transform", "greyscale");
    dispatchOrSendMessage(cmd);
  }

  @Override
  public void sepia() {
    cmd = InstagramLayerCommandFactory.create("transform", "sepia");
    dispatchOrSendMessage(cmd);
  }

  @Override
  public void mosaic(String seed) {
    try {
      int seedConvert = Integer.valueOf(seed);
    } catch (Exception e) {
      sendMessage("Invalid seed value. Please enter an integer.");
      return;
    }
    cmd = InstagramLayerCommandFactory.create("mosaic", seed);
    dispatchOrSendMessage(cmd);
  }

  @Override
  public void makeVisible(String layerName) {
    cmd = InstagramLayerCommandFactory.create("visible", layerName);
    dispatchOrSendMessage(cmd);
  }

  @Override
  public void makeInvisible(String layerName) {
    cmd = InstagramLayerCommandFactory.create("invisible", layerName);
    dispatchOrSendMessage(cmd);
  }

  @Override
  public boolean addLayer(String layerName) {
    cmd = InstagramLayerCommandFactory.create("new", layerName);
    try {
      cmd.dispatchCommand(model);
    } catch (Exception e) {
      sendMessage(e.getMessage());
    }

    return model.getLayerNames().contains(layerName);
  }

  @Override
  public boolean removeLayer(String layerName) {
    cmd = InstagramLayerCommandFactory.create("remove", layerName);
    try {
      cmd.dispatchCommand(model);
    } catch (Exception e) {
      sendMessage(e.getMessage());
    }
    return !model.getLayerNames().contains(layerName);
  }

  @Override
  public void dispatchController() {
    // do nothing
  }

  /**
   * Read a previous project and open it in the current model.
   *
   * @param dirPath the path to the previous project.
   */
  private void loadPreviousProject(String dirPath) {
    String projectPath = dirPath + "/main.txt";
    File script = new File(projectPath);
    try {
      InputStream inStream = new FileInputStream(script);
      Scanner project = new Scanner(inStream);
      readCommands(project);
    } catch (FileNotFoundException e) {
      sendMessage("The given file was not found. A new project will be created.\n");
    }
  }

  /**
   * Reads commands to operate an {@code InstagramLayerModel} from the given scanner object.
   *
   * @param scan the scanner object to read commands from.
   */
  private void readCommands(Scanner scan) {
    InstagramLayerCommand cmd = null;
    while (scan.hasNext()) {
      String curr = scan.next();
      if (curr.equalsIgnoreCase("q")) {
        break;
      }
      try {
        String next = scan.next();
        if (curr.equalsIgnoreCase("q")) {
          break;
        }
        cmd = InstagramLayerCommandFactory.create(curr, next);
        if (cmd != null) {
          cmd.dispatchCommand(model);
        }
      } catch (InputMismatchException ime) {
        sendMessage("Bad length to " + curr + "\n");
      } catch (IllegalArgumentException iae) {
        sendMessage("Bad input: " + iae.getMessage() + "\n");
      } catch (IllegalStateException ise) {
        sendMessage("System error: " + ise.getMessage() + "\n");
      }
    }
  }

  /**
   * Add the message to the log.
   *
   * @param message the message to be sent
   */
  private void sendMessage(String message) {
    log.append(message);
  }

  /**
   * Dispatch the given command, catching any errors along the way and sending them to the view.
   * Update the view once the command has been dispatched.
   *
   * @param cmd the command to be dispatched.
   */
  private void dispatchOrSendMessage(InstagramLayerCommand cmd) {
    try {
      cmd.dispatchCommand(model);
    } catch (Exception e) {
      sendMessage(e.getMessage());
    }
  }
}