package com.ecorp.main;

import com.badlogic.gdx.graphics.Texture;

public class Entity {

    private Texture texture;
    private float x;
    private float y;
    private float rotation;

    public Entity(Texture texture, float x, float y, float rotation) {
        this.texture = texture;
        this.x = x;
        this.y = y;
        this.rotation = rotation;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }
}
