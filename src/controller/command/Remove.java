package controller.command;

import model.InstagramLayerModel;

/**
 * Class to send a command to remove a layer in the {@code InstagramLayerModel}.
 */
public class Remove implements InstagramLayerCommand {

  String str;

  /**
   * Constructs {@code Remove} object.
   *
   * @param str name of the layer to be removed
   */
  public Remove(String str) {
    this.str = str;
  }

  @Override
  public void go(InstagramLayerModel model) {
    model.removeLayer(str);
  }

}