package view;

import controller.Features;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DebugGraphics;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
  private JPanel mainPanel;
  private JPanel imagePanel;

  private JPanel rightPanel;
  private JPanel rightTopPanel;
  private JPanel rightMidPanel;
  private JPanel rightBottomPanel;

  private JLabel display, layerLabel;
  private ImageIcon image;
  private JScrollPane imageScroll;

  private JButton saveButton, scriptButton, importButton, exportButton, blurButton, sharpenButton, setCurrentButton;
  private JButton greyscaleButton, sepiaButton, visibleButton, invisibleButton, newLayerButton, removeLayerButton;

  private JComboBox layerSelection;
  private String[] allLayers;

  private JLabel newLayerName;
  private JTextField newLayerNameInput;

//  private JFileChooser textFile;

  private static int gridSize = 4;

  private ROInstagramModel instaModelRo;

  public InstagramJFrameView(ROInstagramModel instaModelRo) {
    super("Instagram OOD");

    setSize(500, 300);
    setLocation(500, 500);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    mainPanel = new JPanel();

    rightPanel = new JPanel();
    rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.PAGE_AXIS));
    rightTopPanel = new JPanel();
    rightMidPanel = new JPanel();
    rightBottomPanel = new JPanel();
    rightBottomPanel.setLayout(new BoxLayout(rightBottomPanel, BoxLayout.PAGE_AXIS));
    // This is the xy in the window
    // rightPanel.setPreferredSize(new Dimension(150, 100));

    imagePanel = new JPanel();

    image = new ImageIcon();
    display = new JLabel(image);
    imageScroll = new JScrollPane(display);
    imageScroll.setPreferredSize(new Dimension(500, 400));
    imageScroll.createVerticalScrollBar();
    imageScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    imagePanel.add(imageScroll);

    saveButton = new JButton("Save");
    scriptButton = new JButton("Script");
    importButton = new JButton("Import");
    exportButton = new JButton("Export");
    blurButton = new JButton("Blur");
    sharpenButton = new JButton("Sharpen");
    greyscaleButton = new JButton("Greyscale");
    sepiaButton = new JButton("Sepia");
    visibleButton = new JButton("Visible");
    invisibleButton = new JButton("Invisible");
    setCurrentButton = new JButton("Set Current.");
    newLayerButton = new JButton("Add Layer");
    removeLayerButton = new JButton("Remove layer");

    // right top panel set up
    layerLabel = new JLabel("Please input layer name:");
    newLayerNameInput = new JTextField(10);
    rightTopPanel.add(newLayerNameInput);
    rightTopPanel.add(newLayerButton);
    rightTopPanel.add(removeLayerButton);

    // right middle panel set up
    List<String> allLayersTemp = instaModelRo.getLayerNames();
    allLayers = allLayersTemp.toArray(new String[0]);
    layerSelection = new JComboBox(allLayers);
    rightMidPanel.add(layerSelection);
    rightMidPanel.add(visibleButton);
    rightMidPanel.add(invisibleButton);
    rightMidPanel.add(setCurrentButton);

    // right bottom panel set up
    rightBottomPanel.add(saveButton);
    rightBottomPanel.add(scriptButton);
    rightBottomPanel.add(importButton);
    rightBottomPanel.add(exportButton);
    rightBottomPanel.add(blurButton);
    rightBottomPanel.add(sharpenButton);
    rightBottomPanel.add(greyscaleButton);
    rightBottomPanel.add(sepiaButton);

    rightPanel.add(rightTopPanel);
    rightPanel.add(rightMidPanel);
    rightPanel.add(rightBottomPanel);

    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
    mainPanel.add(imagePanel, Component.LEFT_ALIGNMENT);
    mainPanel.add(rightPanel, Component.RIGHT_ALIGNMENT);
    this.add(mainPanel);



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

    visibleButton.addActionListener(evt -> feature.makeVisible((String)layerSelection.getSelectedItem()));
    invisibleButton.addActionListener(evt -> feature.makeInvisible((String)layerSelection.getSelectedItem()));

    newLayerButton.addActionListener(evt -> addLayer(feature));
    removeLayerButton.addActionListener(evt -> removeLayer(feature));

    setCurrentButton.addActionListener(evt -> feature.setCurrent(((String)layerSelection.getSelectedItem())));
    layerSelection.addActionListener(evt -> feature.setCurrent(
        (String) layerSelection.getSelectedItem()));
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

  private void addLayer(Features feature) {
    boolean success = feature.addLayer(newLayerNameInput.getText());
    if(success) {
      layerSelection.addItem(newLayerNameInput.getText());
    }
    newLayerNameInput.setText("");
  }

  private void removeLayer(Features feature) {
    boolean success = feature.removeLayer(newLayerNameInput.getText());
    if(success) {
      layerSelection.removeItem(newLayerNameInput.getText());
    }
    newLayerNameInput.setText("");
  }

  @Override
  public void renderMessage(String message) throws IOException {
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
        "JPG, PNG, & PPM Images", "jpg", "png", "ppm");
    fchooser.setFileFilter(filter);
    int retvalue = fchooser.showOpenDialog(InstagramJFrameView.this);
    if (retvalue == JFileChooser.APPROVE_OPTION) {
      File f = fchooser.getSelectedFile();
      resultPath = f.getAbsolutePath();
    }
    return resultPath;
  }


}
