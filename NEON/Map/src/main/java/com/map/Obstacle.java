package com.map;

import com.library.Sprite;
import com.library.interfaces.Drawable;

public class Obstacle implements Drawable {

    private Sprite sprite;

    Obstacle(Sprite sprite) {
        this.sprite = sprite;
    }

    @Override
    public Sprite getSprite() {
        return sprite;
    }
}
