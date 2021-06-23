package view;

import controller.Features;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.ROInstagramModel;

/**
 * Class to represent Graphic User Interface for our Image Processing application using JFrame.
 */
public class InstagramJFrameView extends JFrame implements InstagramGUIView {

  private final JLabel display;
  private ImageIcon image;

  private final JButton saveButton;
  private final JButton scriptButton;
  private final JButton importButton;
  private final JButton exportButton;
  private final JButton blurButton;
  private final JButton greyscaleButton;
  private final JButton sepiaButton;
  private final JButton visibleButton;
  private final JButton invisibleButton;
  private final JButton newLayerButton;
  private final JButton mosaicButton;
  private final JButton sharpenButton;
  private final JButton setCurrentButton;
  private final JButton removeLayerButton;

  private final JComboBox layerSelection;

  private final JMenuItem saveMenu;
  private final JMenuItem scriptMenu;
  private final JMenuItem importMenu;
  private final JMenuItem exportMenu;
  private final JMenuItem blurMenu;
  private final JMenuItem sharpenMenu;
  private final JMenuItem greyscaleMenu;
  private final JMenuItem sepiaMenu;
  private final JMenuItem visibleMenu;
  private final JMenuItem invisibleMenu;
  private final JMenuItem newLayerMenu;
  private final JMenuItem mosaicMenu;
  private final JMenuItem currentMenu;
  private final JMenuItem removeLayerMenu;

  private final JTextField newLayerNameInput;

  private final ROInstagramModel instaModelRo;

  /**
   * Constructs a new {@code InstagramJFramView} GUI-style object.
   * @param instaModelRo the model to be represented with this view.
   */
  public InstagramJFrameView(ROInstagramModel instaModelRo) {
    super("Instagram OOD");

    setPreferredSize(new Dimension(700, 500));
    setLocation(250, 250);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // building panels and their layouts
    JPanel mainPanel = new JPanel();

    JPanel rightPanel = new JPanel();
    rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.PAGE_AXIS));

    JPanel rightTopPanel = new JPanel();
    rightTopPanel.setBorder(BorderFactory.createLineBorder(Color.black));
    rightTopPanel.setLayout(new GridLayout());

    JPanel rightMidPanel = new JPanel();
    rightMidPanel.setBorder(BorderFactory.createLineBorder(Color.black));
    rightMidPanel.setLayout(new GridLayout());

    JPanel rightBottomPanel = new JPanel();
    rightBottomPanel.setLayout(new GridBagLayout());
    rightBottomPanel.setLayout(new BoxLayout(rightBottomPanel, BoxLayout.PAGE_AXIS));
    rightBottomPanel.setBorder(BorderFactory.createLineBorder(Color.black));
    JPanel rightBottomPanelTop = new JPanel();
    rightBottomPanelTop.setLayout(new GridLayout());
    JPanel rightBottomPanelBottom = new JPanel();
    rightBottomPanelBottom.setLayout(new GridLayout());

    JPanel imagePanel = new JPanel();
    imagePanel.setLayout(new GridBagLayout());

    // making image screen
    image = new ImageIcon();
    display = new JLabel(image);
    JScrollPane imageScroll = new JScrollPane(display);
    imageScroll.setPreferredSize(new Dimension(300, 180));
    imageScroll.createVerticalScrollBar();
    imageScroll.createHorizontalScrollBar();
    imageScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    imagePanel.add(imageScroll);

    // making buttons
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
    setCurrentButton = new JButton("Set Current");
    newLayerButton = new JButton("Add Layer");
    removeLayerButton = new JButton("Remove Layer");
    mosaicButton = new JButton("Mosaic");

    // right top panel set up
    newLayerNameInput = new JTextField(10);
    rightTopPanel.add(newLayerNameInput);
    rightTopPanel.add(newLayerButton);
    rightTopPanel.add(removeLayerButton);
    rightTopPanel.setPreferredSize(new Dimension(400, 100));

    // right middle panel set up
    List<String> allLayersTemp = instaModelRo.getLayerNames();
    String[] allLayers = allLayersTemp.toArray(new String[0]);
    layerSelection = new JComboBox(allLayers);
    rightMidPanel.add(layerSelection);
    rightMidPanel.add(visibleButton);
    rightMidPanel.add(invisibleButton);
    rightMidPanel.add(setCurrentButton);
    rightMidPanel.setPreferredSize(new Dimension(400, 130));

    // right bottom panel set up
    rightBottomPanelTop.add(saveButton);
    rightBottomPanelTop.add(scriptButton);
    rightBottomPanelTop.add(importButton);
    rightBottomPanelTop.add(exportButton);
    rightBottomPanelBottom.add(blurButton);
    rightBottomPanelBottom.add(sharpenButton);
    rightBottomPanelBottom.add(greyscaleButton);
    rightBottomPanelBottom.add(sepiaButton);
    rightBottomPanelBottom.add(mosaicButton);
    rightBottomPanel.setPreferredSize(new Dimension(400, 270));

    rightPanel.add(rightTopPanel);
    rightPanel.add(rightMidPanel);
    rightBottomPanel.add(rightBottomPanelTop);
    rightBottomPanel.add(rightBottomPanelBottom);
    rightPanel.add(rightBottomPanel);

    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
    mainPanel.add(imagePanel, Component.LEFT_ALIGNMENT);
    mainPanel.add(rightPanel, Component.RIGHT_ALIGNMENT);
    this.add(mainPanel);

    // making the menu
    // menu item
    JMenuBar menuBar = new JMenuBar();
    JMenu menu = new JMenu("Tools");
    menuBar.add(menu);

    saveMenu = new JMenuItem("Save");
    menu.add(saveMenu);
    scriptMenu = new JMenuItem("Script");
    menu.add(scriptMenu);
    importMenu = new JMenuItem("Import");
    menu.add(importMenu);
    exportMenu = new JMenuItem("Export");
    menu.add(exportMenu);
    blurMenu = new JMenuItem("Blur");
    menu.add(blurMenu);
    sharpenMenu = new JMenuItem("Sharpen");
    menu.add(sharpenMenu);
    greyscaleMenu = new JMenuItem("Greyscale");
    menu.add(greyscaleMenu);
    sepiaMenu = new JMenuItem("Sepia");
    menu.add(sepiaMenu);
    visibleMenu = new JMenuItem("Visible Current Layer");
    menu.add(visibleMenu);
    invisibleMenu = new JMenuItem("Invisible current Layer");
    menu.add(invisibleMenu);
    currentMenu = new JMenuItem("Set Current");
    menu.add(currentMenu);
    newLayerMenu = new JMenuItem("Add Layer");
    menu.add(newLayerMenu);
    removeLayerMenu = new JMenuItem("Remove Layer");
    menu.add(removeLayerMenu);
    mosaicMenu = new JMenuItem("Mosaic");
    menu.add(mosaicMenu);

    this.setJMenuBar(menuBar);

    // pack and make visible
    pack();
    setVisible(true);

    this.instaModelRo = instaModelRo;
  }

  /**
   * Update the image in the display of this view.
   */
  @Override
  public void display() {
    BufferedImage currImage = instaModelRo.exportImage();
    image = new ImageIcon(currImage);
    display.setIcon(image);
    revalidate();
  }


  /**
   * Add interactive features to this view's elements.
   *
   * @param feature an {@code Features} object that holds the commands for action listeners.
   */
  @Override
  public void addFeatures(Features feature) {
    // button action listeners
    saveButton.addActionListener(evt -> feature.saveProject(saveWindow()));
    scriptButton.addActionListener(evt -> importScript(feature));
    importButton.addActionListener(evt -> feature.importImage(importWindow()));
    exportButton.addActionListener(evt -> feature.exportImage(saveWindow()));
    blurButton.addActionListener(evt -> feature.blur());
    sharpenButton.addActionListener(evt -> feature.sharpen());
    greyscaleButton.addActionListener(evt -> feature.greyscale());
    sepiaButton.addActionListener(evt -> feature.sepia());
    mosaicButton.addActionListener(
        evt -> feature.mosaic(JOptionPane.showInputDialog("Enter the seed value.")));

    visibleButton
        .addActionListener(evt -> feature.makeVisible((String) layerSelection.getSelectedItem()));
    invisibleButton
        .addActionListener(evt -> feature.makeInvisible((String) layerSelection.getSelectedItem()));

    newLayerButton.addActionListener(evt -> addLayer(feature));
    removeLayerButton.addActionListener(evt -> removeLayer(feature));

    setCurrentButton
        .addActionListener(evt -> feature.setCurrent(((String) layerSelection.getSelectedItem())));
    layerSelection.addActionListener(evt -> feature.setCurrent(
        (String) layerSelection.getSelectedItem()));

    // menu item action listeners
    saveMenu.addActionListener(evt -> feature.saveProject(saveWindow()));
    scriptMenu.addActionListener(evt -> importScript(feature));
    importMenu.addActionListener(evt -> feature.importImage(importWindow()));
    exportMenu.addActionListener(evt -> feature.exportImage(saveWindow()));
    blurMenu.addActionListener(evt -> feature.blur());
    sharpenMenu.addActionListener(evt -> feature.sharpen());
    greyscaleMenu.addActionListener(evt -> feature.greyscale());
    sepiaMenu.addActionListener(evt -> feature.sepia());
    mosaicMenu.addActionListener(
        evt -> feature.mosaic(JOptionPane.showInputDialog("Enter the seed value.")));

    visibleMenu
        .addActionListener(evt -> feature.makeVisible(instaModelRo.currentLayer()));
    invisibleMenu
        .addActionListener(evt -> feature.makeInvisible(instaModelRo.currentLayer()));

    newLayerMenu.addActionListener(evt -> addLayerFromInput(feature));
    removeLayerMenu.addActionListener(evt -> removeLayerFromInput(feature));

    currentMenu
        .addActionListener(evt -> setCurrentFromInput(feature));
  }

  /**
   * Sets the current layer by showing a pop-up prompting for a layer name. Also adjusts the
   * selection box appropriately.
   *
   * @param feature the {@code Features} object to dispatch the addition in the model.
   */
  private void setCurrentFromInput(Features feature) {
    String layer = JOptionPane.showInputDialog("Enter the layer name.");
    feature.setCurrent(layer);
    layerSelection.setSelectedItem(layer);
  }

  /**
   * Add a layer to the current model, adjusting the combo box appropriately.
   *
   * @param feature the {@code Features} object to dispatch the addition in the model.
   */
  private void addLayer(Features feature) {
    boolean success = feature.addLayer(newLayerNameInput.getText());
    if (success
        && ((DefaultComboBoxModel) layerSelection.getModel()).getIndexOf(
        newLayerNameInput.getText()) == -1) {
      layerSelection.addItem(newLayerNameInput.getText());
    }
    newLayerNameInput.setText("");
  }

  /**
   * Remove a layer to the current model, adjusting the combo box appropriately.
   *
   * @param feature the {@code Features} object to dispatch the removal in the model.
   */
  private void removeLayer(Features feature) {
    boolean success = feature.removeLayer(newLayerNameInput.getText());
    if (success) {
      layerSelection.removeItem(newLayerNameInput.getText());
    }
    newLayerNameInput.setText("");
  }

  /**
   * Add a layer to the current model, adjusting the combo box appropriately.
   *
   * @param feature the {@code Features} object to dispatch the addition in the model.
   */
  private void addLayerFromInput(Features feature) {
    String layer = JOptionPane.showInputDialog("Enter the layer name.");
    boolean success = feature.addLayer(layer);
    if (success
        && ((DefaultComboBoxModel) layerSelection.getModel()).getIndexOf(
        layer) == -1) {
      layerSelection.addItem(layer);
    }
  }

  /**
   * Remove a layer to the current model, adjusting the combo box appropriately.
   *
   * @param feature the {@code Features} object to dispatch the removal in the model.
   */
  private void removeLayerFromInput(Features feature) {
    String layer = JOptionPane.showInputDialog("Enter the layer name.");
    boolean success = feature.removeLayer(layer);
    if (success) {
      layerSelection.removeItem(layer);
    }
  }

  /**
   * Send a message to the user.
   *
   * @param message the message to be transmitted
   * @throws IOException if showing the message fails
   */
  @Override
  public void renderMessage(String message) throws IOException {
    JOptionPane.showMessageDialog(this, message);
  }

  /**
   * Asks the user to write a new file and then returns the string representing the absolute path of
   * the new file.
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
   * Asks the user to choose a file from a file choose screen, and then returns the absolute path of
   * that file.
   *
   * @return the absolute path of the selected file
   */
  private String importWindow() {
    String resultPath = "";
    final JFileChooser fchooser = new JFileChooser(".");
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
        "JPG, JPEG, PNG, & PPM Images", "jpg", "png", "ppm", "jpeg");
    fchooser.setFileFilter(filter);
    int retvalue = fchooser.showOpenDialog(InstagramJFrameView.this);
    if (retvalue == JFileChooser.APPROVE_OPTION) {
      File f = fchooser.getSelectedFile();
      resultPath = f.getAbsolutePath();
    }
    return resultPath;
  }

  /**
   * Asks the user to choose a txt file from a file choose screen, and then returns the absolute
   * path of that file.
   *
   */
  private void importScript(Features feature) {
    String resultPath = "";
    final JFileChooser fchooser = new JFileChooser(".");
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
        "txt files", "txt");
    fchooser.setFileFilter(filter);
    fchooser.setDialogTitle("Select the .txt file containing the script for the project.");
    int retvalue = fchooser.showOpenDialog(InstagramJFrameView.this);
    if (retvalue == JFileChooser.APPROVE_OPTION) {
      File f = fchooser.getSelectedFile();
      resultPath = f.getAbsolutePath();
    }
    feature.importScript(resultPath);
    layerSelection.removeAllItems();
    for (String s : instaModelRo.getLayerNames()) {
      layerSelection.addItem(s);
    }
  }
}
