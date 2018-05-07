package com.library;

import com.library.vectors.Vector2f;

public class MoveAbility {

    private Vector2f target;
    private boolean move;

    
    public MoveAbility(Vector2f target, boolean move) {
        this.target = target;
        this.move = move;
    }
    public Vector2f getTarget() {
        return target;
    }
    
    public void setTarget(Vector2f target) {
        this.target = target;
    }

    public boolean isMove() {
        return move;
    }

    public void setMove(boolean move) {
        this.move = move;
    }
}
