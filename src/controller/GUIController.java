package controller;

import controller.command.InstagramLayerCommand;
import controller.command.InstagramLayerCommandFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.Scanner;
import model.InstagramLayerModel;
import model.InstagramLayerModelImpl;
import view.InstagramGUIView;
import view.InstagramJFrameView;

public class GUIController implements Features, IController {

  private final InstagramLayerModel model;
  private final InstagramGUIView view;
  private InstagramLayerCommand cmd;

  public GUIController() {
    model = new InstagramLayerModelImpl();
    view = new InstagramJFrameView(model);
    view.addFeatures(this);
  }

  @Override
  public void setCurrent(String layerName) {
    cmd = InstagramLayerCommandFactory.create("current", layerName);
    dispatchOrSendMessage(cmd);
  }

  @Override
  public void saveProject(String dirName) {
    cmd = InstagramLayerCommandFactory.create("save", dirName);
    dispatchOrSendMessage(cmd);
  }

  @Override
  public void openProject(String dirpath) {
    this.openProject(dirpath);
    view.display();
  }

  @Override
  public void importScript(String filepath) {
    File script = new File(filepath);
    try {
      InputStream inStream = new FileInputStream(script);
      Scanner scan = new Scanner(inStream);
    } catch (FileNotFoundException e) {
      sendMessage("The given file was not found.");
    }


    view.display();
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
    }
    catch (Exception e) {
      sendMessage(e.getMessage());
    }

    return model.getLayerNames().contains(layerName);
  }

  @Override
  public boolean removeLayer(String layerName) {
    cmd = InstagramLayerCommandFactory.create("remove", layerName);
    try {
      cmd.dispatchCommand(model);
    }
    catch (Exception e) {
      sendMessage(e.getMessage());
    }
    return !model.getLayerNames().contains(layerName);
  }

  @Override
  public void dispatchController() {

  }

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

  private void sendMessage(String message) {
    try {
      view.renderMessage(message);
    }
    catch (IOException ioe) {
      throw new IllegalStateException("Failed to send to the view.");
    }
  }

  private void dispatchOrSendMessage(InstagramLayerCommand cmd) {
    try {
      cmd.dispatchCommand(model);
      view.display();
    }
    catch (Exception e) {
      sendMessage(e.getMessage());
    }
  }
}
