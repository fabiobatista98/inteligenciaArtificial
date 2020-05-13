package warehouse;

import ga.Problem;

import java.util.ArrayList;
import java.util.LinkedList;

public class WarehouseProblemForGA implements Problem<WarehouseIndividual> {

    //TODO this class might require the definition of additional methods and/or attributes
    //generic al

    private LinkedList<Cell> shelves;
    private Cell cellAgent;
    private Cell exit;
    private ArrayList<Request> requests;
    private int numProducts;
    private LinkedList<Pair> pairs;

    public WarehouseProblemForGA(WarehouseAgentSearch agentSearch) {
        //TODO
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public WarehouseIndividual getNewIndividual() {
        //TODO
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public LinkedList<Cell> getShelves() {
        return shelves;
    }

    public Cell getCellAgent() {
        return cellAgent;
    }

    public Cell getExit() {
        return exit;
    }

    public ArrayList<Request> getRequests() {
        return requests;
    }

    public int getNumProducts() {
        return numProducts;
    }

    public LinkedList<Pair> getPairs() {
        return pairs;
    }
}
