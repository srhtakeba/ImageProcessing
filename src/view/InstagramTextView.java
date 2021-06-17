package view;

import java.io.IOException;

/**
 * Represents a text view for {@code InstagramLayerModel} and {@code IController}. Will simply
 * render messagaes to a given appendable.
 */
public class InstagramTextView implements InstagramView {

  private final Appendable ap;


  /**
   * Constructor to create a {@code InstagramTextView} object.
   *
   * @param ap the appendable to append messages to
   * @throws IllegalArgumentException if given a null Appendable object
   */
  public InstagramTextView(Appendable ap) throws IllegalArgumentException {
    if (ap == null) {
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
