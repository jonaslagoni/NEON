/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.interfaces;

import com.library.vectors.Vector2f;
import com.neon.libary.Sprite;

/**
 * @author Daniel
 */
public interface ITargetingService extends Service {

    Vector2f calculateTargetVector(Vector2f weaponPosition, Sprite enemy);
}
