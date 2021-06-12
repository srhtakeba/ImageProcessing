package model.pixel;

import java.util.Objects;

/**
 * Represents a pixel in an image. Each pixel has 3 channels to represent the three base colors it
 * is comprised of. These channels represent red, green, and blue. Each channel holds a value from
 * 0-255, to represent how much of that color is included in this pixel.
 */
public class PixelImpl implements Pixel {

  private final Channel r;
  private final Channel g;
  private final Channel b;

  /**
   * Conctructs a {@code PixelImpl} object, and creates {@code ChannelR}, {@code ChannelG}, {@code
   * ChannelB}.
   *
   * @param r amount of Red(R) pigment.
   * @param g amount of Green(G) pigment.
   * @param b amount of Blue(B) pigment.
   */
  public PixelImpl(double r, double g, double b) {
    this.r = new ChannelR(r);
    this.g = new ChannelG(g);
    this.b = new ChannelB(b);
  }

  // copy constructor
  public PixelImpl(Pixel source) {
    this(source.getR().getValue(), source.getG().getValue(), source.getB().getValue());
  }

  /**
   * Get the R channel for this pixel. Not the actual channel itself, but a copy of it.
   *
   * @return the R channel with the value.
   */
  @Override
  public Channel getR() {
    return new ChannelR(this.r.getValue());
  }

  /**
   * Get the G channel for this pixel. Not the actual channel itself, but a copy of it.
   *
   * @return the G channel with the value.
   */
  @Override
  public Channel getG() {
    return new ChannelG(this.g.getValue());
  }

  /**
   * Get the B channel for this pixel. Not the actual channel itself, but a copy of it.
   *
   * @return the B channel with the value.
   */
  @Override
  public Channel getB() {
    return new ChannelB(this.b.getValue());
  }

  /**
   * Set the R channel value for this pixel.
   *
   * @param n the new r value for this pixel.
   */
  @Override
  public void setR(double n) {
    this.r.setValue(n);
  }

  /**
   * Set the G channel value for this pixel.
   *
   * @param n the new g value for this pixel.
   */
  @Override
  public void setG(double n) {
    this.g.setValue(n);
  }

  /**
   * Set the B channel value for this pixel.
   *
   * @param n the new b value for this pixel.
   */
  @Override
  public void setB(double n) {
    this.b.setValue(n);
  }

  /**
   * Checks the given object with this {@code PixelImpl} for equality.
   *
   * @param o the object to be compared to
   * @return does this {@code PixelImpl} equal the given object?
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof PixelImpl)) {
      return false;
    }
    PixelImpl that = (PixelImpl) o;
    return this.r.equals(that.r) && this.g.equals(that.g) && this.b.equals(that.b);
  }

  /**
   * Produces the hash code for this {@code PixelImpl} object.
   *
   * @return the hash code for this object.
   */
  @Override
  public int hashCode() {
    return Objects.hash(r.getValue(), g.getValue(), b.getValue());
  }

  /**
   * Produces a {@code String} representation for this {@code PixelImpl}.
   *
   * @return the {@code String} representation
   */
  @Override
  public String toString() {
    return "R: " + this.r + " " + "G: " + this.g + " " + "B: " + this.b;
  }

}
