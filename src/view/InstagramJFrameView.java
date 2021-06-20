package view;

import controller.Features;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DebugGraphics;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.ROInstagramModel;

public class InstagramJFrameView extends JFrame implements InstagramGUIView {
  private JPanel pane, buttonPanel;

  private JLabel display;

  private JScrollPane imageScroll;
  private JButton saveButton, scriptButton, importButton, exportButton, blurButton, sharpenButton;
  private JButton greyscaleButton, sepiaButton, visibleButton, invisibleButton, newLayerButton;
  private JPanel selectionPanel;

  private JLabel newLayerName;
  private JTextField newLayerNameInput;

  private static int gridSize = 4;

  private ROInstagramModel instaModelRo;

  public InstagramJFrameView(ROInstagramModel instaModelRo) {
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

    imageScroll = new JScrollPane(pane);
    add(imageScroll);

    saveButton = new JButton("Save");
    scriptButton = new JButton("Script");
    importButton = new JButton("Import");
    exportButton = new JButton("Export");
    blurButton = new JButton("Blur");
    sharpenButton = new JButton("Sharpen");
    greyscaleButton = new JButton("greyscaleButton");
    sepiaButton = new JButton("sepiaButton");
    visibleButton = new JButton("visibleButton");
    invisibleButton = new JButton("invisibleButton");
    newLayerButton = new JButton("newLayerButton");

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

    selectionPanel = new JPanel();
    selectionPanel.setBorder(BorderFactory.createTitledBorder("Select layer"));
    selectionPanel.setLayout(new BoxLayout(selectionPanel, BoxLayout.X_AXIS));
    pane.add(selectionPanel);

    newLayerName = new JLabel();
    pane.add(newLayerName);

    newLayerNameInput = new JTextField(10);
    pane.add(newLayerNameInput);

    // pack and make visible
    pack();
    setVisible(true);

    this.instaModelRo = instaModelRo;
  }

  @Override
  public void display() {
//    Graphics2D graphicToDisplay = instaModelRo.exportImage().createGraphics();
    Graphics newGraphic = new DebugGraphics();
    newGraphic.drawImage(instaModelRo.exportImage(), 0, 0, this.display);

  }

  @Override
  public void addFeatures(Features feature) {
    saveButton.addActionListener(evt -> feature.saveProject(saveWindow()));
    scriptButton.addActionListener(evt -> feature.importScript(importWindow()));
    importButton.addActionListener(evt -> feature.importImage(importWindow()));
    exportButton.addActionListener(evt -> feature.exportImage(saveWindow()));
    blurButton.addActionListener(evt -> feature.blur());
    sharpenButton.addActionListener(evt -> feature.sharpen());
    greyscaleButton.addActionListener(evt -> feature.greyscale());
    sepiaButton.addActionListener(evt -> feature.sepia());
    visibleButton.addActionListener(evt -> feature.makeVisible());
    invisibleButton.addActionListener(evt -> feature.makeInvisible());
    newLayerButton.addActionListener(evt -> feature.addLayer(newLayerNameInput.getText()));
  }

  @Override
  public String receiveInput() {
    return null;
  }

  @Override
  public void clearInput() {

  }

  /**
   * Shows a message in the form of a pop-up dialog window.
   * @param message the message to be transmitted
   */
  @Override
  public void renderMessage(String message) {
    JOptionPane.showMessageDialog(this, message);
  }

  /**
   * Asks the user to write a new file and then returns the string representing
   * the absolute path of the new file
   *
   * @return the absolute path of the new file
   */
  private String saveWindow() {
    String resultPath = "";
    final JFileChooser fchooser = new JFileChooser(".");
    int retvalue = fchooser.showSaveDialog(InstagramJFrameView.this);
    if (retvalue == JFileChooser.APPROVE_OPTION) {
      File f = fchooser.getSelectedFile();
      resultPath = f.getAbsolutePath();
    }
    return resultPath;
  }

  /**
   * Asks the user to choose a file from a file choose screen, and then returns the absolute
   * path of that file
   *
   * @return the absolute path of the selected file
   */
  private String importWindow() {
    String resultPath = "";
    final JFileChooser fchooser = new JFileChooser(".");
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
        "JPG, PNG, & PPM Images", "jpg", "gif", "ppm");
    fchooser.setFileFilter(filter);
    int retvalue = fchooser.showOpenDialog(InstagramJFrameView.this);
    if (retvalue == JFileChooser.APPROVE_OPTION) {
      File f = fchooser.getSelectedFile();
      resultPath = f.getAbsolutePath();
    }
    return resultPath;
  }


}
