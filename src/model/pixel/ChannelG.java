package model.pixel;

import java.util.Objects;

/**
 * Class to represent a green channel for a pixel.
 */
public class ChannelG extends ChannelImpl {

  /**
   * Constructs a {@CODE ChannelG} object.
   *
   * @param value amount of Green(G) pigment.
   */
  public ChannelG(double value) {
    super(value);
  }

  /**
   * Checks the given object with this {@code ChannelG} for equality.
   *
   * @param o the object to be compared to
   * @return does this {@code ChannelG} equal the given object?
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ChannelG)) {
      return false;
    }
    ChannelG that = (ChannelG) o;
    return that.getValue() == this.getValue();
  }

  /**
   * Produces the hash code for this {@code ChannelG} object.
   *
   * @return the hash code for this object.
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.getValue(), 2);
  }

  /**
   * Produces a {@code String} representation for this {@code ChannelG}.
   *
   * @return the {@code String} representation
   */
  @Override
  public String toString() {
    return "G: " + this.getValue();
  }
}