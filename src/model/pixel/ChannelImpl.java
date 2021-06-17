package model.pixel;


/**
 * Abstract class for three colors, red, green, and blue.
 */
public abstract class ChannelImpl implements Channel {

  private int value;

  /**
   * Constructs {@code ChannelImpl} object.
   *
   * @param value amount of pigment.
   */
  public ChannelImpl(double value) {
    this.setValue(value);
  }

  /**
   * Constructs a copy of the given {@code ChannelImpl} object.
   *
   * @param c the given {@code ChannelImpl} object.
   */
  public ChannelImpl(Channel c) {
    this(c.getValue());
  }

  @Override
  public int getValue() {
    return this.value;
  }

  @Override
  public void setValue(double n) {
    if (n < 0) {
      this.value = 0;
    } else if (n > 255) {
      this.value = 255;
    } else {
      // assign this value to the rounded int value of the given double
      this.value = (int) (n + 0.5);
    }
  }

  /**
   * Checks the given object with this {@code ChannelImpl} for equality.
   *
   * @param o the object to be compared to
   * @return does this {@code ChannelImpl} equal the given object?
   */
  @Override
  public abstract boolean equals(Object o);

  /**
   * Produces the hash code for this {@code ChannelImpl} object.
   *
   * @return the hash code for this object.
   */
  @Override
  public abstract int hashCode();

  /**
   * Produces a {@code String} representation for this {@code ChannelImpl}.
   *
   * @return the {@code String} representation
   */
  @Override
  public String toString() {
    return "" + this.getValue();
  }
}
