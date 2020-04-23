package warehouse;

import agentSearch.Agent;
import agentSearch.State;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class WarehouseAgentSearch<S extends State> extends Agent<S> {

    protected S initialEnvironment;
    private static LinkedList<Cell> shelves;
    private static Cell cellAgent;
    private static Cell exit;
    private static ArrayList<Request> requests;
    private static int numProducts;
    private static LinkedList<Pair> pairs;

    public WarehouseAgentSearch(S environment) {
        super(environment);
        initialEnvironment = environment;
        heuristics.add(new HeuristicWarehouse());
        heuristic = heuristics.get(0);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();

        str.append("Pairs:\n");
        for (Pair p : pairs) {
            str.append(p);
        }
        return str.toString();
    }

    public String showRequests() {
        StringBuilder str = new StringBuilder();
        str.append("\n\nRequests:\n");
        for (Request r : requests) {
            str.append(r).append("\n");
        }
        return str.toString();
    }

    public static int[][] readInitialStateFromFile(File file) throws IOException {
        java.util.Scanner scanner = new java.util.Scanner(file);

        cellAgent = null;
        int dim = scanner.nextInt();
        shelves = new LinkedList<>();
        scanner.nextLine();
        int[][] matrix = new int[dim][dim];
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                matrix[i][j] = scanner.nextInt();
                if (matrix[i][j] == Properties.AGENT) {
                    cellAgent = new Cell(i, j);
                    exit = new Cell(i, j);
                } else if (matrix[i][j] == Properties.SHELF)
                    shelves.add(new Cell(i, j));
            }
            scanner.nextLine();
        }
        pairs = new LinkedList<>();
        for (Cell b : shelves) {
            pairs.add(new Pair(cellAgent, b));
        }

        for (int i = 0; i < shelves.size() - 1; i++) {
            for (int j = i + 1; j < shelves.size(); j++) {
                pairs.add(new Pair(shelves.get(i), shelves.get(j)));
            }
        }

        numProducts = scanner.nextInt();
        scanner.nextLine();
        requests = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String currentline = scanner.nextLine();

            String[] items = currentline.split(" ");
            int[] intitems = new int[items.length];

            for (int i = 0; i < items.length; i++) {
                intitems[i] = Integer.parseInt(items[i]);
            }
            requests.add(new Request(intitems));
        }
        return matrix;
    }

    public static LinkedList<Cell> getShelves() {
        return shelves;
    }

    public LinkedList<Pair> getPairs() {
        return pairs;
    }

    public static Cell getCellAgent() {
        return cellAgent;
    }

    public static Cell getExit() {
        return exit;
    }

    public static ArrayList<Request> getRequests() {
        return requests;
    }

    public static int getNumProducts() {
        return numProducts;
    }

}
