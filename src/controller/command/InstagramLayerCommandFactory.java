package controller.command;

public class InstagramLayerCommandFactory {

  public static InstagramLayerCommand create(String cmd, String next) {
    switch(cmd) {
      case "create":
        return new CreateLayer(next);
      case "remove":
        return new Remove(next);
      case "read":
        return new Read(next);
      case "export":
        return new Export(next);
      case "current":
        return new Current(next);
      case "transform":
        return new Transform(next);
      case "filter":
        return new Filter(next);
      default:
        return null;
    }
  }
}
