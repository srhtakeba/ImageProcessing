import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

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
    assertTrue(layerModel.getAllLayer().containsKey("first"));
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
    assertTrue(layerModel.getAllLayer().get("first").getVisibility());
    assertNull(layerModel.getAllLayer().get("second"));
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
    assertTrue(layerModel.getAllLayer().get("first").getVisibility());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetCurrentLayerDoesNotExist() {
    layerModel.setCurrentLayer("first");
  }

  @Test
  public void testMakeVisible() {
    layerModel.addLayer("first");
    layerModel.makeLayerVisible("first");
    assertTrue(layerModel.getAllLayer().get("first").getVisibility());
    layerModel.makeLayerInvisible("first");
    assertFalse(layerModel.getAllLayer().get("first").getVisibility());
  }

  @Test
  public void testMakeInVisible() {
    layerModel.addLayer("second");
    layerModel.makeLayerInvisible("second");
    assertFalse(layerModel.getAllLayer().get("second").getVisibility());
    layerModel.makeLayerVisible("second");
    assertTrue(layerModel.getAllLayer().get("second").getVisibility());
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
      imported = ImageIO.read(new File("res/images/3x3green.png"));
    } catch (IOException ioe) {
      throw new IllegalStateException("Reading from the file failed.");
    }
    layerModel.read(imported);
    assertEquals("Width: 3\n"
            + "Height: 3\n"
            + "0,0: R: 12 G: 255 B: 0\n"
            + "0,1: R: 12 G: 255 B: 0\n"
            + "0,2: R: 12 G: 255 B: 0\n"
            + "1,0: R: 12 G: 255 B: 0\n"
            + "1,1: R: 12 G: 255 B: 0\n"
            + "1,2: R: 12 G: 255 B: 0\n"
            + "2,0: R: 12 G: 255 B: 0\n"
            + "2,1: R: 12 G: 255 B: 0\n"
            + "2,2: R: 12 G: 255 B: 0\n",
        layerModel.getAllLayer().get("first").toString());
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
    assertEquals(
        "new bottom\ncurrent bottom\nnew invisibleButExistent\ncurrent invisibleButExistent"
            + "\nnew middle\ncurrent middle\n", layerModel.getMainTextString("test"));
  }

  @Test
  public void testTransform() {
    layerModel.addLayer("first");
    layerModel.setCurrentLayer("first");

    BufferedImage imported;
    try {
      imported = ImageIO.read(new File("res/images/3x3green.png"));
    } catch (IOException ioe) {
      throw new IllegalStateException("Reading from the file failed.");
    }
    layerModel.read(imported);
    assertEquals("Width: 3\n"
            + "Height: 3\n"
            + "0,0: R: 12 G: 255 B: 0\n"
            + "0,1: R: 12 G: 255 B: 0\n"
            + "0,2: R: 12 G: 255 B: 0\n"
            + "1,0: R: 12 G: 255 B: 0\n"
            + "1,1: R: 12 G: 255 B: 0\n"
            + "1,2: R: 12 G: 255 B: 0\n"
            + "2,0: R: 12 G: 255 B: 0\n"
            + "2,1: R: 12 G: 255 B: 0\n"
            + "2,2: R: 12 G: 255 B: 0\n",
        layerModel.getAllLayer().get("first").toString());

    layerModel.transform("greyscale");

    assertEquals("Width: 3\n"
            + "Height: 3\n"
            + "0,0: R: 185 G: 185 B: 185\n"
            + "0,1: R: 185 G: 185 B: 185\n"
            + "0,2: R: 185 G: 185 B: 185\n"
            + "1,0: R: 185 G: 185 B: 185\n"
            + "1,1: R: 185 G: 185 B: 185\n"
            + "1,2: R: 185 G: 185 B: 185\n"
            + "2,0: R: 185 G: 185 B: 185\n"
            + "2,1: R: 185 G: 185 B: 185\n"
            + "2,2: R: 185 G: 185 B: 185\n",
        layerModel.getAllLayer().get("first").toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidTransformName() {
    layerModel.addLayer("first");
    layerModel.setCurrentLayer("first");

    BufferedImage imported;
    try {
      imported = ImageIO.read(new File("res/images/3x3green.png"));
    } catch (IOException ioe) {
      throw new IllegalStateException("Reading from the file failed.");
    }
    layerModel.read(imported);
    assertEquals("Width: 3\n"
            + "Height: 3\n"
            + "0,0: R: 12 G: 255 B: 0\n"
            + "0,1: R: 12 G: 255 B: 0\n"
            + "0,2: R: 12 G: 255 B: 0\n"
            + "1,0: R: 12 G: 255 B: 0\n"
            + "1,1: R: 12 G: 255 B: 0\n"
            + "1,2: R: 12 G: 255 B: 0\n"
            + "2,0: R: 12 G: 255 B: 0\n"
            + "2,1: R: 12 G: 255 B: 0\n"
            + "2,2: R: 12 G: 255 B: 0\n",
        layerModel.getAllLayer().get("first").toString());

    layerModel.transform("pink");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTransformNoCurrent() {
    layerModel.addLayer("first");
    layerModel.transform("sepia");
  }

  @Test
  public void testFilter() {
    layerModel.addLayer("first");
    layerModel.setCurrentLayer("first");

    BufferedImage imported;
    try {
      imported = ImageIO.read(new File("res/images/3x3green.png"));
    } catch (IOException ioe) {
      throw new IllegalStateException("Reading from the file failed.");
    }
    layerModel.read(imported);
    assertEquals("Width: 3\n"
            + "Height: 3\n"
            + "0,0: R: 12 G: 255 B: 0\n"
            + "0,1: R: 12 G: 255 B: 0\n"
            + "0,2: R: 12 G: 255 B: 0\n"
            + "1,0: R: 12 G: 255 B: 0\n"
            + "1,1: R: 12 G: 255 B: 0\n"
            + "1,2: R: 12 G: 255 B: 0\n"
            + "2,0: R: 12 G: 255 B: 0\n"
            + "2,1: R: 12 G: 255 B: 0\n"
            + "2,2: R: 12 G: 255 B: 0\n",
        layerModel.getAllLayer().get("first").toString());

    layerModel.filter("blur");

    assertEquals("Width: 3\n"
            + "Height: 3\n"
            + "0,0: R: 7 G: 143 B: 0\n"
            + "0,1: R: 9 G: 191 B: 0\n"
            + "0,2: R: 7 G: 143 B: 0\n"
            + "1,0: R: 9 G: 191 B: 0\n"
            + "1,1: R: 12 G: 255 B: 0\n"
            + "1,2: R: 9 G: 191 B: 0\n"
            + "2,0: R: 7 G: 143 B: 0\n"
            + "2,1: R: 9 G: 191 B: 0\n"
            + "2,2: R: 7 G: 143 B: 0\n",
        layerModel.getAllLayer().get("first").toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidFilterName() {
    layerModel.addLayer("first");
    layerModel.setCurrentLayer("first");

    BufferedImage imported;
    try {
      imported = ImageIO.read(new File("res/images/3x3green.png"));
    } catch (IOException ioe) {
      throw new IllegalStateException("Reading from the file failed.");
    }
    layerModel.read(imported);

    layerModel.filter("mosaic");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testFilterNoCurrent() {
    layerModel.addLayer("first");
    layerModel.filter("blur");
  }

  @Test
  public void testExportImage() {
    layerModel.addLayer("first");
    layerModel.setCurrentLayer("first");

    BufferedImage imported;
    try {
      imported = ImageIO.read(new File("res/images/3x3green.png"));
    } catch (IOException ioe) {
      throw new IllegalStateException("Reading from the file failed.");
    }
    layerModel.read(imported);
    assertEquals("Width: 3\n"
            + "Height: 3\n"
            + "0,0: R: 12 G: 255 B: 0\n"
            + "0,1: R: 12 G: 255 B: 0\n"
            + "0,2: R: 12 G: 255 B: 0\n"
            + "1,0: R: 12 G: 255 B: 0\n"
            + "1,1: R: 12 G: 255 B: 0\n"
            + "1,2: R: 12 G: 255 B: 0\n"
            + "2,0: R: 12 G: 255 B: 0\n"
            + "2,1: R: 12 G: 255 B: 0\n"
            + "2,2: R: 12 G: 255 B: 0\n",
        layerModel.getAllLayer().get("first").toString());
    layerModel.filter("blur");

    BufferedImage exported = layerModel.exportImage();

    InstagramLayerModel layerModelVer2 = new InstagramLayerModelImpl();
    layerModelVer2.addLayer("first");
    layerModelVer2.setCurrentLayer("first");
    layerModelVer2.read(exported);

    assertEquals("Width: 3\n"
            + "Height: 3\n"
            + "0,0: R: 7 G: 143 B: 0\n"
            + "0,1: R: 9 G: 191 B: 0\n"
            + "0,2: R: 7 G: 143 B: 0\n"
            + "1,0: R: 9 G: 191 B: 0\n"
            + "1,1: R: 12 G: 255 B: 0\n"
            + "1,2: R: 9 G: 191 B: 0\n"
            + "2,0: R: 7 G: 143 B: 0\n"
            + "2,1: R: 9 G: 191 B: 0\n"
            + "2,2: R: 7 G: 143 B: 0\n",
        layerModelVer2.getAllLayer().get("first").toString());
  }

  @Test(expected = IllegalStateException.class)
  public void testNothingToExport() {
    layerModel.addLayer("first");
    layerModel.setCurrentLayer("first");
    layerModel.exportImage();
  }

  @Test
  public void testAlllayersSave() {
    layerModel.addLayer("first");
    layerModel.setCurrentLayer("first");

    BufferedImage imported;
    try {
      imported = ImageIO.read(new File("res/images/3x3green.png"));
    } catch (IOException ioe) {
      throw new IllegalStateException("Reading from the file failed.");
    }
    layerModel.read(imported);

    NavigableMap<String, BufferedImage> result = layerModel.allLayersSave("testSave");
    assertNull(result.get("first"));
  }

  @Test
  public void testGetAllLayer() {
    layerModel.addLayer("first");
    layerModel.setCurrentLayer("first");

    BufferedImage imported;
    try {
      imported = ImageIO.read(new File("res/images/3x3green.png"));
    } catch (IOException ioe) {
      throw new IllegalStateException("Reading from the file failed.");
    }
    layerModel.read(imported);
    NavigableMap<String, Layer> result = layerModel.getAllLayer();
    assertEquals("Width: 3\n"
        + "Height: 3\n"
        + "0,0: R: 12 G: 255 B: 0\n"
        + "0,1: R: 12 G: 255 B: 0\n"
        + "0,2: R: 12 G: 255 B: 0\n"
        + "1,0: R: 12 G: 255 B: 0\n"
        + "1,1: R: 12 G: 255 B: 0\n"
        + "1,2: R: 12 G: 255 B: 0\n"
        + "2,0: R: 12 G: 255 B: 0\n"
        + "2,1: R: 12 G: 255 B: 0\n"
        + "2,2: R: 12 G: 255 B: 0\n", result.get("first").toString());
  }

}