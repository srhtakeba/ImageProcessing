package model.Pixel;

import java.util.Objects;

/**
 * Class to represent a red channel for a pixel.
 */
public class ChannelR extends ChannelImpl {

  /**
   * Constructs a {@CODE ChannelR} object.
   *
   * @param value amount of Red(R) pigment.
   */
  public ChannelR(double value) {
    super(value);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ChannelR)) {
      return false;
    }
    ChannelR that = (ChannelR) o;
    return that.getValue() == this.getValue();
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.getValue(), 1);
  }

  @Override
  public String toString() {
    return "R: " + this.getValue();
  }
}
