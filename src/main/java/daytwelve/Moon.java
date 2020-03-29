package daytwelve;

import java.util.Objects;

public class Moon {
    private int xPosition;
    private int yPosition;
    private int zPosition;
    private int xVelocity;
    private int yVelocity;
    private int zVelocity;
    private String name;
    private int potEnergy;
    private int kinenergy;
    private int totalEnergy;

    public Moon(int xPosition, int yPosition, int zPosition) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.zPosition = zPosition;
        xVelocity = 0;
        yVelocity = 0;
        zVelocity = 0;
        name = "";
        potEnergy = 0;
        kinenergy = 0;
        totalEnergy = 0;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getxPosition() {
        return xPosition;
    }

    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    public int getzPosition() {
        return zPosition;
    }

    public void setzPosition(int zPosition) {
        this.zPosition = zPosition;
    }

    public int getxVelocity() {
        return xVelocity;
    }

    public void setxVelocity(int xVelocity) {
        this.xVelocity = xVelocity;
    }

    public int getyVelocity() {
        return yVelocity;
    }

    public void setyVelocity(int yVelocity) {
        this.yVelocity = yVelocity;
    }

    public int getzVelocity() {
        return zVelocity;
    }

    public void setzVelocity(int zVelocity) {
        this.zVelocity = zVelocity;
    }

    public int getTotalEnergy() {
        return totalEnergy;
    }

    @Override
    public String toString() {
        return String.format("Moon{pos=<x=%s, y=%s, z=%s>, vel=<x=%s, y=%s, z=%s>}",
                xPosition, yPosition, zPosition, xVelocity, yVelocity, zVelocity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Moon moon = (Moon) o;

        return Objects.equals(name, moon.name);
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    public void calculateEnergies(){
        potEnergy = Math.abs(xPosition) + Math.abs(yPosition) + Math.abs(zPosition);
        kinenergy = Math.abs(xVelocity) + Math.abs(yVelocity) + Math.abs(zVelocity);
        totalEnergy = potEnergy * kinenergy;
    }
}
