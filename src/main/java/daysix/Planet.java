package daysix;

import java.util.ArrayList;
import java.util.List;

public class Planet {
    private String name;
    private int numberOfOrbits;
    private List<String> planetNamesThatThisPlanetOrbitsAround;

    Planet(String name){
        this.name = name;
        this.numberOfOrbits = 0;
        this.planetNamesThatThisPlanetOrbitsAround = new ArrayList<>();
    }

    String getName() {
        return name;
    }

    int getNumberOfOrbits() {
        return numberOfOrbits;
    }

    void setNumberOfOrbits(int numberOfOrbits) {
        this.numberOfOrbits = numberOfOrbits;
    }

    List<String> getPlanetNamesThatThisPlanetOrbitsAround() {
        return planetNamesThatThisPlanetOrbitsAround;
    }

    void addPlanetsToList(List<String> planets){
        planetNamesThatThisPlanetOrbitsAround.addAll(planets);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Planet planet = (Planet) o;

        return name.equals(planet.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder planets = new StringBuilder();
        for(String planet:planetNamesThatThisPlanetOrbitsAround){
            planets.append(planet).append(",");
        }
        return "Planet{" +
                "name='" + name + '\'' +
                ", numberOfOrbits=" + numberOfOrbits +
                ", planets=" + planets +
                '}';
    }
}
