package dayten;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SpaceMap {
    private int xSize;
    private List<String> inputData;
    private List<Asteroid> asteroids;

    public SpaceMap(List<String> inputData) {
        this.xSize = inputData.get(0).length();
        this.inputData = inputData;
        this.asteroids = new ArrayList<>();
    }

    public void getAsteroidLocations(){
        for (String inputLine:inputData){
            for (int i=0; i<xSize; i++){
                if (inputLine.charAt(i) == '#') {
                    asteroids.add(new Asteroid(new Coordinate(i, inputData.indexOf(inputLine))));
                }
            }
        }
    }

    public void fillCoordinatesListsForEachAsteroid(){
        for (Asteroid currentAsteroid:asteroids){
            for (Asteroid targetAsteroid:asteroids){
                if (!currentAsteroid.equals(targetAsteroid)){
                    float angle = currentAsteroid.getAngle(targetAsteroid.getSelfCoordinates());
                    if (currentAsteroid.getAngles().contains(angle)){
                        currentAsteroid.addCoordinateToAngleExistingInMap(angle
                                , targetAsteroid.getSelfCoordinates());
                    } else {
                        currentAsteroid.addAngleToSet(angle);
                        currentAsteroid.addAngleToMap(angle, targetAsteroid.getSelfCoordinates());
                    }
                }
            }
        }
    }

    public Asteroid getAsteroidWithMaxOthersDetected(){
        int maxAsteroidsDetected = 0;
        int asteroidIndex = 0;
        for (Asteroid asteroid:asteroids){
            if (asteroid.getAngles().size()>maxAsteroidsDetected){
                maxAsteroidsDetected = asteroid.getAngles().size();
                asteroidIndex = asteroids.indexOf(asteroid);
            }
        }
        return asteroids.get(asteroidIndex);
    }

    public void fillAsteroidCoordinatesListsForEachAsteroid(){
        for (Asteroid asteroid:asteroids){
            Map<Float, List<Coordinate>> map = asteroid.getAnglesWithAsteroidCoordinatesOnIt();
            map.forEach((angle, listOfCoordinates) -> {
                if (listOfCoordinates.size() > 1){
                    listOfCoordinates.sort((c1,c2) -> {
                        double distance1 =  Math.sqrt(
                                Math.pow(c1.getX()-asteroid.getSelfCoordinates().getX(),2)
                                        + Math.pow(c1.getY()-asteroid.getSelfCoordinates().getY(),2));
                        double distance2 =  Math.sqrt(
                                Math.pow(c2.getX()-asteroid.getSelfCoordinates().getX(),2)
                                        + Math.pow(c2.getY()-asteroid.getSelfCoordinates().getY(),2));
                        return (int) Math.round(distance1 - distance2);
                    });
                }
            });
        }
    }
}
