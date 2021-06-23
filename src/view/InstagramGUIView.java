package view;

import controller.Features;

/**
 * Interface to hold GUI functionality which connects to {@code ROInstagramImpl}.
 */
public interface InstagramGUIView extends InstagramView {

  /**
   * Update the image displayed on this view.
   */
  void display();

  /**
   * Add interactive features to this view's elements.
   *
   * @param feature an {@code Features} object that holds the commands for action listeners.
   */
  void addFeatures(Features feature);
}
