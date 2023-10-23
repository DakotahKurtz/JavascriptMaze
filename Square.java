package edu.bloomu.homework.projectfive;

public class Square implements Comparable<Square>
{
    private final int[] gridLocation;
    private final int iteration;
    private final int count;


    public Square(int[] gridLocation, int iteration, int count) {
        this.gridLocation = gridLocation;
        this.iteration = iteration;
        this.count = count;

    }


        public int[] getGridLocation()
    {
        return gridLocation;
    }

    public int getCount()
    {
        return count;
    }

    public int getIteration()
    {
        return iteration;
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == this) {
            return true;
        }

        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }

        Square square = (Square) o;
        return (this.iteration == square.iteration && this.count == square.count) && this.gridLocation[0] == square.gridLocation[0] && square.gridLocation[1] == this.gridLocation[1];
    }



    @Override
    public int compareTo(Square o)
    {
        if (this.iteration == o.iteration) {
            return this.count - o.count;
        }
        else {
            return this.iteration - o.iteration;
        }
    }
}
