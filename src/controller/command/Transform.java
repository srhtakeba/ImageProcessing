package controller.command;

import model.InstagramLayerModel;

public class Transform implements InstagramLayerCommand {
  //
  String str;

  public Transform(String str) {
    this.str = str;
  }

  @Override
  public void go(InstagramLayerModel model) {
    model.transform(str);
  }

}