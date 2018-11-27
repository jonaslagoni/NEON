/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.interfaces;

import com.library.Sprite;
import com.library.vectors.Vector2f;

import java.util.List;

/**
 * @author Daniel
 */
public interface ICollisionService {

    /**
     * Returns a list of drawables which collide with a given sprite
     *
     * @param sprite sprite object to check collisions with
     * @return list of collisions
     */
    List<Drawable> getCollisions(Sprite sprite);

    /**
     * Returns a list of drawables which collides with a given position and range
     *
     * @param position at the sprite
     * @param range    of the sprite
     * @return a list of collisions
     */
    List<Drawable> getCollisions(Vector2f position, float range);

}
