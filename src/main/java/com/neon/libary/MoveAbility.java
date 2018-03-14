package com.neon.libary;

import com.neon.libary.vectors.Vector2f;

public class MoveAbility {

    private float velocity;
    private Vector2f targetVector;
    private boolean move;

    public MoveAbility(float velocity) {
        this.targetVector = new Vector2f(0f, 0f);
        this.velocity = velocity;
    }

    public float getVelocity() {
        return velocity;
    }

    public void setVelocity(float velocity) {
        this.velocity = velocity;
    }

    public Vector2f getTargetVector() {
        return targetVector;
    }

    public void setTargetVector(Vector2f targetVector) {
        this.targetVector = targetVector;
    }

    public boolean isMove() {
        return move;
    }

    public void setMove(boolean move) {
        this.move = move;
    }
}
