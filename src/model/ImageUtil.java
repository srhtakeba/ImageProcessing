package model;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import model.Pixel.Pixel;
import model.Pixel.PixelImpl;


/**
 * This class contains utility methods to read a PPM image from file and simply print its contents.
 * Feel free to change this method as required.
 */
public class ImageUtil {

  int cols;
  int rows;
  int[][] importedImage = new int[cols][rows];

  public ImageUtil(int cols, int rows, int[][] importedImage) {
    this.cols = cols;
    this.rows = rows;
    this.importedImage = importedImage;
  }


  /**
   * Read an image file in the PPM format and print the colors.
   *
   * @param filename the path of the file.
   */
  public static Pixel[][] readPPM(String filename) {
    Scanner sc;
    Pixel pixcel;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      System.out.println("File " + filename + " not found!");
      return null; // check this if this is a bad practice or not
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();

//    System.out.println("Width of image: " + width);
    int height = sc.nextInt();
//    System.out.println("Height of image: " + height);
    int maxValue = sc.nextInt();
//    System.out.println("Maximum value of a color in this file (usually 256): " + maxValue);
//    importedRaw = new int[width][height];
    Pixel[][] importedRaw = new Pixel[width][height];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int rRaw = sc.nextInt();
        int gRaw = sc.nextInt();
        int bRaw = sc.nextInt();
        double r = rRaw;
        double g = gRaw;
        double b = bRaw;
//        System.out.println("Color of pixel (" + j + "," + i + "): " + r + "," + g + "," + b);
        pixcel = new PixelImpl(r, g, b);
        importedRaw[i][j] = pixcel;
      }
    }

    return importedRaw;
  }

  //demo main
  public static void main(String[] args) {
    String filename;

    if (args.length > 0) {
      filename = args[0];
    } else {
      filename = "/Users/katsuhikonakanishi/Desktop/CS3500/hw5/src/Koala.ppm";
    }

    ImageUtil.readPPM(filename);
  }

}

