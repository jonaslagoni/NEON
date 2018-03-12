package com.neon.libary;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Sprite {

    private float height;
    private float width;
    private Vector2 position;
    private float rotation;
    private Texture texture;

    /**
     * @param texture  texture
     * @param height   height
     * @param width    width
     * @param rotation rotation
     */
    public Sprite(Texture texture,
                  float height,
                  float width,
                  float rotation) {
        this(texture, height, width);
        this.rotation = rotation;
    }

    /**
     * @param texture texture
     * @param height  height
     * @param width   width
     */
    public Sprite(Texture texture,
                  float height,
                  float width) {
        this.height = height;
        this.width = width;
        this.texture = texture;
        this.position = new Vector2();
    }

    public Sprite(Texture texture,
                  float height,
                  float width,
                  Vector2 position) {
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

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public void setPosition(float x, float y) {
        position.x = x;
        position.y = y;
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
