package model.layer;

import model.image.ImageImpl;
import model.image.InstaImage;

/**
 * Class to represent layer which consist of {@code InstaImage} and a visibility. A layer can exist
 * and be invisible/visible without holding an image. A layer without an image can be considered a
 * 'transparent' layer.
 */
public class LayerImpl implements Layer {

  private InstaImage image;
  private boolean isVisible;

  /**
   * Constructs a {@code LayerImpl} object.
   */
  public LayerImpl() {
    this.image = null;
    this.isVisible = true;
  }

  @Override
  public InstaImage getImage() {
    if (this.image == null) {
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
    if (!isVisible) {
      isVisible = true;
    }
  }

  @Override
  public void makeInvisible() {
    if (isVisible) {
      isVisible = false;
    }
  }

  @Override
  public void setImage(InstaImage image) {
    this.image = image;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof LayerImpl)) {
      return false;
    }
    LayerImpl that = (LayerImpl) o;
    return ((that.isVisible == this.isVisible)
        && (that.image.equals(this.image)));
  }

  @Override
  public int hashCode() {
    return LayerImpl.class.hashCode();
  }

  @Override
  public String toString() {
    return "" + this.image.toString();
  }

}
