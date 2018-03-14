/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neon.libary.interfaces;

import com.neon.libary.Sprite;
import com.neon.libary.vectors.Vector2f;

import java.util.List;

/**
 * @author Daniel
 */
public interface ICollisionService extends Service {

    List<Drawable> getCollisions(Sprite sprite);

    List<Drawable> getCollisions(Vector2f position, float range);

}
