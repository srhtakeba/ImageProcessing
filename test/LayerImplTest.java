import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import model.image.ImageImpl;
import model.image.InstaImage;
import model.layer.Layer;
import model.layer.LayerImpl;
import org.junit.Before;
import org.junit.Test;

/**
 * Class to test {@code LayerImpl} functionality, testing if a layer state becomes visible and
 * invisible.
 */
public class LayerImplTest {

  private InstaImage image;
  private Layer layer;

  @Before
  public void setup() {
    image = new ImageImpl();
    layer = new LayerImpl();
  }

  @Test
  public void testGetImageDefault() {
    assertNull(layer.getImage());
  }

  @Test
  public void testGetImageAfterSetImage() {
    InstaImage checkerBoard = image.makeCheckerBoard(3);
    assertNull(layer.getImage());
    layer.setImage(checkerBoard);
    assertEquals(checkerBoard.toString(), layer.getImage().toString());
  }

  @Test
  public void testGetVisibilityDefault() {
    assertTrue(layer.getVisibility());
  }

  @Test
  public void testGetVisibilityAfterMakeInvisible() {
    InstaImage checkerBoard = image.makeCheckerBoard(3);
    layer.setImage(checkerBoard);
    assertTrue(layer.getVisibility());
    layer.makeInvisible();
    assertFalse(layer.getVisibility());
  }

  @Test
  public void testGetVisibilityAfterMakeInvisibleThenMakeVisible() {
    InstaImage checkerBoard = image.makeCheckerBoard(3);
    layer.setImage(checkerBoard);
    assertTrue(layer.getVisibility());
    layer.makeInvisible();
    assertFalse(layer.getVisibility());
    layer.makeVisible();
    assertTrue(layer.getVisibility());
  }

}