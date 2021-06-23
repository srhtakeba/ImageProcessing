package controller.command;

/**
 * Factory class to create classes which can send commands.
 */
public class InstagramLayerCommandFactory {

  /**
   * Factory method to create classes which can send commands.
   *
   * @param cmd  the name of the command
   * @param next specifier of the command
   * @return different classes which can send commands
   */
  public static InstagramLayerCommand create(String cmd, String next) {
    switch (cmd.toLowerCase()) {
      case "new":
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
      case "save":
        return new Save(next);
      case "visible":
        return new Visible(next);
      case "invisible":
        return new Invisible(next);
      case "mosaic":
        return new Mosaic(next);
      default:
        return null;
    }
  }
}
