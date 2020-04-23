package warehouse;

import agentSearch.Problem;

import java.util.List;

public class WarehouseProblemForSearch<S extends WarehouseState> extends Problem<S> {

    //TODO this class might require the definition of additional methods and/or attributes
    private Cell goalPosition;

    public WarehouseProblemForSearch(S initialWarehouseState, Cell goalPosition) {
        super(initialWarehouseState);
        this.goalPosition = goalPosition;
        //linha e coluna do agente = lina e coluna goalPosition
    }

    @Override
    public List<S> executeActions(S state) {
        //TODO
        throw new UnsupportedOperationException("Not implemented yet.");
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
