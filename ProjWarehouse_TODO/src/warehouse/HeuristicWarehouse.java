package warehouse;

import agentSearch.Heuristic;

public class HeuristicWarehouse extends Heuristic<WarehouseProblemForSearch, WarehouseState> {
    @Override
    public double compute(WarehouseState state){
        //implementar dentro do state, calcular distancia linear até ao goal
        //sumatorio do modelo entre esses dois valores
        Cell agent = new Cell(state.getLineAgent(),state.getColumnAgent());

        return problem.getGoalPosition() + agent;
    }


    @Override
    public String toString(){
        return "Tiles distance to final position";
    }
}