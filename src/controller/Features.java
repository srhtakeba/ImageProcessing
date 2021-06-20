package controller;

public interface Features {
  void setCurrent(String layerName);
  void saveProject(String dirName);
  void openProject(String dirpath);
  void importScript(String filepath);
  void importImage(String filepath);
  void exportImage(String filepath);
  void blur();
  void sharpen();
  void greyscale();
  void sepia();
  void makeVisible(String layerName);
  void makeInvisible(String layerName);
  boolean addLayer(String layerName);
  boolean removeLayer(String layerName);
}
