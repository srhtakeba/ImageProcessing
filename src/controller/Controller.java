package controller;

import controller.command.CreateLayer;
import controller.command.Current;
import controller.command.Export;
import controller.command.Filter;
import controller.command.InstagramLayerCommand;
import controller.command.Read;
import controller.command.Remove;
import controller.command.Transform;
import java.util.InputMismatchException;
import java.util.Scanner;
import model.InstagramLayerModel;
import model.InstagramLayerModelImpl;
import model.InstagramModel;
import model.InstagramModelImpl;

public class Controller implements IController {
  //
  Readable in;
  InstagramLayerModel model;

  public Controller(Readable in) {
    this.in = in;
  }

  @Override
  public void go() {
    Scanner scan = new Scanner(this.in);
    this.model = new InstagramLayerModelImpl();
    InstagramLayerCommand cmd = null;

    while (scan.hasNext()) {
      String curr = scan.next();
      try {
        switch (curr) {
          case "new":
            cmd = new CreateLayer(scan.next());
            break;
          case "remove":
            cmd = new Remove(scan.next());
            break;
          case "read":
            cmd = new Read(scan.next());
            break;
          case "current":
            cmd = new Current(scan.next());
            break;
          case "transform":
            cmd = new Transform(scan.next());
            break;
          case "filter":
            cmd = new Filter(scan.next());
            break;
          case "export":
            cmd = new Export(scan.next());
            break;
          default:
            break;
        }
        if (cmd != null) {
          cmd.go(model);
        }
      } catch (InputMismatchException ime) {
        System.out.println("Bad length to " + curr);
      }
    }
  }

}