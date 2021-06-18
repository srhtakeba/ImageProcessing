package controller.command;

import model.InstagramLayerModel;

/**
 * Class to send a command of filtering the current a layer of the {code InstagramLayerModel}.
 */
public class Filter implements InstagramLayerCommand {

  private final String str;

  /**
   * Constructs {@code Filter} object.
   *
   * @param str name of the filter operation
   */
  public Filter(String str) {
    this.str = str;
  }

  @Override
  public void dispatchCommand(InstagramLayerModel model) {
    model.filter(str);
  }

}
