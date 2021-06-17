import static org.junit.Assert.assertEquals;

import controller.Controller;
import controller.IController;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.InstagramLayerModel;
import org.junit.Before;
import org.junit.Test;

public class MockModelConnectionTest {
  MockModel model;
  StringBuilder expectedLog;
  Readable in;
  Appendable out;

  @Before
  public void setup() {
    model = new MockModel();
    expectedLog = new StringBuilder();
  }

  @Test
  public void checkMCConnection() {

    in = new StringReader("n\n 1\n new test\n remove test\n"
        + "invisible test\n visible test\n current test\n filter test\n transform test\n q\n");
    out = new StringBuffer();
    IController controller = new MockController(model, in, out);
    expectedLog.append("add: test\n");
    expectedLog.append("remove: test\n");
    expectedLog.append("invisible: test\n");
    expectedLog.append("visible: test\n");
    expectedLog.append("current: test\n");
    expectedLog.append("filter: test\n");
    expectedLog.append("transform: test\n");
    controller.go();

    assertEquals(expectedLog.toString(), model.getLogContents());
  }
}
