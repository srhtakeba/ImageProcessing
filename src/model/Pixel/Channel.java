package model.Pixel;

/**
 * Interface to represent an 8-bit channel that holds a value for an rgb color.
 */
public interface Channel {
  public double getValue();

  public void setValue(double n);

  @Override
  public boolean equals(Object o);

  @Override
  public int hashCode();

  @Override
  public String toString();
}
