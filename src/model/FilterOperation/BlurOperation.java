package model.FilterOperation;

public class BlurOperation extends FilterImpl{
  public BlurOperation() {
    super(new Double[][]{{0.0625, 0.125, 0.0625},
        {0.125, 0.25, 0.125},
        {0.0625, 0.125, 0.625}});
  }
}
