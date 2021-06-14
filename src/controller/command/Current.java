package controller.command;

import model.InstagramLayerModel;

/**
 * Class to send a command of declaring the current a layer to a {code InstagramLayerModel}.
 */
public class Current implements InstagramLayerCommand {

  String str;

  /**
   * Constructs {code Current} object.
   *
   * @param str title of the current layer
   */
  public Current(String str) {
    this.str = str;
  }

  @Override
  public void go(InstagramLayerModel model) {
    model.setCurrentLayer(str);
  }

}