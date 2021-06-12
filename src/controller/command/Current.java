package controller.command;

import model.InstagramLayerModel;

public class Current implements InstagramLayerCommand {
//
  String str;

  public Current(String str) {
    this.str = str;
  }

  @Override
  public void go(InstagramLayerModel model) {
    model.setCurrentLayer(str);
  }

}