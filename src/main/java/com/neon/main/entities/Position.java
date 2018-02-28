package com.neon.main.entities;

import com.badlogic.gdx.math.Vector2;

public class Position {

    private Vector2 vector;
    private float rotation;

    public Position(Vector2 vector, float rotation) {
        this.vector = vector;
        this.rotation = rotation;
    }

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public Vector2 getVector() {
        return vector;
    }

    public void setVector(Vector2 vector) {
        this.vector = vector;
    }
}
