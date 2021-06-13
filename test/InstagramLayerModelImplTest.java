import static org.junit.Assert.*;

import model.InstagramModel;
import model.InstagramModelImpl;
import model.image.ImageImpl;
import model.image.InstaImage;
import org.junit.Before;

public class InstagramLayerModelImplTest {

  private InstagramModel modelCheckerBoard;

  private InstagramModel modelRainbow;

  private InstagramModel modelRainbowSingleFile;

  @Before
  public void setup() {
    InstaImage checkerBoard = new ImageImpl().makeCheckerBoard(3);
    modelCheckerBoard = new InstagramModelImpl(checkerBoard);

    InstaImage rainbow = new ImageImpl().makeRainbow(3, 1);
    modelRainbow = new InstagramModelImpl(rainbow);

    InstaImage rainbowSingleFile = new ImageImpl().makeRainbow(1, 1);
    modelRainbowSingleFile = new InstagramModelImpl(rainbowSingleFile);
  }



}