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
  public int getValue();

  /**
   * Set a new amount of the pigment.
   */
  public void setValue(double n);

  @Override
  public boolean equals(Object o);

  @Override
  public int hashCode();

  @Override
  public String toString();
}
