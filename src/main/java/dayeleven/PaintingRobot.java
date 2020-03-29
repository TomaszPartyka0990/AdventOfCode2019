package dayeleven;

import java.util.ArrayList;
import java.util.List;

public class PaintingRobot {
    private int xPosition;
    private int yPosition;
    private RobotDirection currentDirection;
    private List<Panel> grid;
    private Panel tmpPanel;

    public PaintingRobot() {
        this.xPosition = 0;
        this.yPosition = 0;
        currentDirection = RobotDirection.RIGHT;
        grid = new ArrayList<>();
        grid.add(new Panel(0,0));
        grid.get(0).setColor(1);
        tmpPanel = new Panel(0,0);
    }

    void moveTheRobot (int rotation) throws IllegalArgumentException{
        int value = currentDirection.getValue();
        if (rotation == 0){
            value-=1;
        } else if (rotation == 1){
            value+=1;
        } else {
            throw new IllegalArgumentException("Rotation parameter can only tak value 0 or 1");
        }
        if (value > 4){
            value = 1;
        }
        if (value < 1){
            value = 4;
        }
        switch (value){
            case 1:
                currentDirection = RobotDirection.RIGHT;
                break;
            case 2:
                currentDirection = RobotDirection.DOWN;
                break;
            case 3:
                currentDirection = RobotDirection.LEFT;
                break;
            case 4:
                currentDirection = RobotDirection.UP;
                break;
            default:
                break;
        }
        switch (currentDirection){
            case UP:
                yPosition+=1;
                break;
            case LEFT:
                xPosition-=1;
                break;
            case DOWN:
                yPosition-=1;
                break;
            case RIGHT:
                xPosition+=1;
                break;
        }
    }

    void paintThePanel (int color){
        tmpPanel.setX(xPosition);
        tmpPanel.setY(yPosition);
        int index = grid.indexOf(tmpPanel);
        Panel panelFromGrid = grid.get(index);
        if (color == 0){
            panelFromGrid.setColor(0);
            panelFromGrid.incrementPaintingCounter();
        }
        if (color == 1){
            panelFromGrid.setColor(1);
            panelFromGrid.incrementPaintingCounter();
        }
    }

    Panel getThePanelRobotIsOnOrAddNew(){
        tmpPanel.setX(xPosition);
        tmpPanel.setY(yPosition);
        if (grid.contains(tmpPanel)){
            return grid.get(grid.indexOf(tmpPanel));
        } else {
            grid.add(new Panel(xPosition, yPosition));
            return tmpPanel;
        }
    }

    void printGrid(){
        grid.sort((p1, p2) -> {
            if (p1.getY()>p2.getY()){
                return -1;
            }
            if (p1.getY()<p2.getY()){
                return 1;
            }
            if (p1.getX()<p2.getX()){
                return -1;
            }
            if (p1.getX()>p2.getX()){
                return 1;
            }
            return 0;
        });
        StringBuilder gridToBePrinted = new StringBuilder();
        int y = grid.get(0).getY();
        for (Panel panel:grid){
            if (panel.getY() != y){
                y = panel.getY();
                gridToBePrinted.append("\r\n");
            }
            if (panel.getColor()==0){
                gridToBePrinted.append(" ");
            }
            if (panel.getColor()==1){
                gridToBePrinted.append("#");
            }
        }
        System.out.println(gridToBePrinted.toString());
    }

    int countPaintedPanels(){
        int counter = 0;
        for(Panel panel:grid){
            if(panel.getPaintingsCounter() > 0){
                counter++;
            }
        }
        return counter;
    }

    int getColorOfPanelTheRobotIsOn(){
        return getThePanelRobotIsOnOrAddNew().getColor();
    }


    public List<Panel> getGrid() {
        return grid;
    }
}
