package warehouse;

import ga.Problem;

import java.util.ArrayList;
import java.util.HashMap;
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
        this.shelves = agentSearch.getShelves();
        this.requests = agentSearch.getRequests();
        this.numProducts = agentSearch.getNumProducts();
        this.cellAgent = agentSearch.getCellAgent();
        this.exit = agentSearch.getExit();

        this.pairs = agentSearch.getPairs();
    }

    @Override
    public WarehouseIndividual getNewIndividual() {
        //TODO
        return new WarehouseIndividual(this, shelves.size());
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
