package fr.ivan.gameOfLife;

import fr.ivan.profiler.*;
import fr.ivan.gameOfLife.util.Utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.util.Date;
import java.util.HashSet;
import java.util.stream.Collectors;

import static fr.ivan.gameOfLife.util.Utils.*;
import static java.util.stream.Collectors.toList;

public class Main {

    static void timeIt(Runnable r){
        long start = System.nanoTime();
        r.run();
        long end = System.nanoTime();
        System.out.println((end - start)*0.000000001 + "s");
    }

    static void printGrid(boolean[][] grid) {
        System.out.println("-".repeat(grid[0].length));
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[0].length; x++) {
                System.out.print(grid[y][x] ? "O" : " ");
            }
            System.out.println();
        }
        System.out.println("-".repeat(grid[0].length));
    }

    static void saveGrig(boolean[][] grid, int scale, String file) throws IOException {
        ImageIO.write(toImg(grid, scale), "png", new File(file));
    }

    @SuppressWarnings("SameParameterValue")
    static void runFor(String folder, String filename, int duration, int scale, Game game) throws IOException {
        for (int i = 0; i < duration; i++) {
            saveGrig(game.nextIt(), scale, folder + "/" + filename + i + ".png");
        }
        System.out.println(game);
    }

    public static void main(String[] args) throws IOException {
        runFor("games", "game", 5, 30, new Game(new GameBuilder()
                .add("meta_pixel_galaxy.rle", 0, 0)
//                .add("meta_pixel_off.rle",    0, 0)
//                .add("meta_pixel_off.rle", 2048, 0)
//                .add("meta_pixel_off.rle", 4096, 0)
//                .add("meta_pixel_on.rle",    0, 2048)
//                .add("meta_pixel_on.rle", 2048, 2048)
//                .add("meta_pixel_on.rle", 4096, 2048)
//                .add("meta_pixel_off.rle",    0, 4096)
//                .add("meta_pixel_off.rle", 2048, 4096)
//                .add("meta_pixel_off.rle", 4096, 4096)
                .build(), new TimeProfiler()));

//        boolean[][] grid = new GameBuilder()
//                .add("meta_pixel_galaxy.rle", 0, 0)
//                .build();
//        printGrid(grid);

//        System.out.println(Utils.gridToRle(grid));

    }
}
