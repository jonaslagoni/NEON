package com.neon.main.entities;

import com.badlogic.gdx.math.Vector2;

public class MoveAbility {

    private float velocity;
    private Vector2 targetVector;

    public MoveAbility(Vector2 targetVector, float velocity) {
        this.targetVector = targetVector;
        this.velocity = velocity;

    }

    public float getVelocity() {
        return velocity;
    }

    public void setVelocity(float velocity) {
        this.velocity = velocity;
    }

    public Vector2 getTargetVector() {
        return targetVector;
    }

    public void setTargetVector(Vector2 targetVector) {
        this.targetVector = targetVector;
    }
}