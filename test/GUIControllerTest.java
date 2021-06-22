import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import controller.Features;
import controller.GUIController;
import model.InstagramLayerModel;
import model.InstagramLayerModelImpl;
import org.junit.Before;
import org.junit.Test;

public class GUIControllerTest {

  private MockGUIController controller;
  private InstagramLayerModel model;

  @Before
  public void setup() {
    model = new InstagramLayerModelImpl();
    controller = new MockGUIController(model);
  }

  @Test
  public void testSetCurrentErrorMessage() {
    controller.setCurrent("Non-existent");
    assertEquals("The layer with the provided name does not exist.",
        controller.getViewOutput());
  }

  @Test
  public void testSetCurrent() {
    model.addLayer("first");
    controller.setCurrent("first");
    assertEquals("first", model.currentLayer());
  }

  @Test
  public void testNonExistentImportScriptMessage() {
    model.addLayer("first");
    model.setCurrentLayer("first");
    controller.importScript("nonononononononono");
    assertEquals("The given file was not found.",
        controller.getViewOutput());
  }

  @Test
  public void testNonExistentImportImageMessage() {
    model.addLayer("first");
    model.setCurrentLayer("first");
    controller.importImage("nonononononononono");
    assertEquals("Reading from the file failed.",
        controller.getViewOutput());
  }

  @Test
  public void testBlurNoCurrentMessage() {
    controller.blur();
    assertEquals("Select a layer to filter", controller.getViewOutput());
  }

  @Test
  public void testSharpenNoCurrentMessage() {
    controller.sharpen();
    assertEquals("Select a layer to filter", controller.getViewOutput());
  }

  @Test
  public void testGreyscaleNoCurrentMessage() {
    controller.greyscale();
    assertEquals("Select a layer to transform", controller.getViewOutput());
  }

  @Test
  public void testSepiaNoCurrentMessage() {
    controller.sepia();
    assertEquals("Select a layer to transform", controller.getViewOutput());
  }

  @Test
  public void testVisibleNonExistentLayerMessage() {
    controller.makeVisible("non-existent");
    assertEquals("The layer with the provided name does not exist.", controller.getViewOutput());
  }

  @Test
  public void testInvisibleNonExistentLayerMessage() {
    controller.makeInvisible("non-existent");
    assertEquals("The layer with the provided name does not exist.", controller.getViewOutput());
  }

  @Test
  public void testAddLayerMessage() {
    model.addLayer("first");
    controller.addLayer("first");
    assertEquals("The layer with the provided name already exists.",
        controller.getViewOutput());
  }

  @Test
  public void testAddLayerReturnTypeTrue() {
    assertTrue(controller.addLayer("first"));
    assertEquals("", controller.getViewOutput());
  }

  @Test
  public void testRemoveLayerMessage() {
    model.addLayer("second");
    controller.removeLayer("first");
    assertEquals("The layer with the provided name does not exist.",
        controller.getViewOutput());
  }

  @Test
  public void testRemoveLayerReturnTypeTrue() {
    model.addLayer("first");
    assertTrue(controller.removeLayer("first"));
    assertEquals("", controller.getViewOutput());
  }

}
