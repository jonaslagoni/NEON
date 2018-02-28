package com.neon.main.entities;

import com.badlogic.gdx.graphics.Texture;

public class Entity {

    private Texture texture;

    public Entity(Texture texture) {
        this.texture = texture;

    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

}
