package daythree;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

class CoordinatesHandler {
    private Set<Coordiantes> wirePositions;
    private int stepsTaken;
    private int lastX;
    private int lastY;

    CoordinatesHandler() {
        this.wirePositions = new HashSet<>();
        this.lastX = 0;
        this.lastY = 0;
    }

    boolean checkForCoordinates(Coordiantes coordiantes){
        return wirePositions.contains(coordiantes);
    }

    Coordiantes checkForCoordinatesAndReturnThem(Coordiantes coordiantes) throws NoSuchElementException {
        for (Coordiantes data : wirePositions) {
            if (data.equals(coordiantes)) {
                return data;
            }
        }
        throw new NoSuchElementException();
    }

    Set<Coordiantes> getWirePositions() {
        return wirePositions;
    }

    void move(String whereToMove){
        char direction = whereToMove.charAt(0);
        int distance = Integer.parseInt(whereToMove.substring(1));
        switch (direction){
            case 'L':
                for (int i = 1; i <= distance; i++){
                    wirePositions.add(new Coordiantes(--lastX, lastY, ++stepsTaken));
                }
                break;
            case 'R':
                for (int i = 1; i <= distance; i++){
                    wirePositions.add(new Coordiantes(++lastX, lastY, ++stepsTaken));
                }
                break;
            case 'U':
                for (int i = 1; i <= distance; i++){
                    wirePositions.add(new Coordiantes(lastX, ++lastY, ++stepsTaken));
                }
                break;
            case 'D':
                for (int i = 1; i <= distance; i++){
                    wirePositions.add(new Coordiantes(lastX, --lastY, ++stepsTaken));
                }
                break;
        }
    }
}
