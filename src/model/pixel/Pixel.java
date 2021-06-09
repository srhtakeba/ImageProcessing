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
  public Channel getR();

  /**
   * Get the G channel for this pixel. Not the actual channel itself, but a copy of it.
   *
   * @return the G channel with the value.
   */
  public Channel getG();

  /**
   * Get the B channel for this pixel. Not the actual channel itself, but a copy of it.
   *
   * @return the B channel with the value.
   */
  public Channel getB();

  /**
   * Set the R channel value for this pixel.
   *
   * @param n the new r value for this pixel.
   */
  public void setR(double n);

  /**
   * Set the G channel value for this pixel.
   *
   * @param n the new g value for this pixel.
   */
  public void setG(double n);

  /**
   * Set the B channel value for this pixel.
   *
   * @param n the new b value for this pixel.
   */
  public void setB(double n);

  @Override
  public boolean equals(Object o);

  @Override
  public int hashCode();

  @Override
  public String toString();
}
