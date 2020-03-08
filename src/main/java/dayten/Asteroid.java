package dayten;

import java.util.*;

public class Asteroid {
    private Coordinate selfCoordinates;
    private Set<Float> angles;
    private Map<Float, List<Coordinate>> anglesWithAsteroidCoordinatesOnIt;

    public Asteroid(Coordinate selfCoordinates) {
        this.selfCoordinates = selfCoordinates;
        angles = new HashSet<>();
        anglesWithAsteroidCoordinatesOnIt = new TreeMap<>(Collections.reverseOrder());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Asteroid asteroid = (Asteroid) o;

        return selfCoordinates != null ? selfCoordinates.equals(asteroid.selfCoordinates) : asteroid.selfCoordinates == null;
    }

    @Override
    public int hashCode() {
        return selfCoordinates != null ? selfCoordinates.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Asteroid{" +
                "selfCoordinates=" + selfCoordinates +
                '}';
    }

    public Coordinate getSelfCoordinates() {
        return selfCoordinates;
    }

    public void addAngleToSet(Float angle) {
        angles.add(angle);
    }

    public void addAngleToMap(Float angle, Coordinate coordinate) {
        List<Coordinate> list = new LinkedList<>();
        list.add(coordinate);
        anglesWithAsteroidCoordinatesOnIt.put(angle, list);
    }

    public void addCoordinateToAngleExistingInMap(Float angle, Coordinate coordinate){
        anglesWithAsteroidCoordinatesOnIt.get(angle).add(coordinate);
    }

    public Set<Float> getAngles() {
        return angles;
    }

    public Map<Float, List<Coordinate>> getAnglesWithAsteroidCoordinatesOnIt() {
        return anglesWithAsteroidCoordinatesOnIt;
    }

    public float getAngle(Coordinate target) {
        float angle = (float) Math.toDegrees(
                                Math.atan2(this.getSelfCoordinates().getX() - target.getX()
                                        , this.getSelfCoordinates().getY() - target.getY()));

        if (angle <= 0) {
            angle += 360;
        }
        return angle;
    }

    public Coordinate vaporizeOtherAsteroidsAndReturnCoordinatesOf200thVaporized(){
        int counter = 0;
        Coordinate coordinatesToReturn = null;
        Set<Float> anglesToRemoveFromMap = new HashSet<>();
        while (anglesWithAsteroidCoordinatesOnIt.size()>0) {
            for (Float key:anglesWithAsteroidCoordinatesOnIt.keySet()) {
                counter++;
                List<Coordinate> coordinates = anglesWithAsteroidCoordinatesOnIt.get(key);
                if (counter==200){
                    coordinatesToReturn = coordinates.get(0);
                }
                if (coordinates.size()>1){
                    coordinates.remove(0);
                } else {
                    coordinates.remove(0);
                    anglesToRemoveFromMap.add(key);
                }
            }
            for (Float angle:anglesToRemoveFromMap){
                anglesWithAsteroidCoordinatesOnIt.remove(angle);
            }
            anglesToRemoveFromMap.clear();
        }
        return coordinatesToReturn;
    }
}
