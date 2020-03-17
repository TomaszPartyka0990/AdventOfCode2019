package dayeleven;

public enum RobotDirection {
    UP(4),
    DOWN(2),
    LEFT(3),
    RIGHT(1);

    private int value;

    RobotDirection(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
