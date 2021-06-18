
import static org.junit.Assert.assertEquals;

import java.util.NavigableMap;
import java.util.TreeMap;
import model.InstagramLayerModel;
import model.InstagramLayerModelImpl;
import model.layer.Layer;
import model.layer.LayerImpl;
import org.junit.Before;
import org.junit.Test;

/**
 * Class to test {@code InstagramLayerModelImpl} determine the correct functionality of operations
 * on layers.
 */
public class InstagramLayerModelImplTest {

  private InstagramLayerModel layerModel;

  @Before
  public void setup() {
    layerModel = new InstagramLayerModelImpl();
  }

  @Test
  public void testAddLayer() {
    NavigableMap<String, Layer> expected = new TreeMap<>();
    expected.put("first", new LayerImpl());
    layerModel.addLayer("first");
    assertEquals(expected.equals(layerModel), layerModel.equals(expected));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddLayerAlreadyExist() {
    layerModel.addLayer("first");
    layerModel.addLayer("first");
  }

  @Test
  public void testRemoveLayer() {
    NavigableMap<String, Layer> expected = new TreeMap<>();
    expected.put("first", new LayerImpl());
    layerModel.addLayer("first");
    layerModel.addLayer("second");
    layerModel.removeLayer("second");
    assertEquals(expected.toString(), layerModel.getAllLayer().toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveDoesNotExist() {
    layerModel.removeLayer("first");
  }

  @Test
  public void testSetCurrentLayer() {
    NavigableMap<String, Layer> expected = new TreeMap<>();
    expected.put("first", new LayerImpl());
    layerModel.addLayer("first");
    layerModel.setCurrentLayer("first");
    assertEquals(expected.toString(), layerModel.getAllLayer().toString());
    assertEquals("first", layerModel.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetCurrentLayerDoesNotExist() {
    layerModel.setCurrentLayer("first");
  }

  @Test
  public void testMakeVisible() {
    layerModel.addLayer("first");
    layerModel.makeLayerVisible("first");
  }

  @Test
  public void testMakeInVisible() {
    layerModel.addLayer("first");
    layerModel.makeLayerInvisible("first");
  }

  @Test
  public void testGetMainTextStringEmpty() {
    assertEquals("", layerModel.getMainTextString("test"));
  }

  @Test
  public void testGetMainTextString() {
    layerModel.addLayer("bottom");
    layerModel.setCurrentLayer("bottom");
    layerModel.addLayer("middle");
    layerModel.addLayer("invisibleButExistent");
    layerModel.makeLayerInvisible("invisibleButExistent");
    assertEquals("new bottom\ncurrent bottom\nnew invisibleButExistent\ncurrent invisibleButExistent"
  +"\nnew middle\ncurrent middle\n", layerModel.getMainTextString("test"));
  }



}