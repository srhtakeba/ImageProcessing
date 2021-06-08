package model.Pixel;


/**
 * Abstract class for three colors, red, green, and blue.
 */
public abstract class ChannelImpl implements Channel {

  private int value;

  /**
   * Constrcuts (@CODE ChannelImpl} object.
   *
   * @param value amount of pigment.
   */
  public ChannelImpl(double value) {
    this.setValue(value);
  }

  // copy constructor
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
      return;
    }
    if (n > 255) {
      this.value = 255;
      return;
    } else {
      // assign this value to the rounded int value of the given double
      this.value = (int) (n + 0.5);
    }
  }

  @Override
  public abstract boolean equals(Object o);

  @Override
  public abstract int hashCode();

  @Override
  public abstract String toString();
}
