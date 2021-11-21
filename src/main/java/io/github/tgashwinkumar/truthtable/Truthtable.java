package io.github.tgashwinkumar.truthtable;

import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import io.github.tgashwinkumar.binaryExp.BinaryNode;
import io.github.tgashwinkumar.booleanUtils.BooleanUtils;
import io.github.tgashwinkumar.definitions.InputArray;
import io.github.tgashwinkumar.lexer.Lexer;
import io.github.tgashwinkumar.parser.Parser;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Truthtable {

    private InputArray inputArray;
    private String expressionString;

    private int inputLen;
    public int tableLength;
    public HashMap<Integer, Integer> tableMap = new HashMap<>();

    public Truthtable(InputArray inputArr, String exprStr){
        this.inputArray = inputArr;
        this.expressionString = exprStr;
        this.inputLen = this.inputArray.array.length;
        this.tableLength = (int)Math.pow(2, inputLen);
        this.evaluateTable();
    }

    private void evaluateTable(){
        Lexer lexer = new Lexer(this.inputArray, this.expressionString);
        BinaryNode[] tokens = lexer.getTokens();
        Parser pars = new Parser(tokens);
        BinaryNode finalNode = pars.getFinalTree();
        System.out.println(finalNode);
        for(int i = 0; i < tableLength; i++){
            this.tableMap.put(i, finalNode.getBooleanValue(i, inputArray));
        }
    }

    public void printTable(){
        System.out.println("Y = " + this.expressionString);
        for(int i = 0; i < this.tableLength; i++){
            System.out.println(i + " = " + this.tableMap.get(i));
        }
    }

    public void showTable(){
        String[][] data = new String[(int)this.tableLength][this.inputLen+1];
        String[] columns = new String[this.inputLen+1];
        for(int i = 0; i < this.inputLen; i++){
            columns[i] = Character.toString(this.inputArray.array[i]);
        }
        columns[inputLen] = "Output";
        for(int i = 0; i < tableLength; i++){
            int[] inputComb = BooleanUtils.intToBinaryArray(i, inputLen);
            for(int j = 0; j < inputLen; j++){
                data[i][j] = Integer.toString(inputComb[j]);
                // System.out.print(inputComb[j] + "\t");
            }
            data[i][inputLen] = Integer.toString(this.tableMap.get(i));
            // System.out.print(this.tableMap.get(i));
        }

        JFrame fJFrame = new JFrame();
        JTable jTable = new JTable(data, columns);
        jTable.setBounds(30,40,100,tableLength*50);
        JScrollPane sp = new JScrollPane(jTable);
        fJFrame.add(sp);
        fJFrame.setSize(400, tableLength*50);
        fJFrame.setTitle(this.expressionString);
        fJFrame.setVisible(true);
        fJFrame.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });
    }

}
