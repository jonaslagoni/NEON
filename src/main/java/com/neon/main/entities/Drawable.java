package com.neon.main.entities;

import com.badlogic.gdx.graphics.Texture;

public interface Drawable extends Positionable {
    Texture getTexture();

    float getWidth();

    float getHeight();
}
