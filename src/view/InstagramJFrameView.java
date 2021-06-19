package view;

import controller.Features;
import java.awt.Component;
import java.awt.GridLayout;
import java.io.IOException;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class InstagramJFrameView extends JFrame implements InstagramGUIView {
  private JPanel pane, buttonPanel;
  private JLabel display;
  private JScrollPane imageScroll;
  private JButton saveButton, scriptButton, importButton, exportButton, blurButton, sharpenButton;
  private JButton greyscaleButton, sepiaButton, visibleButton, invisibleButton, newLayerButton;
  private JPanel selectionPanel;
  private JLabel input;
  private static int gridSize = 4;

  public InstagramJFrameView() {
    super("Instagram OOD");

    setSize(500, 300);
    setLocation(500, 500);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    pane = new JPanel();

    buttonPanel = new JPanel();
    //buttonPanel.setLayout(new GridLayout(gridSize, gridSize, 10, 10));

    display = new JLabel();

    pane.setLayout(new BoxLayout(pane, BoxLayout.X_AXIS));
    pane.add(buttonPanel, Component.RIGHT_ALIGNMENT);
    pane.add(display, Component.LEFT_ALIGNMENT);
    this.add(pane);

    saveButton = new JButton("Save");
    scriptButton = new JButton("Script");
    importButton = new JButton("Import");
    exportButton = new JButton("Export");
    blurButton = new JButton("Blur");
    sharpenButton = new JButton("Sharpen");
    importButton = new JButton("greyscaleButton");
    exportButton = new JButton("sepiaButton");
    blurButton = new JButton("visibleButton");
    sharpenButton = new JButton("invisibleButton");
    sharpenButton = new JButton("newLayerButton");

    buttonPanel.add(saveButton);
    buttonPanel.add(scriptButton);
    buttonPanel.add(importButton);
    buttonPanel.add(exportButton);
    buttonPanel.add(blurButton);
    buttonPanel.add(sharpenButton);
    buttonPanel.add(greyscaleButton);
    buttonPanel.add(sepiaButton);
    buttonPanel.add(visibleButton);
    buttonPanel.add(invisibleButton);
    buttonPanel.add(newLayerButton);

    // pack and make visible
    pack();
    setVisible(true);
  }

  @Override
  public void display() {

  }

  @Override
  public void addFeatures(Features feature) {

  }

  @Override
  public String receiveInput() {
    return null;
  }

  @Override
  public void clearInput() {

  }

  @Override
  public void renderMessage(String message) throws IOException {

  }



}
