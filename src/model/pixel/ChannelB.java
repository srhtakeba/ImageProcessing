package model.pixel;

import java.util.Objects;

/**
 * Class to represent a blue channel for a pixel.
 */
public class ChannelB extends ChannelImpl {

  /**
   * Constructs a {@code ChannelB} object.
   *
   * @param value amount of Blue(B) pigment.
   */
  public ChannelB(double value) {
    super(value);
  }

  /**
   * Checks the given object with this {@code ChannelB} for equality.
   *
   * @param o the object to be compared to
   * @return does this {@code ChannelB} equal the given object?
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ChannelB)) {
      return false;
    }
    ChannelB that = (ChannelB) o;
    return that.getValue() == this.getValue();
  }

  /**
   * Produces the hash code for this {@code ChannelB} object.
   *
   * @return the hash code for this object.
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.getValue(), 3);
  }
}
