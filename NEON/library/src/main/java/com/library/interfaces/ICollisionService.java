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

    List<Drawable> getCollisions(Sprite sprite);

    List<Drawable> getCollisions(Vector2f position, float range);

}
