package controller.command;

import model.InstagramLayerModel;

/**
 * Class to send a command of reading an image to a {@code InstagramLayerModel}.
 */
public class Read implements InstagramLayerCommand {

  String str;

  /**
   * Constructs {@code Read} object.
   *
   * @param str name of the filepath
   */
  public Read(String str) {
    this.str = str;
  }

  @Override
  public void go(InstagramLayerModel model) {
    model.read(str);
  }

}