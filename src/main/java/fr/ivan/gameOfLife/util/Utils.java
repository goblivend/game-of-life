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
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class Utils {
    public static BufferedImage toImg(boolean[][] arr, byte scale) {
        int width = arr[0].length;
        int height = arr.length;
        System.gc();
        BufferedImage img = new BufferedImage(width*scale, height*scale, Image.SCALE_FAST);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                for (byte k = 0; k < scale; k++) {
                    for (byte l = 0; l < scale; l++) {
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

    public static String gridToRle(boolean[][] grid) {
        String rle = "#Java Generated code written by Ivan\n";
        rle += String.format("x = %d, y = %d, rule = B3/S23\n", grid[0].length, grid.length);

        List<List<String>> lines = new ArrayList<>();
        for (var line : grid) {
            lines.add(lineToRle(line));
        }

        rle += formatRleContent(lines.stream().reduce((l1, l2) -> {
            List<String> l = new ArrayList<>(l1);
            l.add("$");
            l.addAll(l2);
            return l;
        }).orElse(new ArrayList<>()));

        return rle + "!";
    }

    private static String formatRleContent(List<String> words) {
        return words.stream().reduce((acc, w) -> {
            int lst = acc.lastIndexOf('\n');
            int length = acc.length() - lst;
            if (length + w.length() > 71)
                acc += "\n";
            return acc + w;
        }).orElse("");
    }

    private static List<String> lineToRle(boolean[] line) {
        List<String> l = new ArrayList<>();

        int counter = 0;
        boolean current = false;
        for (boolean e : line) {
            if (e == current) {
                counter++;
                continue;
            }
            if (counter != 0) {
                String word = "";
                if (counter > 1)
                    word += counter;
                word += current ? "o" : "b";
                l.add(word);
            }
            current = e;
            counter = 1;
        }

        String word = "";
        if (counter > 1)
            word += counter;
        word += current ? "o" : "b";

        if (current)
            l.add(word);

        return l;
    }

    public static boolean[][] fromFile(InputStream file) {
        List<String> lines = getLines(file);
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

    public static boolean[][] fromRleFile(InputStream file) {
        return fromRle(getLines(file));
    }


    public static boolean[][] fromRle(List<String> lines) {
        Point size = sizeFromRleLines(lines.get(1));
        boolean[][] grid = new boolean[size.y][size.x];
        String[] data = String.join("", lines.subList(2, lines.size()))
                .split("\\$");

        for (int y = 0; y < grid.length; y++) {
            grid[y] = parseRleLine(data[y], size.x);
        }

        return grid;
    }

    private static boolean[] parseRleLine(String line, int size) {
        boolean[] arr = new boolean[size];

        int curr = 0;
        String acc = "";

        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == '!')
                break;
            acc += line.charAt(i);
            if (!acc.endsWith("o") && !acc.endsWith("b"))
                continue;

            int limit = acc.length() > 1
                    ? Integer.decode(acc.substring(0, acc.length()-1))
                    : 1;
            boolean value = acc.charAt(acc.length()-1) == 'o';
            for (int j = 0; j < limit; j++) {
                arr[curr+j] = value;
            }
            curr += limit;
            acc = "";
        }

        return arr;
    }

    private static Point sizeFromRleLines(String size) {
        Integer x = Integer.decode(size.replaceFirst("x = ", "")
                .replaceFirst(", y = .*", ""));

        Integer y = Integer.decode(size.replaceFirst("x = \\d*, y = ", "")
                .replaceFirst(", rule = .*", ""));
        return new Point(x, y);
    }

    public static List<String> getLines(InputStream file) {
        List<String> lines = new ArrayList<>();

        BufferedReader r = new BufferedReader(new InputStreamReader(file));
        String line;
        try {
            while ((line = r.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return lines;
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
