package daythree;

import java.util.Iterator;

class CoordinatesComparer {
    private CoordinatesHandler wireOne;
    private CoordinatesHandler wireTwo;

    CoordinatesComparer(CoordinatesHandler wireOne, CoordinatesHandler wireTwo) {
        this.wireOne = wireOne;
        this.wireTwo = wireTwo;
    }

    int checkForLowestDistance(){
        int distanceFromStart = 0;
        int lowestDistanceFromstart = 0;
        int positiveX = 0;
        int positiveY = 0;
        for (Coordiantes coordinate : wireTwo.getWirePositions()) {
            if (wireOne.checkForCoordinates(coordinate)) {
                if (coordinate.getX() < 0) {
                    positiveX = coordinate.getX() * -1;
                } else {
                    positiveX = coordinate.getX();
                }
                if (coordinate.getY() < 0) {
                    positiveY = coordinate.getY() * -1;
                } else {
                    positiveY = coordinate.getY();
                }
                distanceFromStart = positiveX + positiveY;
                if (lowestDistanceFromstart == 0 || distanceFromStart < lowestDistanceFromstart) {
                    lowestDistanceFromstart = distanceFromStart;
                }
            }
        }
        return  lowestDistanceFromstart;
    }

    int checkForLowestStepsTaken(){
        int stepsTakenFromBeggining = 0;
        int lowestStepsTakenFromBeggining = 0;
        for (Coordiantes coordinate : wireTwo.getWirePositions()) {
            if (wireOne.checkForCoordinates(coordinate)) {
                stepsTakenFromBeggining = wireOne.checkForCoordinatesAndReturnThem(coordinate).getStepsTaken()
                        + coordinate.getStepsTaken();
                if (lowestStepsTakenFromBeggining == 0 || stepsTakenFromBeggining < lowestStepsTakenFromBeggining) {
                    lowestStepsTakenFromBeggining = stepsTakenFromBeggining;
                }
            }
        }
        return lowestStepsTakenFromBeggining;
    }
}
