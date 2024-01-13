package fr.ivan.gameOfLife;

import fr.ivan.profiler.Profiler;

public class Game {
    private boolean[][] grid;
    private final int width;
    private final int height;
    private final Profiler profiler;

    public Game(boolean[][] grid, Profiler profiler) {
        this.profiler = profiler;
        profiler.start("Game");
        this.grid = grid;
        this.height = grid.length;
        assert height>0;
        this.width = grid[0].length;
        profiler.finish("Game");
    }
    public boolean[][] nextIt() {
        profiler.start("Game.nextIt");
        boolean[][] newGrid = new boolean[height][width];
        byte[][] nbNeighbours = getNeighbours();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                newGrid[y][x] = nbNeighbours[y][x] == 3 || (nbNeighbours[y][x] == 2 && grid[y][x]);
            }
        }
        profiler.finish("Game.nextIt");
        grid = newGrid;
        System.gc();
        return grid;
    }

    private byte[][] getNeighbours() {
        profiler.start("Game.getNeighbours");
        System.out.println(Runtime.getRuntime().freeMemory());
        byte[][] nb = new byte[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (!grid[y][x])
                    continue;
                for (int dy = -1; dy <= 1; dy++) {
                    for (int dx = -1; dx <= 1; dx++) {
                        if (dx == 0 && dy == 0)
                            continue;
                        if (y+dy < 0 || height <= y+dy || x+dx < 0 || width <= x+dx)
                            continue;
                        nb[y+dy][x+dx]++;
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
