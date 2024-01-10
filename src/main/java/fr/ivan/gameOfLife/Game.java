package fr.ivan.gameOfLife;

public class Game {
    private boolean[][] grid;
    private final int width;
    private final int height;

    public Game(boolean[][] grid) {
        this.grid = grid;
        this.height = grid.length;
        assert height>0;
        this.width = grid[0].length;

    }
    public boolean[][] nextIt() {
        boolean[][] newGrid = new boolean[height][width];

        int[][] nbNeighbours = getNeighbours();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                newGrid[y][x] = nbNeighbours[y][x] == 3 || (nbNeighbours[y][x] == 2 && grid[y][x]);
            }
        }

        return grid = newGrid;
    }

    private int[][] getNeighbours() {
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
        return nb;
    }
}
