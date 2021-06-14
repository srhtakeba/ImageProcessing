package controller.command;

import model.InstagramLayerModel;

/**
 * Class to send a command of transforming the current a layer of the {code InstagramLayerModel}.
 */
public class Transform implements InstagramLayerCommand {

  String str;

  /**
   * Constructs {@code Transform} object.
   *
   * @param str name of the Transform operation
   */
  public Transform(String str) {
    this.str = str;
  }

  @Override
  public void go(InstagramLayerModel model) {
    model.transform(str);
  }

}