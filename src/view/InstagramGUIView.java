package view;

import controller.Features;

public interface InstagramGUIView extends InstagramView {
  /**
   * Update the image displayed on this view.
   */
  void display();

  void addFeatures(Features feature);

  String receiveInput();

  void clearInput();

}
