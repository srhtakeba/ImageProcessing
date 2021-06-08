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
    model = new InstagramModelImpl(checkerboard);
    model.exportAsPPM("checkerboard");
    model.filter("blur");
    model.exportAsPPM("checkerboardBlur");
    model.filter("sharpen");
    model.exportAsPPM("checkerboardSharpen");
  }

  @Test
  public void testExportRainbowPPM() {
    model = new InstagramModelImpl(new ImageImpl().makeRainbow(30, 70));
    model.exportAsPPM("rainbow");
    model.filter("sharpen");
    model.exportAsPPM("rainbowSharpen");
  }

  @Test
  public void testImportPPMAndExport() {
    InstagramModel flowerModel = new InstagramModelImpl();
    model.readPPM("flower.ppm");
    model.filter("sharpen");
    model.exportAsPPM("flowerSharpen");

  }

}
