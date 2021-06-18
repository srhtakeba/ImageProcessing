package controller.command;

import model.InstagramLayerModel;

/**
 * Class to send a command of adding a layer to a {code InstagramLayerModel}.
 */
public class CreateLayer implements InstagramLayerCommand {

  private final String str;

  /**
   * Constructs {code CreateLayer} object.
   *
   * @param str title for the new layer
   */
  public CreateLayer(String str) {
    this.str = str;
  }

  @Override
  public void dispatchCommand(InstagramLayerModel model) {
    model.addLayer(str);
  }

}
