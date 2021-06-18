
import static org.junit.Assert.assertEquals;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.NavigableMap;
import java.util.TreeMap;
import javax.imageio.ImageIO;
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
    assertEquals(true, layerModel.getAllLayer().get("first").getVisibility());
    layerModel.makeLayerInvisible("first");
    assertEquals(false, layerModel.getAllLayer().get("first").getVisibility());
  }

  @Test
  public void testMakeInVisible() {
    layerModel.addLayer("second");
    layerModel.makeLayerInvisible("second");
    assertEquals(false, layerModel.getAllLayer().get("second").getVisibility());
    layerModel.makeLayerVisible("second");
    assertEquals(true, layerModel.getAllLayer().get("second").getVisibility());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMakeVisibleNoLayer() {
    layerModel.makeLayerVisible("first");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMakeInVisibleNoLayer() {
    layerModel.makeLayerInvisible("second");
  }

  @Test
  public void testRead() {
    layerModel.addLayer("first");
    layerModel.setCurrentLayer("first");
    BufferedImage imported;
    try {
      imported = ImageIO.read(new File("canyonShouldBeGreyScale.png"));
    } catch (IOException ioe) {
      throw new IllegalStateException("Reading from the file failed.");
    }
    layerModel.read(imported);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testReadNoCurrentLayer() {
    BufferedImage imported;
    try {
      imported = ImageIO.read(new File("canyonShouldBeGreyScale.png"));
    } catch (IOException ioe) {
      throw new IllegalStateException("Reading from the file failed.");
    }
    layerModel.read(imported);
  }

  @Test(expected = IllegalStateException.class)
  public void testTransformNoImage() {
    NavigableMap<String, Layer> expected = new TreeMap<>();
    expected.put("first", new LayerImpl());

    layerModel.addLayer("first");
    layerModel.addLayer("second");
    layerModel.setCurrentLayer("first");
    layerModel.transform("blur");
  }

  @Test(expected = IllegalStateException.class)
  public void testFilterNoImage() {
    NavigableMap<String, Layer> expected = new TreeMap<>();
    expected.put("first", new LayerImpl());

    layerModel.addLayer("first");
    layerModel.addLayer("second");
    layerModel.setCurrentLayer("first");
    layerModel.filter("greyscale");
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