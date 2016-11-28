/*
 * Date: Mar 1, 2008
 * Time: 7:13:22 PM
 * (c) 2008 Shawn Silverman
 */
package maze;

import java.util.LinkedList;
import java.util.Random;

/**
 * Implements a maze generator that uses the Recursive Backtracking algorithm.
 * <p>
 * This produces a maze with a small number of longer dead ends, and usually a
 * long and twisty solution.</p>
 *
 * @see <a href="http://www.astrolog.org/labyrnth/algrithm.htm">Think Labyrinth:
 * Maze Algorithms</a>
 *
 * @author Shawn Silverman
 */
public class RecursiveBacktracker extends MazeGenerator {
    // The starting coordinates

    private int startX;
    private int startY;

    private Random rand = new Random();

    /**
     * Creates a new Recursive Backtracking maze generator. A random starting
     * location will be selected.
     *
     * @param width the maze width
     * @param height the maze height
     */
    public RecursiveBacktracker(int width, int height) {
        super(width, height);
    }

    /**
     * Creates a new Recursive Backtracking maze generator. This uses the given
     * starting location.
     *
     * @param width the maze width
     * @param height the maze height
     * @param startX the starting X-coordinate
     * @param startY the starting Y-coordinate
     */
    public RecursiveBacktracker(int width, int height,
            int startX, int startY) {
        super(width, height);

        checkLocation(startX, startY);

        this.startX = startX;
        this.startY = startY;
    }

    /**
     * Generate the maze.
     */
    @Override
    protected void generateMaze() {
        int width = getWidth();
        int height = getHeight();

        boolean[] cells = new boolean[width * height];  // Visited flags
        LinkedList<Cell> stack = new LinkedList<>();

        Cell cell = new Cell(startX, startY);
        
        stack.addFirst(cell);
        int[] neighbours = new int[4];

        do {
            // Mark the current cell as visited

            cells[cell.y * width + cell.x] = true;

            // Examine the current cell's neighbours
            int freeNeighbourCount = 0;
            for (int i = 0; i < 4; i++) {
                switch (i) {
                    case UP:
                        if (cell.y > 0 && !cells[(cell.y - 1) * width + cell.x]) {
                            neighbours[freeNeighbourCount++] = i;
                        }
                        break;
                    case RIGHT:
                        if (cell.x < width - 1 && !cells[cell.y * width + (cell.x + 1)]) {
                            neighbours[freeNeighbourCount++] = i;
                        }
                        break;
                    case DOWN:
                        if (cell.y < height - 1 && !cells[(cell.y + 1) * width + cell.x]) {
                            neighbours[freeNeighbourCount++] = i;
                        }
                        break;
                    case LEFT:
                        if (cell.x > 0 && !cells[cell.y * width + (cell.x - 1)]) {
                            neighbours[freeNeighbourCount++] = i;
                        }
                        break;
                }
            }

            // Pick a random free neighbour
            if (freeNeighbourCount > 0) {
                stack.addFirst(cell);
                cell = new Cell(cell.x, cell.y);

                switch (neighbours[rand.nextInt(freeNeighbourCount)]) {
                    case UP:
                        carve(cell.x, cell.y, UP);
                        cell.y--;
                        break;
                    case RIGHT:
                        carve(cell.x, cell.y, RIGHT);
                        cell.x++;
                        break;
                    case DOWN:
                        carve(cell.x, cell.y, DOWN);
                        cell.y++;
                        break;
                    case LEFT:
                        carve(cell.x, cell.y, LEFT);
                        cell.x--;
                        break;
                }
                /*
                System.out.println(cell);
                print(System.out);
                System.out.println();*/
            } else {
                cell = stack.removeFirst();
            }
        } while (!stack.isEmpty());
    }

    /**
     * Returns a string representation of this object.
     *
     * @return a string representation of this object.
     */
    @Override
    public String toString() {
        return "Recursive Backtracker maze generator";
    }

    public void setStart(int startX, int startY) {
        this.startX = startX;
        this.startY = startY;
    }
}
