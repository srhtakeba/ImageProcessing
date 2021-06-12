package controller.command;

import model.InstagramLayerModel;

public class Export implements InstagramLayerCommand {

  String str;

  public Export(String str) {
    this.str = str;
  }

  @Override
  public void go(InstagramLayerModel model) {
    model.exportImage(str);
  }

}