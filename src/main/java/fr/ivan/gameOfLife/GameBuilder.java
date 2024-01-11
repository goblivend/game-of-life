package fr.ivan.gameOfLife;

import java.util.ArrayList;
import java.util.List;

import static fr.ivan.gameOfLife.util.Utils.fromFile;
import static fr.ivan.gameOfLife.util.Utils.move;

public class GameBuilder {
    private final List<boolean[][]> patterns = new ArrayList<>();

    public GameBuilder() {

    }

    public GameBuilder add(boolean[][] pattern, int dx, int dy) {
        patterns.add(move(pattern, dx, dy));
        return this;
    }

    public GameBuilder add(String file, int dx, int dy) {
        return add(fromFile(getClass().getClassLoader().getResourceAsStream("patterns/" + file)), dx, dy);
    }

    public boolean[][] build() {
        int maxW = 0;
        int maxH = 0;
        for (var pattern: patterns) {
            if (pattern.length > maxH)
                maxH = pattern.length;

            if (pattern[0].length > maxW)
                maxW = pattern[0].length;
        }

        boolean[][] grid = new boolean[maxH][maxW];
        for (var pattern: patterns) {
            for (int y = 0; y < pattern.length; y++) {
                for (int x = 0; x < pattern[0].length; x++) {
                    if (pattern[y][x])
                        grid[y][x] = true;
                }
            }
        }
        return grid;
    }
}
