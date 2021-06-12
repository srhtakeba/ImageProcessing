package controller.command;

import model.InstagramLayerModel;

public class CreateLayer implements InstagramLayerCommand {
//
  String str;

  public CreateLayer(String str) {
    this.str = str;
  }

  @Override
  public void go(InstagramLayerModel model) {
    model.addLayer(str);
  }

}
