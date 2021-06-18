package model.layer;

import model.image.InstaImage;

/**
 * Interface to represent visible or invisible layer image.
 */
public interface Layer {

  /**
   * Returns a individual layer image.
   *
   * @return InstaImage
   */
  InstaImage getImage();

  /**
   * Returns the visibility of a layer image.
   *
   * @return true if a layer is visible, otherwise return false
   */
  boolean getVisibility();

  /**
   * Layer's visibility changes to be visible.
   */
  void makeVisible();

  /**
   * Layer's visibility changes to be invisible.
   */
  void makeInvisible();

  /**
   * Places a InstaImage on a layer.
   */
  void setImage(InstaImage image);

  @Override
  boolean equals(Object o);

  @Override
  int hashCode();

  @Override
  String toString();

}
