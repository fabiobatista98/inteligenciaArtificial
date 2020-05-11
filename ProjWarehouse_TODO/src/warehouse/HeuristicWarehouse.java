package warehouse;

import agentSearch.Heuristic;

public class HeuristicWarehouse extends Heuristic<WarehouseProblemForSearch, WarehouseState> {
    @Override
    public double compute(WarehouseState state){
        //TODO
        //implementar dentro do state, calcular distancia linear até ao goal
        //sumatorio do modelo entre esses dois valores
        return state.computeTileDistances(problem.getGoalState());
    }

    @Override
    public String toString(){
        return "Tiles distance to final position";
    }
}