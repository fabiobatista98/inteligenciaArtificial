package warehouse;

import agentSearch.Action;

public class ActionDown extends Action<WarehouseState>{

    public ActionDown(){
        super(1);
    }

    @Override
    public void execute(WarehouseState state){
        state.moveDown();
        state.setAction(this);
    }

    @Override
    public boolean isValid(WarehouseState state){
        return state.canMoveDown();
    }
}