package controller;

public interface Features {
  void setCurrent(String layerName);
  void saveProject();
  void importScript();
  void importImage(String filepath);
  void exportImage(String filepath);
  void blur();
  void sharpen();
  void greyscale();
  void sepia();
  void makeVisible();
  void makeInvisible();
}
