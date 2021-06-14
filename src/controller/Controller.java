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

public class Controller implements IController {

  Readable in;
  Appendable out;
  InstagramLayerModel model;
  InstagramView view;

  public Controller(Readable in, Appendable out) {
    if(in == null) {
      throw new IllegalArgumentException("Null Readable is invalid.");
    }
    if(out == null) {
      throw new IllegalArgumentException("Null Appendable is invalid.");
    }
    this.out = out;
    this.in = in;
    this.view = new InstagramTextView(out);
  }

  @Override
  public void go() {
    Scanner scan = new Scanner(this.in);
    this.model = new InstagramLayerModelImpl();
    sendMessage("Welcome to OOD Instagram.\n");
    loadPreviousProject(scan);

    // Choose the interaction
    sendMessage("Would you like to\n 1) Use interactive. \n 2) Use a script.\n");
    sendMessage("Please type choice 1 or 2\n");
    Scanner input = new Scanner("");
    int choice = scan.nextInt();
    switch (choice) {
      case 1:
        input = new Scanner(this.in);
        sendMessage("Begin interaction. Refer to USEME.md for detailed instructions.\n");
        break;
      case 2:
        sendMessage("Please input the filepath to the script.\n");
        String filepath = scan.next();
        File script = new File(filepath);
        try {
          InputStream inStream = new FileInputStream(script);
          input = new Scanner(inStream);
        }
        catch (FileNotFoundException e) {
          sendMessage("The given file was not found.");
        }
        break;
      default:
        sendMessage("Invalid choice. Type 1 or 2.\n");
        return;
    }

    readCommands(input);
  }

  /**
   * Prompts the client if they would like to re-open an existing {@code InstagramLayerModelImpl}
   * project.
   * @param scan the scanner that holds the user input.
   */
  private void loadPreviousProject(Scanner scan) {
    // Option to load an old project
    sendMessage("Would you like to open an existing project?\n");
    sendMessage("Type Y or N.\n");
    String open = scan.next();
    switch (open.toUpperCase()) {
      case "Y":
        sendMessage("Please type the directory path of the project.\n");
        String projectPath = scan.next() + "/main.txt";
        File script = new File(projectPath);
        try {
          InputStream inStream = new FileInputStream(script);
          Scanner project = new Scanner(inStream);
          readCommands(project);
        }
        catch (FileNotFoundException e) {
          sendMessage("The given file was not found.");
        }
        break;
      default:
        //ignore
        break;
    }
  }

  /**
   * Sends a message to this MVC program's view.
   * @param message the message to be send to the view.
   * @throws IllegalStateException if there is an I/O Exception.
   */
  private void sendMessage(String message) throws IllegalStateException {
    try {
      view.renderMessage(message);
    }
    catch (IOException ioe) {
      throw new IllegalStateException("Sending to the view failed.");
    }
  }

  /**
   * Reads commands to operate an {@code InstagramLayerModel} from the given scanner object.
   * @param scan the scanner object to read commands from.
   */
  public void readCommands(Scanner scan) {
    InstagramLayerCommand cmd = null;
    while (scan.hasNext()) {
      String curr = scan.next();
      try {
        String next = scan.next();
        switch (curr) {
          case "new":
            cmd = InstagramLayerCommandFactory.create("create", next);
            break;
          case "remove":
            cmd = InstagramLayerCommandFactory.create("remove", next);
            break;
          case "read":
            cmd = InstagramLayerCommandFactory.create("read", next);
            break;
          case "current":
            cmd = InstagramLayerCommandFactory.create("current", next);
            break;
          case "transform":
            cmd = InstagramLayerCommandFactory.create("transform", next);
            break;
          case "filter":
            cmd = InstagramLayerCommandFactory.create("filter", next);
            break;
          case "export":
            cmd = InstagramLayerCommandFactory.create("export", next);
            break;
          case "save":
            cmd = InstagramLayerCommandFactory.create("save", next);
          default:
            break;
        }
        if (cmd != null) {
          cmd.go(model);
        }
      } catch (InputMismatchException ime) {
        sendMessage("Bad length to " + curr+ "\n");
      }
      catch (IllegalArgumentException iae) {
        sendMessage("Bad input: " + iae.getMessage() + "\n");
      }
      catch (IllegalStateException ise) {
        sendMessage("System error: " + ise.getMessage()+ "\n");
      }
    }
  }

}