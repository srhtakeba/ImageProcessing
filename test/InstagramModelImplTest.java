import static org.junit.Assert.assertEquals;

import model.InstaImage.ImageImpl;
import model.InstaImage.InstaImage;
import model.InstagramModel;
import model.InstagramModelImpl;
import org.junit.Before;
import org.junit.Test;

public class InstagramModelImplTest {

  private InstagramModel modelCheckerBoard;
  private InstaImage checkerBoard;

  @Before
  public void setup() {
    checkerBoard = new ImageImpl();

    modelCheckerBoard = new InstagramModelImpl(checkerBoard.makeCheckerBoard(3));
  }

  @Test
  public void testGreyscaleCheckerboard() {
    modelCheckerBoard.transform("GREYSCALE");
    InstaImage expected = new ImageImpl().makeCheckerBoard(3);
    assertEquals(expected.toString(),
        modelCheckerBoard.exportAsInstaImage().toString());
  }

}