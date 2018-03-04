package com.neon.main.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Sprite {

    private float height;
    private float width;
    private Vector2 position;
    private float rotation;
    private Texture texture;

    public Sprite(Texture texture,
                  Vector2 position,
                  float height,
                  float width,
                  float rotation) {
        this(texture, position, height, width);
        this.rotation = rotation;
    }

    public Sprite(Texture texture,
                  Vector2 position,
                  float height,
                  float width) {
        this.height = height;
        this.width = width;
        this.position = position;
        this.texture = texture;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public void translateX(float x) {
        position.x += x;
    }

    public void translateY(float y) {
        position.y += y;
    }
}
