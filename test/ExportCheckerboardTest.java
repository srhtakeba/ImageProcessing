import model.InstaImage.ImageImpl;
import model.InstaImage.InstaImage;
import model.InstagramModel;
import model.InstagramModelImpl;
import org.junit.Test;

public class ExportCheckerboardTest {
  InstaImage checkerboard = new ImageImpl().makeCheckerBoard(20);
  InstagramModel model = new InstagramModelImpl(checkerboard);

  @Test
  public void testExportPPM() {
    model.exportAsPPM();
  }

  @Test
  public void testExportRainbowPPM() {
    model = new InstagramModelImpl(new ImageImpl().makeRainbow(30, 70));
    model.exportAsPPM();
  }

}
