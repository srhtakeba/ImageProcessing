import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import model.pixel.Channel;
import model.pixel.ChannelB;
import model.pixel.ChannelG;
import model.pixel.ChannelR;
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
  public void testSetValueR() {
    this.r.setValue(200);
    assertEquals(200, this.r.getValue(), 0.001);
  }

  @Test
  public void testSetValueNegativeR() {
    this.r.setValue(-5);
    assertEquals(0, this.r.getValue(), 0.001);
  }

  @Test
  public void testSetValueOvershootR() {
    this.r.setValue(300);
    assertEquals(255, this.r.getValue(), 0.001);
  }

  @Test
  public void testSetValueG() {
    this.g.setValue(200);
    assertEquals(200, this.g.getValue(), 0.001);
  }

  @Test
  public void testSetValueNegativeG() {
    this.g.setValue(-5);
    assertEquals(0, this.g.getValue(), 0.001);
  }

  @Test
  public void testSetValueOvershootG() {
    this.g.setValue(300);
    assertEquals(255, this.g.getValue(), 0.001);
  }

  @Test
  public void testSetValueB() {
    this.b.setValue(200);
    assertEquals(200, this.b.getValue(), 0.001);
  }

  @Test
  public void testSetValueNegativeB() {
    this.b.setValue(-5);
    assertEquals(0, this.b.getValue(), 0.001);
  }

  @Test
  public void testSetValueOvershootB() {
    this.b.setValue(300);
    assertEquals(255, this.b.getValue(), 0.001);
  }

  @Test
  public void testEqualsTrue() {
    assertEquals(this.r, new ChannelR(50));
  }

  @Test
  public void testEqualsFalseDiffValue() {
    assertNotEquals(this.r, new ChannelR(51));
  }

  @Test
  public void testEqualsFalseSameValueDiffColor() {
    assertNotEquals(this.r, this.b);
  }

}
