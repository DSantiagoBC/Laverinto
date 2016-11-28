package maze;

import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.*;

/**
 * A single entity moving round our maze. It maintains its position in terms of
 * grid cells. Note that the positions are floating point numbers allowing us to
 * be partially across a cell.
 *
 * @author Kevin Glass
 */
public class Entity {

    /**
     * The x position of this entity in terms of grid cells
     */
    private float x;
    /**
     * The y position of this entity in terms of grid cells
     */
    private float y;
    /**
     * The sprites to draw for this entity
     */
    private Image[][] sprites;
    /**
     * The maze which this entity is wandering around
     */
    private CanvasMaze maze;
    /**
     * The angle to draw this entity at
     */
    private double ang;
    /**
     * The size of this entity, this is used to calculate collisions with walls
     */
    private float sizeX = 0.32f;
    private float sizeY = 0.32f;
    private int flast = 10;

    private String player;
    private int ani;

    /**
     * Create a new entity in the game
     *
     * @param maze The maze this entity is going to wander around
     * @param x The initial x position of this entity in grid cells
     * @param y The initial y position of this entity in grid cells
     */
    public Entity(CanvasMaze maze, String player, float x, float y) {
        this.maze = maze;
        this.player = player;
        this.x = x;
        this.y = y;
        this.ani = 1;
    }

    /**
     * Move this entity a given amount. This may or may not succeed depending on
     * collisions
     *
     * @param dx The amount to move on the x axis
     * @param dy The amount to move on the y axis
     * @return True if the move succeeded
     */
    public boolean move(float dx, float dy) {
        // work out what the new position of this entity will be
        float nx = x + dx;
        float ny = y + dy;

        // check if the new position of the entity collides with
        // anything
        if (validLocation(nx, ny)) {
            // if it doesn't then change our position to the new position
            x = nx;
            y = ny;

            // and calculate the angle we're facing based on our last move
            ang = Math.atan2(dy, dx) - (Math.PI / 2);

            return true;
        }

        // if it wasn't a valid move don't do anything apart from 
        // tell the caller
        return false;
    }

    /**
     * Check if the entity would be at a valid location if its position was as
     * specified
     *
     * @param nx The potential x position for the entity
     * @param ny The potential y position for the entity
     * @return True if the new position specified would be valid
     */
    public boolean validLocation(float nx, float ny) {
        // here we're going to check some points at the corners of
        // the player to see whether we're at an invalid location
        // if any of them are blocked then the location specified
        // isn't valid
        if (maze.blocked(nx - sizeX, ny + (float) 0.1)) {
            return false;
        }
        if (maze.blocked(nx + sizeX, ny + (float) 0.1)) {
            return false;
        }
        if (maze.blocked(nx - sizeX, ny + (float) 0.57)) {
            return false;
        }
        if (maze.blocked(nx + sizeX, ny + (float) 0.57)) {
            return false;
        }

        // if all the points checked are unblocked then we're in an ok
        // location
        return true;
    }

    /**
     * Draw this entity to the graphics context provided.
     *
     * @param g The graphics context to which the entity should be drawn
     */
    public void paint(Graphics2D g) {
        // work out the screen position of the entity based on the
        // x/y position and the size that tiles are being rendered at. So
        // if we're at 1.5,1.5 and the tile size is 10 we'd render on screen 
        // at 15,15.
        int xp = (int) (CanvasMaze.TILE_SIZE * x);
        int yp = (int) (CanvasMaze.TILE_SIZE * y);
        // rotate the sprite based on the current angle and then
        // draw it
        if (this.ani == 4 * flast) {
            this.ani = 1;
        }
        int ang = (int)((this.ang/Math.PI) * 100);
        switch (ang) {
            case 0: {
                g.drawImage(sprites[0][(int) (this.ani/flast)],
                        (xp - sprites[0][(int) (this.ani/flast)].getWidth(null) / 2),
                        (yp - sprites[0][(int) (this.ani/flast)].getHeight(null) / 2),
                        null);
                break;
            }
            case -25: {
                g.drawImage(sprites[1][(int) (this.ani/flast)],
                        (xp - sprites[1][(int) (this.ani/flast)].getWidth(null) / 2),
                        (yp - sprites[1][(int) (this.ani/flast)].getHeight(null) / 2),
                        null);
                break;
            }
            case -50: {
                g.drawImage(sprites[2][(int) (this.ani/flast)],
                        (xp - sprites[2][(int) (this.ani/flast)].getWidth(null) / 2),
                        (yp - sprites[2][(int) (this.ani/flast)].getHeight(null) / 2),
                        null);
                break;
            }
            case -75: {
                g.drawImage(sprites[3][(int) (this.ani/flast)],
                        (xp - sprites[3][(int) (this.ani/flast)].getWidth(null) / 2),
                        (yp - sprites[3][(int) (this.ani/flast)].getHeight(null) / 2),
                        null);
                break;
            }
            case -100: {
                g.drawImage(sprites[4][(int) (this.ani/flast)],
                        (xp - sprites[4][(int) (this.ani/flast)].getWidth(null) / 2),
                        (yp - sprites[4][(int) (this.ani/flast)].getHeight(null) / 2),
                        null);
                break;
            }
            case -125: {
                g.drawImage(sprites[5][(int) (this.ani/flast)],
                        (xp - sprites[5][(int) (this.ani/flast)].getWidth(null) / 2),
                        (yp - sprites[5][(int) (this.ani/flast)].getHeight(null) / 2),
                        null);
                break;
            }
            case 50: {
                g.drawImage(sprites[6][(int) (this.ani/flast)],
                        (xp - sprites[6][(int) (this.ani/flast)].getWidth(null) / 2),
                        (yp - sprites[6][(int) (this.ani/flast)].getHeight(null) / 2),
                        null);
                break;
            }
            case 25: {
                g.drawImage(sprites[7][(int) (this.ani/flast)],
                        (xp - sprites[7][(int) (this.ani/flast)].getWidth(null) / 2),
                        (yp - sprites[7][(int) (this.ani/flast)].getHeight(null) / 2),
                        null);
                break;
            }

        }
        this.ani++;
    }

    void paintframe(Graphics2D g) {
        // work out the screen position of the entity based on the
        // x/y position and the size that tiles are being rendered at. So
        // if we're at 1.5,1.5 and the tile size is 10 we'd render on screen 
        // at 15,15.
        int xp = (int) (CanvasMaze.TILE_SIZE * x);
        int yp = (int) (CanvasMaze.TILE_SIZE * y);
        // rotate the sprite based on the current angle and then
        // draw it
        int ang = (int)((this.ang/Math.PI) * 100);
        switch (ang) {
            case 0: {
                g.drawImage(sprites[0][0],
                        (xp - sprites[0][0].getWidth(null) / 2),
                        (yp - sprites[0][0].getHeight(null) / 2),
                        null);
                break;
            }
            case -25: {
                g.drawImage(sprites[1][0],
                        (xp - sprites[1][0].getWidth(null) / 2),
                        (yp - sprites[1][0].getHeight(null) / 2),
                        null);
                break;
            }
            case -50: {
                g.drawImage(sprites[2][0],
                        (xp - sprites[2][0].getWidth(null) / 2),
                        (yp - sprites[2][0].getHeight(null) / 2),
                        null);
                break;
            }
            case -75: {
                g.drawImage(sprites[3][0],
                        (xp - sprites[3][0].getWidth(null) / 2),
                        (yp - sprites[3][0].getHeight(null) / 2),
                        null);
                break;
            }
            case -100: {
                g.drawImage(sprites[4][0],
                        (xp - sprites[4][0].getWidth(null) / 2),
                        (yp - sprites[4][0].getHeight(null) / 2),
                        null);
                break;
            }
            case -125: {
                g.drawImage(sprites[5][0],
                        (xp - sprites[5][0].getWidth(null) / 2),
                        (yp - sprites[5][0].getHeight(null) / 2),
                        null);
                break;
            }
            case 50: {
                g.drawImage(sprites[6][0],
                        (xp - sprites[6][0].getWidth(null) / 2),
                        (yp - sprites[6][0].getHeight(null) / 2),
                        null);
                break;
            }
            case 25: {
                g.drawImage(sprites[7][0],
                        (xp - sprites[7][0].getWidth(null) / 2),
                        (yp - sprites[7][0].getHeight(null) / 2),
                        null);
                break;
            }

        }
    }

    public Image[][] fillSprites() {
        this.sprites = new Image[8][4];
        String[][] imagePath = new String[8][4];
        String direction;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 4; j++) {
                switch (i) {
                    case 0: {
                        direction = "F";
                        break;
                    }
                    case 1: {
                        direction = "FR";
                        break;
                    }
                    case 2: {
                        direction = "R";
                        break;
                    }
                    case 3: {
                        direction = "BR";
                        break;
                    }
                    case 4: {
                        direction = "B";
                        break;
                    }
                    case 5: {
                        direction = "BL";
                        break;
                    }
                    case 6: {
                        direction = "L";
                        break;
                    }
                    case 7: {
                        direction = "FL";
                        break;
                    }
                    default:
                        direction = "F";

                }
                imagePath[i][j] = "pictures/" 
                        + this.player 
                        + "/walk" 
                        + direction 
                        + j
                        + ".png";
            }
        }
        ImageIcon ii;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 4; j++) {
                ii = new ImageIcon(imagePath[i][j]);
                sprites[i][j] = ii.getImage();
            }
        }
        return sprites;
    }
}
