package com.neon.libary;

import com.badlogic.gdx.graphics.Texture;
import com.neon.libary.vectors.Vector2f;

public class Sprite {

    private final float height;
    private final float width;
    private Vector2f position;
    private Vector2f velocity;
    private Texture texture;

    public Sprite(Texture texture,
                  Vector2f position,
                  Vector2f velocity,
                  float height,
                  float width) {
        this.height = height;
        this.width = width;
        this.texture = texture;
        this.velocity = velocity;
        this.position = position;
    }

    public Vector2f getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2f velocity) {
        this.velocity = velocity;
    }

    public float getHeight() {
        return height;
    }

    public float getWidth() {
        return width;
    }

    public Vector2f getPosition() {
        return position;
    }

    public void setPosition(Vector2f position) {
        this.position = position;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }
}
