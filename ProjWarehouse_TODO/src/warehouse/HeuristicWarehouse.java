package warehouse;

import agentSearch.Heuristic;

public class HeuristicWarehouse extends Heuristic<WarehouseProblemForSearch, WarehouseState> {
    @Override
    public double compute(WarehouseState state){

        //sumatorio do modelo entre esses dois valores
        //verificar se o goal é a porta de saida, senão colocar +1 na colona do goal por causa de ser uma parteleira
        if(state.getLineExit() == problem.getGoalPosition().getLine() && state.getColumnExit() == problem.getGoalPosition().getColumn()){
            return Math.abs(problem.getGoalPosition().getLine() - state.getLineAgent()) + Math.abs(problem.getGoalPosition().getColumn() -  state.getColumnAgent());
        }
        return Math.abs(problem.getGoalPosition().getLine() - state.getLineAgent()) + Math.abs((problem.getGoalPosition().getColumn()+1) -  state.getColumnAgent());
    }


    @Override
    public String toString(){
        return "Tiles distance to final position";
    }
}