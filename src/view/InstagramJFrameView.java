package view;

import controller.Features;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DebugGraphics;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.ROInstagramModel;

public class InstagramJFrameView extends JFrame implements InstagramGUIView {
  private JPanel mainPanel;
  private JPanel imagePanel;
  private JPanel rightPanel;

  private JLabel display;
  private ImageIcon image;
  private JScrollPane imageScroll;

  private JButton saveButton, scriptButton, importButton, exportButton, blurButton, sharpenButton;
  private JButton greyscaleButton, sepiaButton, visibleButton, invisibleButton, newLayerButton;

  private JComboBox layerSelection;

  private JLabel newLayerName;
  private JTextField newLayerNameInput;

  private JFileChooser textFile;

  private static int gridSize = 4;

  private ROInstagramModel instaModelRo;

  public InstagramJFrameView(ROInstagramModel instaModelRo) {
    super("Instagram OOD");

    setSize(500, 300);
    setLocation(500, 500);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    mainPanel = new JPanel();

    rightPanel = new JPanel();
    // This is the xy in the window
    // rightPanel.setPreferredSize(new Dimension(150, 100));

    image = new ImageIcon();
    display = new JLabel(image);
    imageScroll = new JScrollPane();
    imageScroll.createVerticalScrollBar();
    imageScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    display.add(imageScroll);


    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
    mainPanel.add(rightPanel, Component.RIGHT_ALIGNMENT);
    mainPanel.add(display, Component.LEFT_ALIGNMENT);
    this.add(mainPanel);

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

    rightPanel.add(saveButton);
    rightPanel.add(scriptButton);
    rightPanel.add(importButton);
    rightPanel.add(exportButton);
    rightPanel.add(blurButton);
    rightPanel.add(sharpenButton);
    rightPanel.add(greyscaleButton);
    rightPanel.add(sepiaButton);
    rightPanel.add(visibleButton);
    rightPanel.add(invisibleButton);
    rightPanel.add(newLayerButton);

//    selectionPanel = new JPanel();
//    selectionPanel.setBorder(BorderFactory.createTitledBorder("Select layer"));
//    selectionPanel.setLayout(new BoxLayout(selectionPanel, BoxLayout.X_AXIS));
//    pane.add(selectionPanel);

    newLayerName = new JLabel();
    rightPanel.add(newLayerName);

    newLayerNameInput = new JTextField(10);
    rightPanel.add(newLayerNameInput);

    layerSelection = new JComboBox();
    rightPanel.add(layerSelection);

    // pack and make visible
    pack();
    setVisible(true);

    this.instaModelRo = instaModelRo;
  }

  @Override
  public void display() {
    BufferedImage currImage = instaModelRo.exportImage();
    image = new ImageIcon(currImage);
    display.setIcon(image);
    revalidate();
  }


  @Override
  public void addFeatures(Features feature) {

    // JUST A FEATURE REFF
//    void setCurrent(String layerName);
//    void saveProject(String dirName);
//    void openProject(String dirpath);
//    void importScript(String filepath);
//    void importImage(String filepath);
//    void exportImage(String filepath);

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
//    newLayerButton.addActionListener(evt -> feature.exportImage();


  }

  @Override
  public String receiveInput() {
    return null;
  }

  // receiveTextInput
  // receiveImageInput

  @Override
  public void clearInput() {

  }

  @Override
  public void renderMessage(String message) throws IOException {

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
