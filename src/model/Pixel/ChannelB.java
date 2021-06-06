package model.Pixel;

import java.util.Objects;

/**
 * Class to represent a blue channel for a pixel.
 */
public class ChannelB extends ChannelImpl{
  public ChannelB(double value) {
    super(value);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if(!(o instanceof ChannelB)) {
      return false;
    }
    ChannelB that = (ChannelB)o;
    return Math.abs(that.getValue() - this.getValue()) < 0.001;
  }

  @Override
  // use 3 for blue code
  public int hashCode() {
    return Objects.hash(this.getValue(), 3);
  }

  @Override
  public String toString() {
    return "B: " + this.getValue();
  }
}
