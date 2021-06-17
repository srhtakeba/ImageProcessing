package model.Layer;

import model.image.ImageImpl;
import model.image.InstaImage;

/**
 * Class to represent layer which consist of {@code InstaImage}.
 */
public class LayerImpl implements Layer{
  private InstaImage image;
  private boolean isVisible;

//  public LayerImpl(InstaImage image, boolean isVisible) {
//    this.image = image;
//    this.isVisible = isVisible;
//  }
//
//
//  public LayerImpl(InstaImage image) {
//    this.image = image;
//    this.isVisible = true;
//  }

  /**
   * Constructs a {@code LayerImpl} object.
   */
  public LayerImpl() {
    this.image = null;
    this.isVisible = true;
  }

  @Override
  public InstaImage getImage() {
    if(this.image == null) {
      return null;
    }
    return new ImageImpl(this.image);
  }

  @Override
  public boolean getVisibility() {
    return this.isVisible;
  }

  @Override
  public void makeVisible() {
    if(!isVisible) {
      isVisible = true;
    }
  }

  @Override
  public void makeInvisible() {
    if(isVisible) {
      isVisible = false;
    }
  }

  @Override
  public void setImage(InstaImage image) {
    this.image = image;
  }
}
