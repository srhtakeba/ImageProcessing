import java.awt.image.BufferedImage;
import java.util.NavigableMap;
import model.InstagramLayerModel;
import model.image.InstaImage;

public class MockModel implements InstagramLayerModel {

  private final StringBuilder log;

  public MockModel() {
    log = new StringBuilder();
  }

  public String getLogContents() {
    return log.toString();
  }

  @Override
  public void addLayer(String layerName) {
    log.append("add: " + layerName);
    log.append("\n");
  }

  @Override
  public void removeLayer(String layerName) {
    log.append("remove: " + layerName);
    log.append("\n");
  }

  @Override
  public void makeLayerInvisible(String layerName) throws IllegalArgumentException {
    log.append("invisible: " + layerName);
    log.append("\n");
  }

  @Override
  public void makeLayerVisible(String layerName) throws IllegalArgumentException {
    log.append("visible: " + layerName);
    log.append("\n");
  }

  @Override
  public void setCurrentLayer(String layerName) {
    log.append("current: " + layerName);
    log.append("\n");
  }

  @Override
  public BufferedImage exportImage() {
    log.append("export");
    log.append("\n");
    return null;
  }

  @Override
  public void read(BufferedImage imported) throws IllegalStateException, IllegalArgumentException {
    log.append("read");
    log.append("\n");
  }

  @Override
  public String getMainTextString(String dirName) {
    log.append("get main text string");
    log.append("\n");
    return null;
  }

  @Override
  public NavigableMap<String, BufferedImage> allLayersSave(String dirName) {
    return null;
  }

  @Override
  public void filter(String operation) throws IllegalStateException {
    log.append("filter: " + operation);
    log.append("\n");
  }

  @Override
  public void transform(String operation) throws IllegalStateException {
    log.append("transform: " + operation);
    log.append("\n");
  }

  @Override
  public void readPPM(String filename) throws IllegalArgumentException {

  }

  @Override
  public void exportAsPPM(String title) throws IllegalStateException {

  }

  @Override
  public InstaImage exportAsInstaImage() throws IllegalStateException {
    return null;
  }

  @Override
  public void readInstaImage(InstaImage image) throws IllegalArgumentException {

  }

  @Override
  public void save() throws IllegalStateException {

  }

  @Override
  public void retrieve() throws IllegalStateException {

  }
}
