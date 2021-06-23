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
import view.InstagramTextView;
import view.InstagramView;

/**
 * A class to represent a controller for an OOD instagram program whose model is an {@code
 * InstagramLayerModel}. The controller allows functionality to interact with the model as well as
 * does the I/O for reading and exporting to external files. The view is a text view that can be
 * connected to a console or any other appendable. Users can interact with the controller and choose
 * to send in a script to the application rather than continue to use the interactive feature. Users
 * can also choose to reload a previous project, in which the controller will read from that
 * external directory and load it back into the model.
 */
public class Controller implements IController {

  private final Readable in;
  private InstagramLayerModel model;
  private final InstagramView view;

  /**
   * Creates a {@code Controller} object.
   *
   * @param in  the input stream for this controller
   * @param out the output stream that the view will render to.
   */
  public Controller(Readable in, Appendable out) {
    if (in == null) {
      throw new IllegalArgumentException("Null Readable is invalid.");
    }
    if (out == null) {
      throw new IllegalArgumentException("Null Appendable is invalid.");
    }
    this.in = in;
    this.view = new InstagramTextView(out);
  }

  @Override
  public void dispatchController() {
    Scanner scan = new Scanner(this.in);
    this.model = new InstagramLayerModelImpl();
    sendMessage("Welcome to OOD Instagram.\n");
    loadPreviousProject(scan);

    // Choose the interaction
    sendMessage("Would you like to\n 1) Use interactive. \n 2) Use a script.\n");
    sendMessage("Please type choice 1 or 2\n");
    int choice = scan.nextInt();
    switch (choice) {
      case 1:
        sendMessage("Begin interaction. Refer to USEME.md for detailed instructions. "
            + "Type Q/q to quit at anytime.\n");
        break;
      case 2:
        sendMessage("Please input the filepath to the script.\n");
        String filepath = scan.next();
        File script = new File(filepath);
        try {
          InputStream inStream = new FileInputStream(script);
          scan = new Scanner(inStream);
        } catch (FileNotFoundException e) {
          sendMessage("The given file was not found.");
        }
        break;
      default:
        sendMessage("Invalid choice. Type 1 or 2.\n");
        return;
    }

    readCommands(scan);
    sendMessage("Program has been quit.\n");
  }

  /**
   * Prompts the client if they would like to re-open an existing {@code InstagramLayerModelImpl}
   * project.
   *
   * @param scan the scanner that holds the user input.
   */
  private void loadPreviousProject(Scanner scan) {
    // Option to load an old project
    sendMessage("Would you like to open an existing project?\n");
    sendMessage("Type Y or N.\n");
    String open = scan.next();
    if (open.equalsIgnoreCase("Y")) {
      sendMessage("Please type the directory path of the project.\n");
      String projectPath = scan.next() + "/main.txt";
      File script = new File(projectPath);
      try {
        InputStream inStream = new FileInputStream(script);
        Scanner project = new Scanner(inStream);
        readCommands(project);
      } catch (FileNotFoundException e) {
        sendMessage("The given file was not found. A new project will be created.\n");
      }
    }
  }

  /**
   * Sends a message to this MVC program's view.
   *
   * @param message the message to be send to the view.
   * @throws IllegalStateException if there is an I/O Exception.
   */
  private void sendMessage(String message) throws IllegalStateException {
    try {
      view.renderMessage(message);
    } catch (IOException ioe) {
      throw new IllegalStateException("Sending to the view failed.");
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

}