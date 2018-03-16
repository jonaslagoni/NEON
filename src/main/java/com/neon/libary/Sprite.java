package com.neon.libary;

import com.badlogic.gdx.graphics.Texture;
import com.neon.libary.vectors.Vector2f;

public class Sprite {

    private float height;
    private float width;
    private Vector2f position;
    private float rotation;
    private Texture texture;

    public Sprite(Texture texture,
                  float height,
                  float width,
                  float rotation,
                  Vector2f position) {
        this.rotation = rotation;
        this.height = height;
        this.width = width;
        this.texture = texture;
        this.position = position;
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

    public void setPosition(float x, float y) {
        position.setY(y);
        position.setX(x);
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
        position.setX(position.getX() + x);
    }

    public void translateY(float y) {
        position.setY(position.getY() + y);
    }
}
