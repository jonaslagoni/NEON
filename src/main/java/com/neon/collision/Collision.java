/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neon.collision;

import com.badlogic.gdx.utils.Array;
import com.neon.main.entities.Entity;
import com.neon.main.entities.Sprite;
import java.util.List;

/**
 *
 * @author Daniel
 */
public interface Collision {
    
    List<Entity> getCollisions(Sprite sprite);
    
 }
