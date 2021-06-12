package model.pixel;

/**
 * Interface to represent a pixel with three-color channels.
 */
public interface Pixel {

  /**
   * Get the R channel for this pixel. Not the actual channel itself, but a copy of it.
   *
   * @return the R channel with the value.
   */
  Channel getR();

  /**
   * Get the G channel for this pixel. Not the actual channel itself, but a copy of it.
   *
   * @return the G channel with the value.
   */
  Channel getG();

  /**
   * Get the B channel for this pixel. Not the actual channel itself, but a copy of it.
   *
   * @return the B channel with the value.
   */
  Channel getB();

  /**
   * Set the R channel value for this pixel.
   *
   * @param n the new r value for this pixel.
   */
  void setR(double n);

  /**
   * Set the G channel value for this pixel.
   *
   * @param n the new g value for this pixel.
   */
  void setG(double n);

  /**
   * Set the B channel value for this pixel.
   *
   * @param n the new b value for this pixel.
   */
  void setB(double n);

  /**
   * Checks the given object with this {@code Pixel} for equality.
   *
   * @param o the object to be compared to
   * @return does this {@code Pixel} equal the given object?
   */
  @Override
  boolean equals(Object o);

  /**
   * Produces the hash code for this {@code Pixel} object.
   *
   * @return the hash code for this object.
   */
  @Override
  int hashCode();

  /**
   * Produces a {@code String} representation for this {@code Pixel}.
   *
   * @return the {@code String} representation
   */
  @Override
  String toString();
}
