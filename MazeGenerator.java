package edu.bloomu.homework.projectfive;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Class to generate a character based maze using recursive division.
 *
 * @author Dakotah Kurtz
 */

public class MazeGenerator
{
    private final int height;
    private final int width;
    private final char[][] maze;

    private final GenerationData generationData;




    private final static char WALL = '1';
    private final static char PATH = '-';
    private final static char START = 'b';
    private final static char EXIT = 'e';

    private enum ORIENTATION {HORIZONTAL, VERTICAL} // to determine direction of next wall
    private final ThreadLocalRandom rand;

    /**
     * Constructor to initialize variables and create the perimeter wall around the
     * outside of the maze.
     *
     * @param height of maze to be generated
     * @param width of maze to be generated
     */

    public MazeGenerator(int height, int width) {

        this.height = height;
        this.width = width;

        generationData = new GenerationData();


        rand = ThreadLocalRandom.current();
        maze = new char[height][width];

        makePerimeter();
    }

    /**
     * Prints the maze to console.
     */
    public void printMaze() {
        System.out.println();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(maze[i][j] + " ");
            }
            System.out.print("\n");
        }
        System.out.println();
    }

    /**
     * Public method to initiate recursive division. Adds exit and entrance spots to
     * maze upon completion.
     *
     * @return char[][] of completed maze.
     */

    public char[][] getMaze() {

        make(0, height - 1, 0, width - 1, -1);

        // Add start position near top left corner.
        for (int i = 1; i < height; i++) {
            if (maze[i][1] == PATH) {
                maze[i][0] = START;
                generationData.setStart(new int[]{i, 0});
                break;
            }
        }

        // Add exit position near bottom right corner.
        for (int i = height - 2; i > 0; i--) {
            if (maze[i][width - 2] == PATH) {
                maze[i][width - 1] = EXIT;
                generationData.setEnd(new int[]{i, width - 1});
                break;
            }
        }

        return maze;
    }

    /**
     * Updates "orientation" of maze (the walls are always added across the narrowest
     * dimension).
     *
     * @param height of section being worked on.
     * @param width of section being worked on.
     * @return orientation of next wall (VERTICAL or HORIZONTAL)
     */
    private ORIENTATION updateOrientation(int height, int width) {
        ORIENTATION orientation;
        if (width > height) {
            orientation = ORIENTATION.VERTICAL;
        }
        else if (width < height) {
            orientation = ORIENTATION.HORIZONTAL;
        }
        else { // if even dimensions, choose randomly
            orientation = rand.nextBoolean() ? ORIENTATION.HORIZONTAL :
                    ORIENTATION.VERTICAL;
        }
        return orientation;
    }

    /**
     * Recursive method that creates maze. Calculates new width and height, and sends
     * information to appropriate helper methods.
     *
     * @param minY - Of maze partition, row value closest to 0.
     * @param maxY - Of maze partition, row value closest to maze height
     * @param minX - Of maze partition, column value closest to 0.
     * @param maxX - Of maze partition, column value closest to width.
     */
    private void make(int minY, int maxY, int minX, int maxX, int iteration)
    {
        iteration++;

        int width = maxX - minX;
        int height = maxY - minY;

        if ((width > 3 || height > 3) && (width > 2 && height > 2))
        {
            ORIENTATION orientation = updateOrientation(height, width);

            if (orientation == ORIENTATION.HORIZONTAL)
            {
                splitHorizontal(minY, maxY, minX, maxX, iteration);
            } else
            {
                splitVertical(minY, maxY, minX, maxX, iteration);
            }
        }
    }

    /**
     * Draws a line horizontally through the current partition of the maze. Chooses
     * randomly the row for the wall and the column for the door. Verifies that the new
     * wall will not close a section of the maze off from the main maze by checking if
     * it will intersect the indices of another door.
     *
     * @param minY - Of maze partition, row value closest to 0.
     * @param maxY - Of maze partition, row value closest to maze height
     * @param minX - Of maze partition, column value closest to 0.
     * @param maxX - Of maze partition, column value closest to width.
     */

    private void splitHorizontal(int minY, int maxY, int minX, int maxX, int iteration) {

        // Maintains at least one segment of PATH between walls.
        int wallY = rand.nextInt(minY + 2, maxY - 1);

        // If the column bounds of the wall are a PATH, then a section of the maze will
        // be blocked off.
        boolean lSideCollide = maze[wallY][minX] == PATH;
        boolean rSideCollide = maze[wallY][maxX] == PATH;

        // Add the wall to the maze.
        int count = 0;
        for (int i = minX + 1; i < maxX; i++) {
            maze[wallY][i] = WALL;
            generationData.add(new int[]{wallY, i}, iteration, count);
            count++;
        }

        // If part of the maze is blocked, remove the blocking wall.
        if (rSideCollide) {
            maze[wallY][maxX - 1] = PATH;
            generationData.remove(new int[]{wallY, maxX - 1});

        }
        if (lSideCollide){
            maze[wallY][minX + 1] = PATH;
            generationData.remove(new int[]{wallY, minX + 1});
        }

        // If there was no blocking, add the door
        if (!(lSideCollide || rSideCollide)) {
            int doorX = rand.nextInt(minX + 1, maxX);
            maze[wallY][doorX] = PATH;
            generationData.remove(new int[]{wallY, doorX});

        }

        // Recursively call for the

        //bottom partition
        make(minY, wallY, minX, maxX, iteration);

        // top partition
        make(wallY, maxY, minX, maxX, iteration);
    }

    /**
     * Draws a line vertically through the current partition of the maze. Chooses
     * randomly the column for the wall and the row for the door. Verifies that the new
     * wall will not close a section of the maze off from the main maze by checking if
     * it will intersect the indices of another door.
     *
     * @param minY - Of maze partition, row value closest to 0.
     * @param maxY - Of maze partition, row value closest to maze height
     * @param minX - Of maze partition, column value closest to 0.
     * @param maxX - Of maze partition, column value closest to width.
     */

    private void splitVertical(int minY, int maxY, int minX, int maxX, int iteration) {

        // Maintains at least one segment of PATH between walls.
        int wallX = rand.nextInt(minX + 2, maxX - 1);

        // If the row bounds of the wall are a PATH, then the wall will be blocking off
        // part of the maze.
        boolean upperCollide = maze[minY][wallX] == PATH;
        boolean lowerCollide = maze[maxY][wallX] == PATH;

        // Add wall to maze.
        int count = 0;
        for (int i = minY + 1; i < maxY; i++) {
            maze[i][wallX] = WALL;
            generationData.add(new int[]{i, wallX}, iteration, count);
            count++;
        }

        // If part of the maze was blocked off, remove the blocking wall.
        if (lowerCollide) {
            maze[maxY - 1][wallX] = PATH;
            generationData.remove(new int[]{maxY - 1, wallX});
        }
        if (upperCollide){
            maze[minY + 1][wallX] = PATH;
            generationData.remove(new int[]{minY + 1, wallX});
        }

        // If no block occurred, add the door to the wall.
        if (!(upperCollide || lowerCollide)) {
            int doorY = rand.nextInt(minY + 1, maxY);
            maze[doorY][wallX] = PATH;
            generationData.remove(new int[]{doorY, wallX});
        }

        // Recursively call for the

        // left partition
        make(minY, maxY, minX, wallX, iteration);

        // top partition
        make(minY, maxY, wallX, maxX, iteration);
    }

    public GenerationData getGenerationData() {

        return generationData;
    }

    /**
     * Return the height of the generated maze.
     * @return height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Return the width of the generated maze.
     * @return width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Creates a wall around the perimeter of the maze (along the 0 indices and
     * height/width indices).
     */
    private void makePerimeter()
    {
        for (int i = 0; i < height - 1; i++) {

            for (int j = 0; j < width - 1; j++) {
                maze[i][j] = PATH;
            }
        }

        for (int i = 0; i < width; i++) {
            maze[0][i] = WALL;
            maze[height - 1][i] = WALL;
        }

        for (int i = 0; i < height; i++) {
            maze[i][0] = WALL;
            maze[i][width - 1] = WALL;
        }
    }

    public static void main(String[] args)
    {
        MazeGenerator generator = new MazeGenerator(10, 10);
        char[][] arr = generator.getMaze();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j]);
            }
        }
    }
}
