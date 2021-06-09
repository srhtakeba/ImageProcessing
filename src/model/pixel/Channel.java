package model.pixel;

/**
 * Interface to represent an 8-bit channel that holds a value for an rgb color.
 */
public interface Channel {

  /**
   * Returns the amount of the pigment.
   *
   * @return amount of the pigment.
   */
  int getValue();

  /**
   * Set a new amount of the pigment.
   */
  void setValue(double n);

  /**
   * Checks the given object with this {@code Channel} for equality.
   *
   * @param o the object to be compared to
   * @return does this {@code Channel} equal the given object?
   */
  @Override
  boolean equals(Object o);

  /**
   * Produces the hash code for this {@code Channel} object.
   *
   * @return the hash code for this object.
   */
  @Override
  int hashCode();

  /**
   * Produces a {@code String} representation for this {@code Channel}.
   *
   * @return the {@code String} representation
   */
  @Override
  String toString();
}
