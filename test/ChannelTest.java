import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import model.Pixel.Channel;
import model.Pixel.ChannelB;
import model.Pixel.ChannelG;
import model.Pixel.ChannelR;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for Channel: unit tests to ensure that Channel, Red, Blue, and Green's value can be
 * correctly changed and give its holding value.
 */
public class ChannelTest {

  private Channel r;
  private Channel g;
  private Channel b;

  @Before
  public void setup() {
    this.r = new ChannelR(50);
    this.g = new ChannelG(50);
    this.b = new ChannelB(50);
  }

  @Test
  public void testSetValue() {
    this.r.setValue(200);
    assertEquals(200, this.r.getValue(), 0.001);
  }

  @Test
  public void testSetValueNegative() {
    this.r.setValue(-5);
    assertEquals(0, this.r.getValue(), 0.001);
  }

  @Test
  public void testSetValueOvershoot() {
    this.r.setValue(300);
    assertEquals(255, this.r.getValue(), 0.001);
  }

  @Test
  public void testEqualsTrue() {
    assertTrue(this.r.equals(new ChannelR(50)));
  }

  @Test
  public void testEqualsFalseDiffValue() {
    assertFalse(this.r.equals(new ChannelR(51)));
  }

  @Test
  public void testEqualsFalseSameValueDiffColor() {
    assertFalse(this.r.equals(this.b));
  }

}
