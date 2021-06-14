package controller.command;

import model.InstagramLayerModel;

/**
 * Class to send a command of exporting the current a layer to a {code InstagramLayerModel}.
 */
public class Export implements InstagramLayerCommand {

  String str;

  /**
   * Constructs {@code Export} object.
   *
   * @param str name for the layer which is being exported
   */
  public Export(String str) {
    this.str = str;
  }

  @Override
  public void go(InstagramLayerModel model) {
    model.exportImage(str);
  }

}