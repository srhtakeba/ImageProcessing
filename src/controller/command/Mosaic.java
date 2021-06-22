package controller.command;

import model.InstagramLayerModel;

/**
 * Class to send a command of Mosaic-fying the current a layer of the {code InstagramLayerModel}.
 */
public class Mosaic implements InstagramLayerCommand {

  private final String str;

  /**
   * Constructs {@code Mosaic} object.
   *
   * @param str name of the Transform operation
   */
  public Mosaic(String str) {
    this.str = str;
  }

  @Override
  public void dispatchCommand(InstagramLayerModel model) {
    model.mosaic(Integer.valueOf(str));
  }

}
