package maze;

import java.awt.*;
import java.util.ArrayList;

public class CanvasMaze {

    /**
     * The width in grid cells of our Maze
     */
    private int WIDTH=5;
    /**
     * The height in grid cells of our Maze
     */
    private int HEIGHT=5;
    /**
     * The rendered size of the tile (in pixels)
     */
    public static final int TILE_SIZE = 15;
    private MazeGenerator maze;
    private ArrayList<Rectangle> paredes;
    private boolean[][] mazeWalls;

    public CanvasMaze() {
        this.maze = new RecursiveBacktracker(WIDTH, HEIGHT, 0, 0);
        this.maze.generate();
        this.maze.print(System.out);
        this.paredes = new ArrayList<>();
    }

    public void paint(Graphics2D g2d) {
        //Laberinto

        mazeWalls = convTileCoord(this.maze.getHorizWalls(), this.maze.getVertWalls());/*
        for (int x = 0; x < WIDTH * 2 + 1; x++) {
            for (int y = 0; y < HEIGHT * 2 + 1; y++) {

                g2d.setColor(Color.DARK_GRAY);
                if (mazeWalls[x][y]) {
                    g2d.setColor(Color.GRAY);
                }

                // draw the rectangle with a dark outline
                g2d.fillRect(TILE_SIZE * WIDTH * 2 - x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                if (mazeWalls[x][y]) {
                    g2d.setColor(g2d.getColor().darker());
                }
                g2d.drawRect(TILE_SIZE * WIDTH * 2 - x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            }
        }*/
        for (int x = 0; x < WIDTH * 2 + 1; x++) {
            for (int y = 0; y < HEIGHT * 2 + 1; y++) {

                // so if the cell is blocks, draw a light grey block
                // otherwise use a dark gray
                g2d.setColor(Color.DARK_GRAY);
                if (mazeWalls[x][y]) {
                    g2d.setColor(Color.GRAY);
                }

                // draw the rectangle with a dark outline
                g2d.fillRect(/*TILE_SIZE * WIDTH * 2 */+ x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                if (mazeWalls[x][y]) {
                    g2d.setColor(g2d.getColor().darker());
                }
                g2d.drawRect(/*TILE_SIZE * WIDTH * 2 */+ x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            }
        }

    }

    public boolean[][] convTileCoord(boolean[] horiz, boolean[] vert) {
        boolean[][] tiles = new boolean[WIDTH * 2 + 1][HEIGHT * 2 + 1];
        int rowBase;
        for (int y = 0; y < HEIGHT * 2 + 1; y++) {
            for (int x = 0; x < WIDTH * 2 + 1; x++) {
                if (x % 2 == 0 && y % 2 == 0) {
                    tiles[x][y] = true;
                } else if (x == 0 || y == 0 || x == WIDTH * 2 || y == HEIGHT * 2) {
                    tiles[x][y] = true;
                } else if (x % 2 != 0 && y % 2 != 0) {
                    tiles[x][y] = false;
                }
            }
        }

        for (int y = 0; y < HEIGHT; y++) {

            rowBase = y * WIDTH;
            for (int x = 0; x < WIDTH; x++) {
                if (horiz[rowBase + x]) {
                    tiles[x * 2 + 1][y * 2] = true;
                }
            }

            rowBase = y * (WIDTH + 1);
            for (int x = 0; x < WIDTH; x++) {
                if (vert[rowBase + x]) {
                    tiles[x * 2][y * 2 + 1] = true;
                }
            }
        }
        return tiles;
    }

    /**
     * Check if a particular location on the map is blocked. Note that the x and
     * y parameters are floating point numbers meaning that we can be checking
     * partially across a grid cell.
     *
     * @param x The x position to check for blocking
     * @param y The y position to check for blocking
     * @return True if the location is blocked
     */
    public boolean blocked(float x, float y) {
        // look up the right cell (based on simply rounding the floating
        // values) and check the value
        return mazeWalls[(int) x][(int) y];
    }

    public void setSize(int WIDTH, int HEIGHT) {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
    }

    public int getTotalWIDTH() {
        return WIDTH*2+1;
    }

    public int getTotalHEIGHT() {
        return HEIGHT*2+1;
    }
}