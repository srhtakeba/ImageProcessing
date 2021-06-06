package model.Pixel;

import java.util.Objects;

public abstract class ChannelImpl implements Channel{
  private double value;

  public ChannelImpl(double value) {
    this.setValue(value);
  }

  @Override
  public double getValue() {
    return this.value;
  }

  @Override
  public void setValue(double n) {
    if(n<0) {
      this.value = 0;
      return;
    }
    if(n>255) {
      this.value = 255;
      return;
    }
    else {
      this.value = n;
    }
  }

  @Override
  public abstract boolean equals(Object o);

  @Override
  public abstract int hashCode();

  @Override
  public abstract String toString();
}
