package view;

import java.io.IOException;

/**
 * Represents a view for {@code InstagramLayerModel} and {@code IController}. Will simply render
 * messagaes to a given appendable.
 */
public interface InstagramView {

  /**
   * Render a specific message to the provided data destination.
   *
   * @param message the message to be transmitted
   * @throws IOException if transmission of the board to the provided data destination fails
   */
  void renderMessage(String message) throws IOException;

}
