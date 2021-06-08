package model.Pixel;

import java.util.Objects;

/**
 * Represents a pixel in an image. Each pixel has 3 channels to represent the three base colors
 * it is comprised of. These channels represent red, green, and blue. Each channel holds a value
 * from 0-255, to represent how much of that color is included in this pixel.
 */
public class PixelImpl implements Pixel{
  private final Channel r;
  private final Channel g;
  private final Channel b;

  /**
   * Conctructs a {@CODE PixelImpl} object, and creates {@CODE ChannelR},
   * {@CODE ChannelG}, {@CODE ChannelB}.
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

  @Override
  public Channel getR() {
    return new ChannelR(this.r.getValue());
  }

  @Override
  public Channel getG() {
    return new ChannelG(this.g.getValue());
  }

  @Override
  public Channel getB() {
    return new ChannelB(this.b.getValue());
  }

  @Override
  public void setR(double n) {
    this.r.setValue(n);
  }

  @Override
  public void setG(double n) {
    this.g.setValue(n);
  }

  @Override
  public void setB(double n) {
    this.b.setValue(n);
  }

  /**
   * Check for pixel equality based on the rgb values.
   * @param o the object to be checked for equality.
   * @return does the given object equal this pixel?
   */
  @Override
  public boolean equals(Object o) {
    if(this == o) {
      return true;
    }
    if(!(o instanceof PixelImpl)) {
      return false;
    }
    PixelImpl that = (PixelImpl)o;
    return this.r.equals(that.r) && this.g.equals(that.g) && this.b.equals(that.b);
  }

  /**
   * Returns a hash code for this pixel.
   * @return the hash code for this pixel calculated from rgb values.
   */
  @Override
  public int hashCode() {
    return Objects.hash(r.getValue(), g.getValue(), b.getValue());
  }

  /**
   * Produce a string representation of this pixel.
   * @return a string containing this pixels r,g, and b values.
   */
  @Override
  public String toString() {
    return this.r + " " + this.g + " " + this.b;
  }

}
