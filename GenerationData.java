package edu.bloomu.homework.projectfive;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.concurrent.ThreadLocalRandom;

public class GenerationData
{
    private int size;

    private final ArrayList<ArrayList<Square>> finalSort = new ArrayList<>();
    private final ArrayList<Square> walls = new ArrayList<>();

    private final int[] start;
    private final int[] end;


    public GenerationData() {
        size = 0;
        start = new int[2];
        end = new int[2];
    }

    public void add(int[] square, int iteration, int count) {

        walls.add(new Square(square, iteration, count));

    }

    public void setStart(int[] startCoordinates) {
        start[0] = startCoordinates[0];
        start[1] = startCoordinates[1];
    }

    public void setEnd(int[] endCoordinates) {
        end[0] = endCoordinates[0];
        end[1] = endCoordinates[1];
    }


    public ArrayList<Square> getWalls() {
        return walls;
    }

    /**
     * Once all maze data has been added, order by iteration and count (in that order).
     * Then place into output ArrayList with matching squares together.
     */

    public void collate() {
        walls.sort(new Comparator<Square>()
        {
            @Override
            public int compare(Square o1, Square o2)
            {
                if (o1.getIteration() == o2.getIteration()) {
                    return o1.getCount() - o2.getCount();
                } else {
                    return o1.getIteration() - o2.getIteration();
                }
            }
        });


        int i = 0;

        while (i < walls.size()) {

            ArrayList<Square> section = new ArrayList<>();

            do
            {

                Square current = walls.get(i);
                section.add(current);

                i++;


            } while (i < walls.size() && walls.get(i - 1).equals(walls.get(i)));

            size++;
            finalSort.add(section);
        }
    }

    public ArrayList<ArrayList<Square>> getFinalSort() {
        return finalSort;
    }

    public void remove(int[] arr)
    {
        for (int i = 0; i < walls.size(); i++)
        {
            if ((arr[0] == walls.get(i).getGridLocation()[0]) && (
                    arr[1] == walls.get(i).getGridLocation()[1]))
            {
                {
                    walls.remove(i);
                    break;
                }
            }
        }
    }

    public ArrayList<Square> get(int i) {
        return finalSort.get(i);
    }

    public int[] getStart()
    {
        return start;
    }

    public int[] getEnd()
    {
        return end;
    }

    public int getSize()
    {
        return size;
    }

    public static void main(String[] args)
    {

        GenerationData data = new GenerationData();

        ThreadLocalRandom random = ThreadLocalRandom.current();


        for (int i = 0; i < 50; i++) {

                int[] arr = new int[]{i, i};
                int iteration = random.nextInt(5);
                int count = random.nextInt(5);

                data.add(arr, iteration, count);

            }


        ArrayList<Square> squares = data.getWalls();

        for (int i = 0; i < squares.size(); i++) {
            Square square = squares.get(i);
            System.out.println("Iteration: " + square.getIteration() + ", Count: " + square.getCount());
        }
        System.out.println();
        System.out.println("**************");

        data.collate();

        ArrayList<ArrayList<Square>> finalSort = data.finalSort;

        for (int i = 0; i < data.getSize(); i++) {

            ArrayList<Square> round = finalSort.get(i);

            for (int j = 0; j < round.size(); j++) {
                Square square = round.get(j);
                System.out.println("iteration: " + square.getIteration() + ", " +
                        "Count: " + square.getCount() + " \n");
            }
            System.out.println();
        }




    }
}
