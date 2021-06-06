package model.Pixel;

import java.util.Objects;

/**
 * Class to represent a red channel for a pixel.
 */
public class ChannelR extends ChannelImpl{
  public ChannelR(double value) {
    super(value);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if(!(o instanceof ChannelR)) {
      return false;
    }
    ChannelR that = (ChannelR)o;
    return that.getValue() == this.getValue();
  }

  @Override
  // use 1 for red code
  public int hashCode() {
    return Objects.hash(this.getValue(), 1);
  }

  @Override
  public String toString() {
    return "R: " + this.getValue();
  }
}
