package org.example.parser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class HeightParser {
    private int minimumColorOfPixel = 0;
    private int maximumColorOfPixel = 255;
    private final String fileSpector;
    private final int low;
    private final int high;
    private final int sizePixel;

    public HeightParser(String fileSpector, int low, int high, int sizePixel){
        this.fileSpector = fileSpector;
        this.low = low;
        this.high = high;
        this.sizePixel = sizePixel;
    }

    public void parsing(String fileMap, String output){
        Color[] spector;
        Pixel[][] map;
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(output))){
            BufferedImage spectorImage = ImageIO.read(new File(fileSpector));
            int l = spectorImage.getHeight();
            spector = new Color[l];
            for(int i = 0; i < l; i++){
                spector[i] = new Color(spectorImage.getRGB(10, i));
            }

            BufferedImage mapImage = ImageIO.read(new File(fileMap));
            map = new Pixel[mapImage.getHeight()][mapImage.getWidth()];
            for (int i = 0; i < map.length; i++) {
                //System.out.print(i + ": ");
                for (int j = 0; j < map[i].length; j++) {
                    map[i][j] = new Pixel(mapImage.getRGB(j, i), minimumColorOfPixel, maximumColorOfPixel);
                    map[i][j].countHeight(spector, low, high);
                }
            }

            double diagonal =  Math.sqrt(2 * sizePixel * sizePixel);
            for (int i = 0; i < map.length; i++) {
                System.out.print(i + ": ");
                for (int j = 0; j < map[i].length; j++) {

                    for (int k = -1; k <= 1; k++) {
                        if((i == 0 && k == -1) || (i == map.length - 1 && k == 1)) continue;
                        for (int m = -1; m <= 1; m++) {
                            if(k == 0 && m == 0) continue;
                            if((j == 0 && m == -1) || (j == map[i].length - 1 && m == 1)) continue;
                            map[i][j].setAngle(map[i + k][j + m].getH(), (k != 0 && m != 0)? diagonal : sizePixel);
                        }
                    }

                    System.out.print(map[i][j].getH() + " " + String.format("%.2f", map[i][j].getAngle()) + "; ");
                    bw.write(map[i][j].getH() + " " + String.format("%.2f", map[i][j].getAngle()) + "; ");
                }
                System.out.println();
                bw.write('\n');
            }
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    public int getMinimumColorOfPixel() {
        return minimumColorOfPixel;
    }

    public void setMinimumColorOfPixel(int minimumColorOfPixel) {
        if(minimumColorOfPixel >= 0 && minimumColorOfPixel < 255 && minimumColorOfPixel < this.maximumColorOfPixel){
            this.minimumColorOfPixel = minimumColorOfPixel;
        }
    }

    public int getMaximumColorOfPixel() {
        return maximumColorOfPixel;
    }

    public void setMaximumColorOfPixel(int maximumColorOfPixel) {
        if(maximumColorOfPixel > 0 && maximumColorOfPixel <= 255 && maximumColorOfPixel > this.minimumColorOfPixel){
            this.maximumColorOfPixel = maximumColorOfPixel;
        }
    }
}
