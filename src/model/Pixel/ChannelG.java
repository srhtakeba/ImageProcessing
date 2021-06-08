package model.Pixel;

import java.util.Objects;

/**
 * Class to represent a green channel for a pixel.
 */
public class ChannelG extends ChannelImpl{

  /**
   * Constructs a {@CODE ChannelG} object.
   *
   * @param value amount of Green(G) pigment.
   */
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
  public int hashCode() {
    return Objects.hash(this.getValue(), 2);
  }

  @Override
  public String toString() {
    return "G: " + this.getValue();
  }
}
