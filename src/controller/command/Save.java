package controller.command;

import model.InstagramLayerModel;

public class Save implements InstagramLayerCommand {

  String str;

  public Save(String str) {
    this.str = str;
  }

  @Override
  public void go(InstagramLayerModel model) {
    model.save(str);
  }

}
