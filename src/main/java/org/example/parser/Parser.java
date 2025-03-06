package org.example.parser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Parser {
/*
    private Color[] spectr;
    public QuarterOfHectare[][] map;

    public Parser(String fileSpectr, String fileMap, int low, int high){
        try{
            BufferedImage spectrImage = ImageIO.read(new File(fileSpectr));
            int l = spectrImage.getHeight();
            //System.out.println(l);
            spectr = new Color[l];
            for(int i = 0; i < l; i++){
                spectr[i] = new Color(spectrImage.getRGB(3, i));
                //System.out.println(i + ": " + spectr[i]);
            }

            BufferedImage mapImage = ImageIO.read(new File(fileMap));
            map = new QuarterOfHectare[mapImage.getHeight()][mapImage.getWidth()];
            for (int i = 0; i < map.length; i++) {
                System.out.print(i + ": ");
                for (int j = 0; j < map[i].length; j++) {
                    map[i][j] = new QuarterOfHectare(mapImage.getRGB(j, i));
                    map[i][j].countHeight(spectr, low, high);
                    System.out.print(map[i][j].getH() + " ");
                }
                System.out.println();
            }
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }*/

    public static void parsing(String fileSpectr, String fileMap, int low, int high){
        Color[] spectr;
        QuarterOfHectare[][] map;
        try{
            BufferedImage spectrImage = ImageIO.read(new File(fileSpectr));
            int l = spectrImage.getHeight();
            //System.out.println(l);
            spectr = new Color[l];
            for(int i = 0; i < l; i++){
                spectr[i] = new Color(spectrImage.getRGB(3, i));
                //System.out.println(i + ": " + spectr[i]);
            }

            BufferedImage mapImage = ImageIO.read(new File(fileMap));
            map = new QuarterOfHectare[mapImage.getHeight()][mapImage.getWidth()];
            for (int i = 0; i < map.length; i++) {
                System.out.print(i + ": ");
                for (int j = 0; j < map[i].length; j++) {
                    map[i][j] = new QuarterOfHectare(mapImage.getRGB(j, i));
                    map[i][j].countHeight(spectr, low, high);
                    System.out.print(map[i][j].getH() + " ");
                }
                System.out.println();
            }
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
