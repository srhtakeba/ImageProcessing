package model.Pixel;

/**
 * Interface to represent a pixel with three-color channels.
 */
public interface Pixel {
  public Channel getR();

  public Channel getG();

  public Channel getB();

  public void setR(double n);

  public void setG(double n);

  public void setB(double n);

  @Override
  public boolean equals(Object o);

  @Override
  public int hashCode();

  @Override
  public String toString();
}
