package controller;

import controller.command.InstagramLayerCommand;
import controller.command.InstagramLayerCommandFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
    view = new InstagramJFrameView();
  }

  @Override
  public void setCurrent(String layerName) {
    cmd = InstagramLayerCommandFactory.create("current", layerName);
    cmd.dispatchCommand(model);
  }

  @Override
  public void saveProject(String dirName) {
    cmd = InstagramLayerCommandFactory.create("save", dirName);
    cmd.dispatchCommand(model);
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
    } catch (FileNotFoundException e) {
      view.renderMessage("The given file was not found.");
    }
  }

  @Override
  public void importImage(String filepath) {
    cmd = InstagramLayerCommandFactory.create("import", filepath);
    cmd.dispatchCommand(model);
  }

  @Override
  public void exportImage(String filepath) {
    cmd = InstagramLayerCommandFactory.create("save", filepath);
    cmd.dispatchCommand(model);
  }

  @Override
  public void blur() {
    cmd = InstagramLayerCommandFactory.create("filter", "blur");
    cmd.dispatchCommand(model);
  }

  @Override
  public void sharpen() {
    cmd = InstagramLayerCommandFactory.create("filter", "sharpen");
    cmd.dispatchCommand(model);
  }

  @Override
  public void greyscale() {
    cmd = InstagramLayerCommandFactory.create("transform", "greyscale");
    cmd.dispatchCommand(model);
  }

  @Override
  public void sepia() {
    cmd = InstagramLayerCommandFactory.create("transform", "sepia");
    cmd.dispatchCommand(model);
  }

  @Override
  public void makeVisible() {
    cmd = InstagramLayerCommandFactory.create("visible", model.currentLayer());
    cmd.dispatchCommand(model);
  }

  @Override
  public void makeInvisible() {
    cmd = InstagramLayerCommandFactory.create("invisible", model.currentLayer());
    cmd.dispatchCommand(model);
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
      view.renderMessage("The given file was not found. A new project will be created.\n");
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
        view.renderMessage("Bad length to " + curr + "\n");
      } catch (IllegalArgumentException iae) {
        view.renderMessage("Bad input: " + iae.getMessage() + "\n");
      } catch (IllegalStateException ise) {
        view.renderMessage("System error: " + ise.getMessage() + "\n");
      }
    }
  }
}
