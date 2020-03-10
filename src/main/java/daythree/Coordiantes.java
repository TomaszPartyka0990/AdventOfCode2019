package daythree;

public class Coordiantes {
    private int x;
    private int y;
    private int stepsTaken;

    Coordiantes(int x, int y, int stepsTaken) {
        this.x = x;
        this.y = y;
        this.stepsTaken = stepsTaken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordiantes that = (Coordiantes) o;

        if (x != that.x) return false;
        return y == that.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    int getStepsTaken() {
        return stepsTaken;
    }

    @Override
    public String toString() {
        return "Coordiantes{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
