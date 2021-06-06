package model.Pixel;

import java.util.Objects;

/**
 * Class to represent a green channel for a pixel.
 */
public class ChannelG extends ChannelImpl{
  public ChannelG(double value) {
    super(value);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if(!(o instanceof ChannelG)) {
      return false;
    }
    ChannelG that = (ChannelG)o;
    return that.getValue() == this.getValue();
  }

  @Override
  // use 2 for green code
  public int hashCode() {
    return Objects.hash(this.getValue(), 2);
  }

  @Override
  public String toString() {
    return "G: " + this.getValue();
  }
}
