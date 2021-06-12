package controller;

import controller.command.CreateLayer;
import controller.command.Current;
import controller.command.Export;
import controller.command.Filter;
import controller.command.InstagramLayerCommand;
import controller.command.InstagramLayerCommandFactory;
import controller.command.Read;
import controller.command.Remove;
import controller.command.Transform;
import java.util.InputMismatchException;
import java.util.Scanner;
import model.InstagramLayerModel;
import model.InstagramLayerModelImpl;

public class Controller implements IController {

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
//      try {
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
          default:
            break;
        }
        if (cmd != null) {
          cmd.go(model);
        }
//      } catch (InputMismatchException ime) {
//        System.out.println("Bad length to " + curr);
//      }
    }
  }

}