package controller;

import model.InstagramModel;

public interface IController {

  /**
   * Dispatch operations depending on the
   * @param model
   */
  void go(InstagramModel model);

}
