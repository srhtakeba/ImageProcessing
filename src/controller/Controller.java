package controller;

import model.InstagramModel;

public class Controller implements IController {

  Readable in;

  public Controller(Readable in){
   this.in = in;
  }

  @Override
  public void go(InstagramModel model) {

  }
}
