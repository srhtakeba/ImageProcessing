package controller.command;

import model.InstagramLayerModel;

public class Read implements InstagramLayerCommand {

  String str;

  public Read(String str) {
    this.str = str;
  }

  @Override
  public void go(InstagramLayerModel model) {
    model.read(str);
  }

}