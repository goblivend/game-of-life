package fr.ivan.gameOfLife;

import junit.framework.TestCase;
import org.junit.Assert;

public class GameTest extends TestCase {
    public static boolean[][] empty3 = new boolean[][] {
            {false, false, false},
            {false, false, false},
            {false, false, false}
    };

    public void test1Living() {
        boolean[][] grid1 = new boolean[][] {
                {false, false, false},
                {false, true, false},
                {false, false, false}
        };

        Game game = new Game(grid1);

        Assert.assertArrayEquals(empty3, game.nextIt());
    }

    public void testSquare3() {
        boolean[][] grid = new boolean[][] {
                {true, true, false},
                {true, false, false},
                {false, false, false}
        };

        Game game = new Game(grid);

        boolean[][] expected = new boolean[][] {
                {true, true, false},
                {true, true, false},
                {false, false, false}
        };

        Assert.assertArrayEquals(expected, game.nextIt());
    }

    public void testLine3() {
        boolean[][] grid = new boolean[][] {
                {false, false, false},
                {true,  true,  true},
                {false, false, false}
        };

        Game game = new Game(grid);

        boolean[][] expected = new boolean[][] {
                {false, true, false},
                {false, true, false},
                {false, true, false}
        };

        Assert.assertArrayEquals(expected, game.nextIt());
    }

    public void testSquare4() {
        boolean[][] grid = new boolean[][] {
                {true, true, false},
                {true, true, false},
                {false, false, false}
        };

        Game game = new Game(grid);

        Assert.assertArrayEquals(grid, game.nextIt());
    }
}