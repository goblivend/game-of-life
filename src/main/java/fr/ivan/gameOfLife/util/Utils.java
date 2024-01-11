package fr.ivan.gameOfLife.util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    public static boolean[][] fromFile(InputStream file) {
        List<String> lines = new ArrayList<>();

        BufferedReader r = new BufferedReader(new InputStreamReader(file));
        String line;
        try {
            while ((line=r.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int h = lines.size();
        int w = 0;
        for (var l : lines) {
            if (l.length() > w) {
                w = l.length();
            }
        }

        boolean[][] grid = new boolean[h][w];
        for (int y = 0; y < lines.size(); y++) {
            for (int x = 0; x < lines.get(y).length(); x++) {
                grid[y][x] = lines.get(y).charAt(x) == 'X';
            }
        }
        return grid;
    }

    public static boolean[][] move(boolean[][] pattern, int dx, int dy) {
        int h = pattern.length + dy;
        int w = pattern[0].length + dx;

        boolean[][] grid = new boolean[h][w];

        for (int y = 0; y < pattern.length; y++) {
            for (int x = 0; x < pattern[0].length; x++) {
                grid[y+dy][x+dx] = pattern[y][x];
            }
        }
        return grid;
    }
}
