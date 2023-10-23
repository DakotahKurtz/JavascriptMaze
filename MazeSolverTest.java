package edu.bloomu.homework.projectfive;

/**
 * Class created to test MazeSolver class and methods on two different mazes.
 *
 * @author Dakotah Kurtz
 */
public class MazeSolverTest
{
    public static void main(String[] args)
    {
        ASCIIMazeGenerator generator = new ASCIIMazeGenerator(12, 30);
        char[][] maze = generator.getMaze();
        generator.printMaze();

        MazeSolver solver = new MazeSolver(maze, 12, 30);
        solver.solve();
        char[][] solution = solver.getFastestSolution();

        solver.printMaze(solution);

    }
}
