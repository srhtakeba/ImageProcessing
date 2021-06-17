import controller.Controller;
import model.InstagramLayerModel;

public class MockController extends Controller {

  MockModel mockM;

  public MockController(MockModel model, Readable in, Appendable out) {
    super(in, out);
    this.mockM = model;
  }

  @Override
  public void go() {
    this.model = mockM;
    super.go();
  }
}
