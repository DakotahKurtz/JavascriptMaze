package edu.bloomu.homework.projectfive;

/**
 * This class uses recursive backtracking to find all possible solutions to a
 * maze with the following form:
 *
 * A char[][] with:
 * 1. Starting position marked by 'b.
 * 2. Exit marked with 'e'.
 * 3. Barriers marked with '1'.
 * 4. Open path marked with '0'.
 *
 * This class keeps track of the number of solutions to the maze and one of the paths
 * with the lowest number of steps from beginning to end.
 *
 * @author Dakotah Kurtz
 */
public class MazeSolver
{
    private final char[][] maze;
    private final char[][] fastestPath;
    private final char[][] firstPath;
    private final int height;
    private final int width;

    private final static char PATH = '-';
    private final static char WALL = '1';
    private final static char ENTRANCE = 'b';
    private final static char EXIT = 'e';

    private int solutionCount;
    private int fastestPathCount;

    /**
     * Constructor for MazeSolver class. Initializes variables.
     *
     * @param maze to be solved.
     * @param height of maze.
     * @param width of maze.
     */

    public MazeSolver(char[][] maze, int height, int width)
    {
        this.maze = maze;
        this.height = height;
        this.width = width;
        solutionCount = 0;
        fastestPath = new char[height][width];
        firstPath = new char[height][width];
        fastestPathCount = Integer.MAX_VALUE;
    }

    /**
     * Public method to solve the maze. Finds the starting position marked by 'b', and
     * then feeds it into the recursive function.
     */

    public void solve()
    {
        int[] start = findStart();

        // If the char[][] maze doesn't include a start position marked 'b', end the
        // process.
        if (start != null)
        {
            solveMaze(start, 0);
        }
        else
        {
            System.out.println("Maze must include a starting position marked with 'b'");
        }
    }

    /**
     * Helper method to find the [x,y] position of the starting location.
     * @return an int[2] containing the [x,y] location of the starting location 'b'.
     */
    private int[] findStart()
    {
        for (int i = 0; i < height; i++)
        {
            for (int j = 0; j < width; j++)
            {
                if (maze[i][j] == ENTRANCE)
                {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    /**
     * Private method used to solve maze using recursion with backtracking. Keeps track
     * of the number of solutions, and the fastest path found, and the first path found.
     *
     * @param location - of current position
     * @param currentPathCount - number of steps taken so far.
     */
    private void solveMaze(int[] location, int currentPathCount)
    {
        int y = location[0];
        int x = location[1];

        if (maze[y][x] == EXIT) { // maze exit found.
            solutionCount++;

            // checks to see if this is the first solution found.
            if (solutionCount == 1)
            {
                for (int i = 0; i < height; i++)
                {
                    for (int j = 0; j < width; j++)
                    {
                       firstPath[i][j] = maze[i][j]; // if it is, copy it.
                    }
                }
            }
            // checks to see if this is the fastest path so far.
            if (fastestPathCount > currentPathCount)
            {
                fastestPathCount = currentPathCount;
                for (int i = 0; i < height; i++)
                {
                    for (int j = 0; j < width; j++)
                    {
                        fastestPath[i][j] = maze[i][j]; // if it is, copy it.
                    }
                }
            }
            return;
        }

        // if location is on the wall or is on a location previously visited, path ends.
        if (maze[y][x] == WALL || maze[y][x] == 'p')
        {
            return;
        }

        // mark path and iterate counter.
        currentPathCount++;
        maze[y][x] = 'p';

        // try to go right
        if ((x + 1) < width)
        {
            solveMaze(new int[]{y, x + 1}, currentPathCount);
        }

        // try to go left
        if ((x - 1) >= 0)
        {
            solveMaze(new int[]{y, x - 1}, currentPathCount);
        }

        // try to go down
        if ((y + 1) < height)
        {
            solveMaze(new int[]{y + 1, x}, currentPathCount);
        }

        // try to go up
        if ((y - 1) >= 0)
        {
            solveMaze(new int[]{y - 1, x}, currentPathCount);
        }

        // if the path ends in a dead end, back up until previously
        maze[y][x] = PATH;
    }

    /**
     * Method to print mazes to console.
     */

    public void printMaze(char[][] mazeToPrint)
    {
        System.out.println();
        for (int i = 0; i < height; i++)
        {
            for (int j = 0; j < width; j++)
            {
                System.out.print(mazeToPrint[i][j] + " ");
            }
            System.out.print("\n");
        }
        System.out.println();
    }

    /**
     * Returns the fastest path count.
     * @return the fastest path count.
     */

    public int getFastestPathCount()
    {
        return fastestPathCount;
    }

    /**
     * Returns the number of solutions.
     * @return the number of solutions.
     */
    public int getSolutionCount() {
        return solutionCount;
    }

    /**
     * Returns the fastest path.
     * @return the fastest path
     */

    public char[][] getFastestSolution() {
        return fastestPath;
    }

    /**
     * Returns the first found solution.
     * @return the first found solution
     */
    public char[][] getFirstSolution() {
        return firstPath;
    }
}
