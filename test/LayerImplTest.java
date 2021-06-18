import static org.junit.Assert.*;

import model.image.ImageImpl;
import model.image.InstaImage;
import model.layer.Layer;
import model.layer.LayerImpl;
import org.junit.Before;
import org.junit.Test;

/**
 * Class to test {@code LayerImpl} functionality,
 * testing if a layer state becomes visible and invisible.
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
    assertEquals(null, layer.getImage());
  }

  @Test
  public void testGetImageAfterSetImage() {
    InstaImage checkerBoard = image.makeCheckerBoard(3);
    assertEquals(null, layer.getImage());
    layer.setImage(checkerBoard);
    assertEquals(checkerBoard.toString(), layer.getImage().toString());
  }

  @Test
  public void testGetVisibilityDefault() {
    assertEquals(true, layer.getVisibility());
  }

  @Test
  public void testGetVisibilityAfterMakeInvisible() {
    InstaImage checkerBoard = image.makeCheckerBoard(3);
    layer.setImage(checkerBoard);
    assertEquals(true, layer.getVisibility());
    layer.makeInvisible();
    assertEquals(false, layer.getVisibility());
  }

  @Test
  public void testGetVisibilityAfterMakeInvisibleThenMakeVisible() {
    InstaImage checkerBoard = image.makeCheckerBoard(3);
    layer.setImage(checkerBoard);
    assertEquals(true, layer.getVisibility());
    layer.makeInvisible();
    assertEquals(false, layer.getVisibility());
    layer.makeVisible();
    assertEquals(true, layer.getVisibility());
  }


}