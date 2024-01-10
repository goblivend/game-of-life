package fr.ivan.gameOfLife;

import fr.ivan.profiler.*;
import fr.ivan.gameOfLife.util.Utils;

import javax.imageio.ImageIO;
import java.awt.image.*;
import java.io.*;
import java.util.Date;

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

    static void runFor(String folder, String filename, int duration, int scale, Game game) throws IOException {
        for (int i = 0; i < duration; i++) {
            ImageIO.write(Utils.toImg(game.nextIt(), scale), "png", new File(folder + "/" + filename + i + ".png"));
        }
    }




    public static void main(String[] args) throws IOException {
        boolean[][] grid1 = new boolean[][] {
                {false, true, false},
                {false, true, false},
                {false, true, false}
        };

        runFor("games", "game", 50, 30, new Game(grid1));

    }
}
