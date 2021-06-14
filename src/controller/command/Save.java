package controller.command;

import model.InstagramLayerModel;

/**
 * Class to send a command of saving the existing images of the {@code InstagramLayerModel}.
 */
public class Save implements InstagramLayerCommand {

  String str;

  /**
   * Constructs {@code Save} object.
   *
   * @param str name of the directory
   */
  public Save(String str) {
    this.str = str;
  }

  @Override
  public void go(InstagramLayerModel model) {
    model.save(str);
  }

}
