package com.library.interfaces;

import com.library.Sprite;

public interface Drawable extends Entity {

    /**
     * Return the entity sprite
     *
     * @return sprite
     */
    Sprite getSprite();
}
