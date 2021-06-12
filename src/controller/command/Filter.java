package controller.command;

import model.InstagramLayerModel;

public class Filter implements InstagramLayerCommand {

  String str;

  public Filter(String str) {
    this.str = str;
  }

  @Override
  public void go(InstagramLayerModel model) {
    model.filter(str);
  }

}
