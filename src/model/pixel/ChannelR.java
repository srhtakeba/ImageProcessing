package model.pixel;

import java.util.Objects;

/**
 * Class to represent a red channel for a pixel.
 */
public class ChannelR extends ChannelImpl {

  /**
   * Constructs a {@code ChannelR} object.
   *
   * @param value amount of Red(R) pigment.
   */
  public ChannelR(double value) {
    super(value);
  }

  /**
   * Checks the given object with this {@code ChannelR} for equality.
   *
   * @param o the object to be compared to
   * @return does this {@code ChannelR} equal the given object?
   */
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

  /**
   * Produces the hash code for this {@code ChannelR} object.
   *
   * @return the hash code for this object.
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.getValue(), 1);
  }
}
