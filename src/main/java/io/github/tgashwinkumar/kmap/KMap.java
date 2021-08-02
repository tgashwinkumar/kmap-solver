package io.github.tgashwinkumar.kmap;

import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import io.github.tgashwinkumar.binaryExp.BinaryNode;
import io.github.tgashwinkumar.definitions.InputArray;
import io.github.tgashwinkumar.errors.InputLimitExceeded;
import io.github.tgashwinkumar.lexer.Lexer;
import io.github.tgashwinkumar.parser.Parser;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class KMap {

    private InputArray inputArray;
    private String expressionString;
    private int inputLen;
    private int combLen;
    public HashMap<Integer, Integer> tableMap = new HashMap<>();

    private String[] grayCodeArr = {"00", "01", "11", "10"};
    private int rowNo;
    private int colNo;

    public KMap(InputArray inputArr, String exprString){
        this.inputArray = inputArr;
        this.expressionString = exprString;
        this.inputLen = this.inputArray.array.length;
        this.combLen = (int) Math.pow(2, inputLen);

        try {
            this.checkInputs();
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.evaluateKMap();
    }

    private void checkInputs() throws InputLimitExceeded{
        if(this.inputLen > 4){
            throw new InputLimitExceeded();
        }
    }

    private void evaluateKMap() {
        Lexer lexer = new Lexer(this.inputArray, this.expressionString);
        BinaryNode[] tokens = lexer.getTokens();
        Parser pars = new Parser(tokens);
        BinaryNode finalNode = pars.getFinalTree();
        System.out.println(finalNode);
        for (int i = 0; i < combLen; i++) {
            this.tableMap.put(i, finalNode.getBooleanValue(i, inputArray));
        }
        switch (this.inputLen) {
            case 1:
                this.rowNo = 1;
                this.colNo = 2;
                break;
            case 2:
                this.rowNo = 2;
                this.colNo = 2;
                break;
            case 3:
                this.rowNo = 2;
                this.colNo = 4;
                break;
            case 4:
                this.rowNo = 4;
                this.colNo = 4;
                break;
            default:
                break;
        }
    }

    private int getGrayCode(int num){
        if(num == 3) num = 2;
        if(num == 2) num = 3;
        return num;
    }

    public void showKMap(){
        String[][] data = new String[this.rowNo][this.colNo+1];
        String[] columnHeaders = new String[this.colNo+1];
        columnHeaders[0]= "";

        for(int i = 1; i < this.colNo+1; i++){
            columnHeaders[i] = grayCodeArr[i-1];
        }

        for(int i = 0; i < this.rowNo; i++){
            data[i][0] = grayCodeArr[i]; 
        }

        for(int r = 0; r < this.rowNo; r++){
            for(int c = 0; c < this.colNo; c++){
                int rgray = this.getGrayCode(r);
                int cgray = this.getGrayCode(c);
                if(this.colNo == 2){
                    data[r][c + 1] = Integer.toString(this.tableMap.get(rgray * 2 + cgray));
                }else if(this.colNo == 4){
                    data[r][c + 1] = Integer.toString(this.tableMap.get(rgray * 4 + cgray));
                }
            }
        }
        
        JFrame fJFrame = new JFrame();
        JTable jTable = new JTable(data, columnHeaders);
        jTable.setBounds(30, 40, 100, combLen * 50);
        JScrollPane sp = new JScrollPane(jTable);
        fJFrame.add(sp);
        fJFrame.setSize(400, combLen * 50);
        fJFrame.setTitle(this.expressionString);
        fJFrame.setVisible(true);
        fJFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

}

