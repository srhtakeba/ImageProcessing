package view;

import java.io.IOException;

public class InstagramTextView implements InstagramView {
  private Appendable ap;


  public InstagramTextView(Appendable ap) throws IllegalArgumentException {
    if(ap == null) {
      throw new IllegalArgumentException("The appendable can not be null.");
    }
    this.ap = ap;
  }

  /**
   * Render a specific message to the provided data destination.
   *
   * @param message the message to be transmitted
   * @throws IOException if transmission of the board to the provided data destination fails
   */
  @Override
  public void renderMessage(String message) throws IOException {
    ap.append(message);
  }
}
