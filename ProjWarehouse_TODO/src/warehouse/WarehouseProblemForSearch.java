package warehouse;

import agentSearch.Action;
import agentSearch.Problem;

import java.util.LinkedList;
import java.util.List;

public class WarehouseProblemForSearch<S extends WarehouseState> extends Problem<S> {

    public WarehouseState getGoalState() {
        return goalState;
    }

    private Cell goalPosition;
    int distancia;
    private List<Action> actions;
    private WarehouseState goalState;



    public WarehouseProblemForSearch(S initialWarehouseState, Cell goalPosition) {
        super(initialWarehouseState);
        this.goalPosition = goalPosition;
        //linha e coluna do agente = lina e coluna goalPosition
        distancia = Math.abs(initialWarehouseState.getLineAgent()-goalPosition.getLine())+ Math.abs(initialWarehouseState.getColumnAgent()-goalPosition.getColumn());

        actions = new LinkedList<>();
        actions.add(new ActionDown());
        actions.add(new ActionUp());
        actions.add(new ActionRight());
        actions.add(new ActionLeft());
    }

    @Override
    public List<S> executeActions(S state) {

        List<S> successors = new LinkedList<>();
        for (Action a : actions){
            if (a.isValid(state)) {
                WarehouseState successor = (WarehouseState)state.clone();
                a.execute(successor);
                successors.add((S) successor);
            }
        }

        return successors;
    }

    public boolean isGoal(S state) {
        //verificar se a posição do agente é igual à posição goal
        if (goalPosition.getLine() == state.getColumnExit() && goalPosition.getColumn() == state.getLineExit()){
            //porta de saida
            return goalPosition.getLine() == state.getLineAgent() && goalPosition.getColumn() == state.getColumnAgent();
        }
        //parteleira
        return goalPosition.getLine() == state.getLineAgent() && goalPosition.getColumn() == state.getColumnAgent()-1;
    }
}

