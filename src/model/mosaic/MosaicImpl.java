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

public class MosaicImpl implements Mosaic {
  private Map<PosnXY, List<PosnXY>> mosaicMap;
  private List<PosnXY> seeds;

  public MosaicImpl() {
    mosaicMap = new HashMap<>();
    seeds = new ArrayList<>();
  }

  @Override
  public InstaImage apply(InstaImage image, int seed) {
    if(seed > image.getHeight()*image.getWidth()) {
      return image;
    }
    Pixel[][] gridToBeModified = image.getPixelGrid();
    getSeeds(gridToBeModified, seed, image.getWidth(), image.getHeight());
    cluster(gridToBeModified);
    return new ImageImpl(averageOut(gridToBeModified), image.getWidth(), image.getHeight());
  }

  private void getSeeds(Pixel[][] pixelGrid, int seed, int width, int height) {
    Random r = new Random();
    for (int i = 0; i < seed; i++) {
      PosnXY seedChoice = new PosnXY(width+1, height+1);
      while(!seeds.contains(seedChoice)) {
        seedChoice = new PosnXY(r.nextInt(width), r.nextInt(height));
        if(!seeds.contains(seedChoice)) {
          seeds.add(seedChoice);
        }
      }
      mosaicMap.put(seedChoice, new ArrayList<PosnXY>());
    }
  }

  private void cluster(Pixel[][] pixelGrid) {
    for(int i=0;i< pixelGrid.length;i++) {
      for(int j=0;j<pixelGrid[i].length;j++) {
        // for each pixel in the pixelGrid, find it's closest seed pixel
        PosnXY closest = seeds.get(0);
        double minDistance = Math.sqrt(Math.pow(i-closest.x, 2) + Math.pow(j-closest.y, 2));
        for(PosnXY seedPosn : seeds) {
          double tempDistance = Math.sqrt(Math.pow(i-seedPosn.x, 2) + Math.pow(j-seedPosn.y, 2));
          if(tempDistance < minDistance) {
            closest = seedPosn;
            minDistance = tempDistance;
          }
        }
        // add the pixel to its corresponding closest seed pixel in the map
        mosaicMap.get(closest).add(new PosnXY(i,j));
      }
    }
  }

  private Pixel[][] averageOut(Pixel[][] pixelGrid) {
    Pixel[][] result = new Pixel[pixelGrid.length][pixelGrid[0].length];
    for(PosnXY seedPosn : seeds) {
      List<PosnXY> clusterPosnList = mosaicMap.get(seedPosn);
      int totalR = 0;
      int totalG = 0;
      int totalB = 0;
      for(PosnXY posnXY : clusterPosnList) {
        int x = posnXY.getX();
        int y = posnXY.getY();
        totalR += pixelGrid[x][y].getR().getValue();
        totalG += pixelGrid[x][y].getG().getValue();
        totalB += pixelGrid[x][y].getB().getValue();
      }
      int sum = clusterPosnList.size();
      if(sum == 0) {
        sum = 1;
      }
      int averageR = (int)(totalR/sum);
      int averageG = (int)(totalG/sum);
      int averageB = (int)(totalB/sum);
      for(PosnXY posnXY : clusterPosnList) {
        result[posnXY.getX()][posnXY.getY()] = new PixelImpl(averageR, averageG, averageB);
      }
    }
    return result;
  }

  class PosnXY {
    Integer x;
    Integer y;
    PosnXY(int x, int y) {
      this.x = x;
      this.y = y;
    }
    PosnXY() {
      this.x = null;
      this.y = null;
    }

    int getX() {
      return this.x;
    }

    int getY() {
      return this.y;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof PosnXY) || o == null) {
        return false;
      }
      PosnXY that = (PosnXY) o;
      return this.x.equals(that.x) && this.y.equals(that.y);
    }

    @Override
    public int hashCode() {
      return Objects.hash(this.x, this.y);
    }
  }
}
