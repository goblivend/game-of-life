package fr.ivan.gameOfLife.util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.*;
import java.util.HashMap;
import java.util.Map;

public class Utils {
    public static final String al = " .";

    public static BufferedImage toImg(boolean[][] arr, int scale) {
        int width = arr[0].length;
        int height = arr.length;

        BufferedImage img = new BufferedImage(width*scale, height*scale, Image.SCALE_DEFAULT);

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                for (int k = 0; k < scale; k++) {
                    for (int l = 0; l < scale; l++) {
                        if (arr[j][i])
                            img.setRGB(i*scale+k, j*scale+l, Color.WHITE.getRGB());
                        else
                            img.setRGB(i*scale+k, j*scale+l, Color.BLACK.getRGB());
                    }
                }

            }
        }

        return img;
    }
}
