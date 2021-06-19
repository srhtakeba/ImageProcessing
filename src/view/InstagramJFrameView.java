package view;

import java.awt.Component;
import java.io.IOException;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class InstagramJFrameView extends JFrame implements InstagramGUIView {
  private JPanel pane, right;
  private JLabel display;
  private JScrollPane imageScroll;
  private JButton saveButton, scriptButton, importButton, exportButton, blurButton, sharpenButton;
  private JButton greyscaleButton, sepiaButton, visibleButton, invisibleButton, newLayerButton;
  private JPanel selectionPanel;
  private JLabel input;


  public InstagramJFrameView() {
    super("Instagram OOD");

    setSize(500, 300);
    setLocation(500, 500);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    pane = new JPanel();
    right = new JPanel();
    display = new JLabel();
    pane.setLayout(new BoxLayout(pane, BoxLayout.X_AXIS));
    pane.add(right, Component.RIGHT_ALIGNMENT);
    pane.add(display, Component.LEFT_ALIGNMENT);



    // pack and make visible
    pack();
    setVisible(true);
  }

  @Override
  public void display() {

  }

  @Override
  public void renderMessage(String message) throws IOException {

  }
}
