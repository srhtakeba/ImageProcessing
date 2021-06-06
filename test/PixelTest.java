import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import model.Pixel.ChannelB;
import model.Pixel.ChannelG;
import model.Pixel.ChannelR;
import model.Pixel.Pixel;
import model.Pixel.PixelImpl;
import org.junit.Before;
import org.junit.Test;

public class PixelTest {
  private Pixel red;
  private Pixel green;
  private Pixel blue;
  private Pixel brown;

  @Before
  public void setup() {
    this.red = new PixelImpl(255.0, 0.0, 0.0);
    this.green = new PixelImpl(0, 255.0, 0.0);
    this.blue = new PixelImpl(0,0,255);
    this.brown = new PixelImpl(100,100,100);
  }

  @Test
  public void testGetR() {
    assertEquals(new ChannelR(255), this.red.getR());
    assertEquals(new ChannelR(0), this.green.getR());
    assertEquals(new ChannelR(0), this.blue.getR());
    assertEquals(new ChannelR(100), this.brown.getR());
  }

  @Test
  public void testGetG() {
    assertEquals(new ChannelG(0), this.red.getG());
    assertEquals(new ChannelG(255), this.green.getG());
    assertEquals(new ChannelG(0), this.blue.getG());
    assertEquals(new ChannelG(100), this.brown.getG());
  }

  @Test
  public void testGetB() {
    assertEquals(new ChannelB(0), this.red.getB());
    assertEquals(new ChannelB(0), this.green.getB());
    assertEquals(new ChannelB(255), this.blue.getB());
    assertEquals(new ChannelB(100), this.brown.getB());
  }

  @Test
  public void testSetR() {
    this.red.setR(100);
    assertEquals(new ChannelR(100), this.red.getR());
  }

  @Test
  public void testSetRNegative() {
    this.red.setR(-50);
    assertEquals(new ChannelR(0), this.red.getR());
  }

  @Test
  public void testSetROvershoot() {
    this.red.setR(256);
    assertEquals(new ChannelR(255), this.red.getR());
  }

  @Test
  public void testSetG() {
    this.green.setG(100);
    assertEquals(new ChannelG(100), this.green.getG());
  }

  @Test
  public void testSetGNegative() {
    this.green.setG(-50);
    assertEquals(new ChannelG(0), this.green.getG());
  }

  @Test
  public void testSetGOvershoot() {
    this.green.setG(256);
    assertEquals(new ChannelG(255), this.green.getG());
  }

  @Test
  public void testSetB() {
    this.blue.setB(100);
    assertEquals(new ChannelG(100), this.blue.getB());
  }

  @Test
  public void testSetBNegative() {
    this.blue.setB(-50);
    assertEquals(new ChannelG(0), this.blue.getB());
  }

  @Test
  public void testSetBOvershoot() {
    this.blue.setB(256);
    assertEquals(new ChannelG(255), this.blue.getB());
  }

  @Test
  public void testEqualsTrue() {
    assertTrue(new PixelImpl(255, 0, 0).equals(this.red));
  }

  @Test
  public void testEqualsFalse() {
    assertFalse(new PixelImpl(254, 0, 0).equals(this.red));
  }
}
