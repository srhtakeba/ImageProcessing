package controller.command;

import model.InstagramLayerModel;

/**
 * Class to represent a command to declare the current layer to a {code InstagramLayerModel}.
 */
public interface InstagramLayerCommand {

  /**
   * send a command to a {code InstagramLayerModel}.
   *
   * @param model {code InstagramLayerModel} which holds image processing functions
   */
  void dispatchCommand(InstagramLayerModel model);

}
