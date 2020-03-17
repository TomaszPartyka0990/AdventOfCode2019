package dayeleven;

//Class that contains one Panel of the ship's grid
public class Panel {
    private int x;
    private int y;
    private int color;
    private int paintingsCounter;

    public Panel(int x, int y) {
        this.x = x;
        this.y = y;
        color = 0;
        paintingsCounter = 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Panel panel = (Panel) o;

        if (x != panel.x) return false;
        return y == panel.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public String toString() {
        return "Panel{" +
                "x=" + x +
                ", y=" + y +
                ", color=" + color +
                ", paintingsCounter=" + paintingsCounter +
                '}';
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    void incrementPaintingCounter(){
        paintingsCounter++;
    }

    public int getPaintingsCounter() {
        return paintingsCounter;
    }
}
