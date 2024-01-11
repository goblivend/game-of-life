package fr.ivan.gameOfLife;

import fr.ivan.profiler.Profiler;

public class Game {
    private boolean[][] grid;
    private final int width;
    private final int height;
    private final Profiler profiler;

    public Game(boolean[][] grid, Profiler profiler) {
        this.grid = grid;
        this.height = grid.length;
        assert height>0;
        this.width = grid[0].length;
        this.profiler = profiler;
    }
    public boolean[][] nextIt() {
        profiler.start("Game.nextIt");
        boolean[][] newGrid = new boolean[height][width];

        int[][] nbNeighbours = getNeighbours();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                newGrid[y][x] = nbNeighbours[y][x] == 3 || (nbNeighbours[y][x] == 2 && grid[y][x]);
            }
        }
        profiler.finish("Game.nextIt");
        return grid = newGrid;
    }

    private int[][] getNeighbours() {
        profiler.start("Game.getNeighbours");
        int[][] nb = new int[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (!grid[y][x])
                    continue;

                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        if (i == 0 && j == 0)
                            continue;
                        if (y+j < 0 || height <= y+j || x+i < 0 || width <= x+i)
                            continue;
                        nb[y+j][x+i]++;
                    }
                }
            }
        }
        profiler.finish("Game.getNeighbours");
        return nb;
    }

    @Override
    public String toString() {
        return profiler.toString();
    }
}
