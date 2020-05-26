package warehouse;

import agentSearch.Action;
import agentSearch.State;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class WarehouseState extends State implements Cloneable {

    private int[][] matrix;
    private int lineAgent, columnAgent;
    private int lineExit, columnExit;
    private int steps;

    public WarehouseState(int[][] matrix) {
        this.matrix = new int[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                this.matrix[i][j] = matrix[i][j];
                if (this.matrix[i][j] == 1) {
                    lineExit = i;
                    columnExit = j;
                    lineAgent = i;
                    columnAgent = j;
                }
            }
        }
    }

    public WarehouseState(int[][] matrix, int lineAgent, int columnAgent, int lineExit, int columnExit) {
        this.matrix = new int[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }
        this.lineAgent = lineAgent;
        this.columnAgent = columnAgent;
        this.lineExit = lineExit;
        this.columnExit = columnExit;
    }

    public void executeAction(Action action) {
        action.execute(this);
        steps++;
    }

    public void executeActionSimulation(Action action) {
        action.execute(this);
        steps++;
        fireUpdatedEnvironment();
    }

    public boolean canMoveUp() {
        return lineAgent != 0 && matrix[lineAgent - 1][columnAgent] != Properties.SHELF;
    }

    public boolean canMoveRight() {
        return columnAgent!=matrix.length-1 && matrix[lineAgent][columnAgent + 1] != Properties.SHELF;
    }

    public boolean canMoveDown() {
        return lineAgent!=matrix.length-1 && matrix[lineAgent + 1][columnAgent] != Properties.SHELF;
    }

    public boolean canMoveLeft() {
        return columnAgent != 0 && matrix[lineAgent][columnAgent - 1] != Properties.SHELF;
    }

    public void moveUp() {
        matrix[lineAgent][columnAgent] = Properties.EMPTY;
        matrix[--lineAgent][columnAgent] = Properties.AGENT;
    }

    public void moveRight() {
        matrix[lineAgent][columnAgent] = Properties.EMPTY;
        matrix[lineAgent][++columnAgent] = Properties.AGENT;
    }

    public void moveDown() {
        matrix[lineAgent][columnAgent] = Properties.EMPTY;
        matrix[++lineAgent][columnAgent] = Properties.AGENT;
    }

    public void moveLeft() {
        matrix[lineAgent][columnAgent] = Properties.EMPTY;
        matrix[lineAgent][--columnAgent] = Properties.AGENT;
    }

    public void setCellAgent(int line, int column) {
        matrix[lineAgent][columnAgent] = Properties.EMPTY;
        lineAgent = line;
        columnAgent = column;
        matrix[lineAgent][columnAgent] = Properties.AGENT;

    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public int getSize() {
        return matrix.length;
    }

    public Color getCellColor(int line, int column) {
        if (line == lineExit && column == columnExit && (line != lineAgent || column != columnAgent))
            return Properties.COLOREXIT;

        switch (matrix[line][column]) {
            case Properties.AGENT:
                return Properties.COLORAGENT;
            case Properties.SHELF:
                return Properties.COLORSHELF;
            default:
                return Properties.COLOREMPTY;
        }
    }

    public int getLineAgent() {
        return lineAgent;
    }

    public int getColumnAgent() {
        return columnAgent;
    }

    public int getLineExit() {
        return lineExit;
    }

    public int getColumnExit() {
        return columnExit;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof WarehouseState)) {
            return false;
        }

        WarehouseState o = (WarehouseState) other;
        if (matrix.length != o.matrix.length) {
            return false;
        }

        return Arrays.deepEquals(matrix, o.matrix);
    }

    @Override
    public int hashCode() {
        return 97 * 7 + Arrays.deepHashCode(this.matrix);
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append(matrix.length);
        for (int i = 0; i < matrix.length; i++) {
            buffer.append('\n');
            for (int j = 0; j < matrix.length; j++) {
                buffer.append(matrix[i][j]);
                buffer.append(' ');
            }
        }
        return buffer.toString();
    }

    @Override
    public WarehouseState clone() {
        return new WarehouseState(matrix, lineAgent, columnAgent, lineExit, columnExit);
    }

    private final ArrayList<EnvironmentListener> listeners = new ArrayList<>();

    public synchronized void addEnvironmentListener(EnvironmentListener l) {
        if (!listeners.contains(l)) {
            listeners.add(l);
        }
    }

    public synchronized void removeEnvironmentListener(EnvironmentListener l) {
        listeners.remove(l);
    }

    public void fireUpdatedEnvironment() {
        for (EnvironmentListener listener : listeners) {
            listener.environmentUpdated();
        }
    }
}
