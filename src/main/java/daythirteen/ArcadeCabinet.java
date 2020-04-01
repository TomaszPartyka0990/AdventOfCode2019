package daythirteen;

import java.util.ArrayList;
import java.util.List;

public class ArcadeCabinet {
    private List<Panel> grid;
    private Intcode intcode;
    private Panel tmpPanel;

    public ArcadeCabinet(Intcode intcode) {
        this.grid = new ArrayList<>();
        this.intcode = intcode;
        this.tmpPanel = new Panel(0, 0 ,0);
    }

    public void makeIntCodeCicle(){
        intcode.runIntcode();
        tmpPanel.setX(intcode.getIntcodeOutput());
        intcode.runIntcode();
        tmpPanel.setY(intcode.getIntcodeOutput());
        intcode.runIntcode();
        tmpPanel.setValue(intcode.getIntcodeOutput());
        if (grid.contains(tmpPanel)){
            grid.get(grid.indexOf(tmpPanel)).setValue(tmpPanel.getValue());
        } else {
            grid.add(new Panel(tmpPanel.getX(), tmpPanel.getY(), tmpPanel.getValue()));
        }
    }

    public List<Panel> getGrid() {
        return grid;
    }

    public void playGame(){
        while (!intcode.isIntcodeIsFinished()){
            makeIntCodeCicle();
        }
    }

    public int coutBlockTiles(){
        int counter = 0;
        for (Panel panel:grid){
            if (panel.getValue()==2){
                counter++;
            }
        }
        return counter;
    }
}
