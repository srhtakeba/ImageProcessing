package model.mosaic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import model.image.ImageImpl;
import model.image.InstaImage;
import model.pixel.Pixel;
import model.pixel.PixelImpl;

/**
 * Class representing mosaic functionality for images using random seed selection and averaging out
 * pixels by the nearest seed cluster.
 */
public class MosaicImpl implements Mosaic {

  // map representing the seeds as keys and a list of posns that have the key as the closest seed
  private Map<PosnXY, List<PosnXY>> mosaicMap;
  // the list of posns to represent the randomly chosen seeds
  private List<PosnXY> seeds;

  /**
   * Constructs a new {@code MosaicImpl} object.
   */
  public MosaicImpl() {
    mosaicMap = new HashMap<>();
    seeds = new ArrayList<>();
  }

  /**
   * Applies this random seed mosaic operation to the given image.
   *
   * @param image the {@code InstaImage} image to be mosaic-ed.
   * @param seed  the random seed to be used for the mosaic process.
   * @return the given {@code InstaImage} image, but mosaic-ed.
   * @throws IllegalArgumentException if the given seed is negative or the given image is null
   */
  @Override
  public InstaImage apply(InstaImage image, int seed) {
    if (image == null) {
      throw new IllegalArgumentException("Given image is null.");
    }
    if (seed < 0) {
      throw new IllegalArgumentException("Can not operate with negative seed value.");
    }
    if (seed > image.getHeight() * image.getWidth()) {
      return image;
    }
    Pixel[][] gridToBeModified = image.getPixelGrid();
    getSeeds(gridToBeModified, seed, gridToBeModified.length, gridToBeModified[0].length);
    cluster(gridToBeModified);
    return new ImageImpl(averageOut(gridToBeModified), image.getWidth(),
        image.getHeight());
  }

  /**
   * Select a random set of X Y positions in the given pixel grid.
   *
   * @param pixelGrid the pixel grid to choose random seed XY positions from
   * @param seed      the number of random selections to be made
   * @param width     the width of the pixel grid
   * @param height    the height of the pixel grid
   */
  private void getSeeds(Pixel[][] pixelGrid, int seed, int width, int height) {
    Random r = new Random();
    for (int i = 0; i < seed; i++) {
      PosnXY seedChoice = new PosnXY(width + 1, height + 1);
      while (!seeds.contains(seedChoice)) {
        seedChoice = new PosnXY(r.nextInt(width), r.nextInt(height));
        if (!seeds.contains(seedChoice)) {
          seeds.add(seedChoice);
        }
      }
      mosaicMap.put(seedChoice, new ArrayList<PosnXY>());
    }
  }

  /**
   * For each pixel in the given pixel grid, find the closest random seed selected position, and add
   * that pixels position to this function objects map to keep track of the clusters.
   *
   * @param pixelGrid the pixel grid to be clustered by closest random seed selection
   */
  private void cluster(Pixel[][] pixelGrid) {
    for (int i = 0; i < pixelGrid.length; i++) {
      for (int j = 0; j < pixelGrid[i].length; j++) {
        // for each pixel in the pixelGrid, find it's closest seed pixel
        PosnXY closest = seeds.get(0);
        double minDistance = Math.sqrt(Math.pow(i - closest.x, 2) + Math.pow(j - closest.y, 2));
        for (PosnXY seedPosn : seeds) {
          double tempDistance = Math
              .sqrt(Math.pow(i - seedPosn.x, 2) + Math.pow(j - seedPosn.y, 2));
          if (tempDistance < minDistance) {
            closest = seedPosn;
            minDistance = tempDistance;
          }
        }
        // add the pixel to its corresponding closest seed pixel in the map
        mosaicMap.get(closest).add(new PosnXY(i, j));
      }
    }
  }

  /**
   * Set all the pixel rgb values of the given pixel grid to the average values of the cluster they
   * reside in.
   *
   * @param pixelGrid the pixel grid to be modified
   * @return the pixel grid, but modified by averaging out rgb values by cluster.
   */
  private Pixel[][] averageOut(Pixel[][] pixelGrid) {
    Pixel[][] result = new Pixel[pixelGrid.length][pixelGrid[0].length];
    for (PosnXY seedPosn : seeds) {
      List<PosnXY> clusterPosnList = mosaicMap.get(seedPosn);
      int totalR = 0;
      int totalG = 0;
      int totalB = 0;
      for (PosnXY posnXY : clusterPosnList) {
        int x = posnXY.getX();
        int y = posnXY.getY();
        totalR += pixelGrid[x][y].getR().getValue();
        totalG += pixelGrid[x][y].getG().getValue();
        totalB += pixelGrid[x][y].getB().getValue();
      }
      int sum = clusterPosnList.size();
      if (sum == 0) {
        sum = 1;
      }
      int averageR = (int) (totalR / sum);
      int averageG = (int) (totalG / sum);
      int averageB = (int) (totalB / sum);
      for (PosnXY posnXY : clusterPosnList) {
        result[posnXY.getX()][posnXY.getY()] = new PixelImpl(averageR, averageG, averageB);
      }
    }
    return result;
  }

  /**
   * Inner class to represent an x and y Cartesian position.
   */
  class PosnXY {

    Integer x;
    Integer y;

    /**
     * Creates a new {@code PosnXY} object.
     *
     * @param x int position of x
     * @param y int position y
     */
    PosnXY(int x, int y) {
      this.x = x;
      this.y = y;
    }

    /**
     * Creates a new empty {@code PosnXY} object.
     */
    PosnXY() {
      this.x = null;
      this.y = null;
    }

    /**
     * Getter method for the x position.
     *
     * @return the x position
     */
    int getX() {
      return this.x;
    }

    /**
     * Getter method for the y position.
     *
     * @return the y position
     */
    int getY() {
      return this.y;
    }

    /**
     * Check if this {@code PosnXY} is the same as the given object.
     *
     * @param o the object to be compared for equality
     * @return is the given object the same as this object?
     */
    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (this == null) {
        return o == null;
      }
      if (!(o instanceof PosnXY)) {
        return false;
      }
      PosnXY that = (PosnXY) o;
      return this.x.equals(that.x) && this.y.equals(that.y);
    }

    /**
     * Gets the hash code for this {@code PosnXY} object.
     *
     * @return the hash code for this object.
     */
    @Override
    public int hashCode() {
      return Objects.hash(this.x, this.y);
    }
  }

}
