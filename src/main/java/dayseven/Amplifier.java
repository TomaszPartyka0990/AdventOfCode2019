package dayseven;

import static dayseven.Main.*;

public class Amplifier {
    private String phaseSetting;
    private String input;
    private String[] table;
    private boolean firstInput;
    private int actualPosition;

    public Amplifier(String[] table, String phaseSetting){
        this.table = table.clone();
        this.phaseSetting = phaseSetting;
        this.firstInput = true;
        this.actualPosition = 0;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public void intcode (){
        int firstNumber = 0;
        int secondNumber = 0;
        int resultPosition = 0;
        String result;
        String opcode = "";
        String parameterOneMode="";
        String parameterTwoMode="";
        do {
            if (table[actualPosition].length() > 1){
                switch (table[actualPosition].length()){
                    case 2:
                        if ("99".equals(table[actualPosition])){
                            opcode = "99";
                        } else {
                            opcode = table[actualPosition].charAt(1) + "";
                            parameterOneMode = "0";
                            parameterTwoMode = "0";
                        }
                        break;
                    case 3:
                        opcode = table[actualPosition].charAt(2)+"";
                        parameterOneMode = table[actualPosition].charAt(0)+"";
                        parameterTwoMode = "0";
                        break;
                    case 4:
                        opcode = table[actualPosition].charAt(3)+"";
                        parameterOneMode = table[actualPosition].charAt(1)+"";
                        parameterTwoMode = table[actualPosition].charAt(0)+"";
                        break;
                    case 5:
                        opcode = table[actualPosition].charAt(4)+"";
                        parameterOneMode = table[actualPosition].charAt(2)+"";
                        parameterTwoMode = table[actualPosition].charAt(1)+"";
                        break;
                }
            } else {
                opcode = table[actualPosition];
                parameterOneMode = "0";
                parameterTwoMode = "0";
            }
            if ("1".equals(opcode)||"2".equals(opcode)||"5".equals(opcode)||"6".equals(opcode)
                    ||"7".equals(opcode)||"8".equals(opcode)) {
                if ("0".equals(parameterOneMode)){
                    firstNumber = Integer.parseInt(table[Integer.parseInt(table[actualPosition + 1])]);
                } else {
                    firstNumber = Integer.parseInt(table[actualPosition + 1]);
                }
                if ("0".equals(parameterTwoMode)) {
                    secondNumber = Integer.parseInt(table[Integer.parseInt(table[actualPosition + 2])]);
                } else {
                    secondNumber = Integer.parseInt(table[actualPosition + 2]);
                }
                resultPosition = Integer.parseInt(table[actualPosition+3]);
            }
            if ("1".equals(opcode)){

                result = firstNumber + secondNumber + "";
                table[resultPosition] = result;
                actualPosition += 4;
            }
            if ("2".equals(opcode)){
                result = firstNumber * secondNumber + "";
                table[resultPosition] = result;
                actualPosition += 4;
            }
            if ("3".equals(opcode)){
                resultPosition = Integer.parseInt(table[actualPosition+1]);
                if (firstInput) {
                    result = phaseSetting;
                    firstInput = false;
                } else {
                    result = input;
                }
                table[resultPosition] = result;
                actualPosition += 2;
            }
            if ("4".equals(opcode)){
                if ("0".equals(parameterOneMode)) {
                    result = table[Integer.parseInt(table[actualPosition + 1])];
                } else {
                    result = table[actualPosition + 1];
                }
                output = result;
                actualPosition += 2;
                return;
            }
            if ("5".equals(opcode)){
                if (firstNumber!=0){
                    actualPosition = secondNumber;
                } else {
                    actualPosition += 3;
                }
            }
            if ("6".equals(opcode)){
                if (firstNumber==0){
                    actualPosition = secondNumber;
                } else {
                    actualPosition += 3;
                }
            }
            if ("7".equals(opcode)){
                if (firstNumber<secondNumber){
                    result = "1";
                } else {
                    result = "0";
                }
                table[resultPosition] = result;
                actualPosition += 4;
            }
            if ("8".equals(opcode)) {
                if (firstNumber == secondNumber) {
                    result = "1";
                } else {
                    result = "0";
                }
                table[resultPosition] = result;
                actualPosition += 4;
            }
        } while (!"99".equals(opcode));
        isFinished = true;
    }
}
