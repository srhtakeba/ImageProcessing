package model.Layer;

import model.image.InstaImage;

public interface Layer {

  InstaImage getImage();

  boolean getVisibility();

  void makeVisible();

  void makeInvisible();

  void setImage(InstaImage image);
}
