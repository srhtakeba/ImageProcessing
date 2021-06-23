package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import model.image.ImageImpl;
import model.image.InstaImage;
import model.pixel.Pixel;
import model.pixel.PixelImpl;


/**
 * This class contains utility methods to read a PPM image from file and simply print its contents.
 * Feel free to change this method as required.
 */
public class ImageUtil {

  /**
   * Read an image file in the PPM format and print the colors.
   *
   * @param filename the path of the file.
   * @throws IllegalArgumentException if the given file name is invalid.
   */
  public static InstaImage readPPM(String filename) throws IllegalArgumentException {
    Scanner sc;
    Pixel pixel;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      System.out.println("File " + filename + " not found!");
      throw new IllegalArgumentException("File not found.");
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
      throw new IllegalArgumentException("Invalid PPM file: plain RAW file should begin with P3");
    }

    int width = sc.nextInt();
    int height = sc.nextInt();

    // Keeping it just in case. Maximum value of a color in this file (usually 256)
    int maxValue = sc.nextInt();

    Pixel[][] importedRaw = new Pixel[height][width];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int rRaw = sc.nextInt();
        int gRaw = sc.nextInt();
        int bRaw = sc.nextInt();
        double r = rRaw;
        double g = gRaw;
        double b = bRaw;
        pixel = new PixelImpl(r, g, b);
        importedRaw[i][j] = pixel;
      }
    }

    return new ImageImpl(importedRaw, width, height);
  }
}

