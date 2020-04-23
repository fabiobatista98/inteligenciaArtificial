package warehouse;

import agentSearch.Action;

public class ActionRight extends Action<WarehouseState>{

    public ActionRight(){
        super(1);
    }

    @Override
    public void execute(WarehouseState state){
        state.moveRight();
        state.setAction(this);
    }

    @Override
    public boolean isValid(WarehouseState state){
        return state.canMoveRight();
    }
}