package controller.command;

import model.InstagramLayerModel;

public class Remove implements InstagramLayerCommand {

  String str;

  public Remove(String str) {
    this.str = str;
  }

  @Override
  public void go(InstagramLayerModel model) {
    model.removeLayer(str);
  }

}