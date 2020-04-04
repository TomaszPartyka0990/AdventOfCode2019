package daythirteen;

import java.util.ArrayList;
import java.util.List;

public class ArcadeCabinet {
    private List<Panel> grid;
    private Intcode intcode;
    private Panel tmpPanel;
    private int paddleX;
    private int paddleY;
    private int ballX;
    private int ballY;
    private int score;

    public ArcadeCabinet(Intcode intcode) {
        this.grid = new ArrayList<>();
        this.intcode = intcode;
        this.tmpPanel = new Panel(0, 0 ,0);
    }

    private void makeIntCodePreparationForGameCicle(){
        intcode.runIntcode();
        tmpPanel.setX(intcode.getIntcodeOutput());
        intcode.runIntcode();
        tmpPanel.setY(intcode.getIntcodeOutput());
        intcode.runIntcode();
        tmpPanel.setValue(intcode.getIntcodeOutput());
        if (tmpPanel.getValue() == 3){
            paddleX = tmpPanel.getX();
            paddleY = tmpPanel.getY();
        }
        if (tmpPanel.getValue() == 4){
            ballX = tmpPanel.getX();
            ballY = tmpPanel.getY();
        }
        if (grid.contains(tmpPanel)) {
            grid.get(grid.indexOf(tmpPanel)).setValue(tmpPanel.getValue());
        } else {
            if (tmpPanel.getX()!=-1) {
                grid.add(new Panel(tmpPanel.getX(), tmpPanel.getY(), tmpPanel.getValue()));
            } else {
                score = tmpPanel.getValue();
            }
        }
    }

    public void playGame(){
        while (!intcode.isInputStarts()){
            makeIntCodePreparationForGameCicle();
        }
        System.out.println("Block tiles at start: " + coutBlockTiles());
        printGrid();
        while (!intcode.isIntcodeIsFinished()){
            if (paddleX > ballX) {
                intcode.setIntcodeInput("-1");
            } else if (paddleX < ballX) {
                intcode.setIntcodeInput("1");
            } else {
                intcode.setIntcodeInput("0");
            }
            makeIntCodePreparationForGameCicle();
            //printGrid();
        }
        System.out.println("FINAL SCORE: " + score);
    }

    private int coutBlockTiles(){
        int counter = 0;
        for (Panel panel:grid){
            if (panel.getValue()==2){
                counter++;
            }
        }
        return counter;
    }

    private void printGrid(){
        StringBuilder sb = new StringBuilder();
        int y = grid.get(0).getY();
        for (Panel panel:grid){
            if (panel.getY()!=y){
                y = panel.getY();
                sb.append("\r\n");
            }
            switch (panel.getValue()){
                case 0:
                    sb.append(" ");
                    break;
                case 1:
                    sb.append("W");
                    break;
                case 2:
                    sb.append("*");
                    break;
                case 3:
                    sb.append("-");
                    break;
                case 4:
                    sb.append("O");
                    break;
            }
        }
        System.out.println(sb.toString());
    }
}
