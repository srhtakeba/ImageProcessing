package controller.command;

import model.InstagramLayerModel;

/**
 * Class to send a command to make the current layer of {@code InstagramLayerModel} visible.
 */
public class Visible implements InstagramLayerCommand {

  private final String str;

  /**
   * Constructs {@code Filter} object.
   *
   * @param str name of the filter operation
   */
  public Visible(String str) {
    this.str = str;
  }

  @Override
  public void dispatchCommand(InstagramLayerModel model) {
    model.makeLayerVisible(str);
  }

}
