package com.library.interfaces;

import com.library.ShotType;
import com.library.vectors.Vector2f;

public interface IProjectileService {

    /**
     * 
     * @param spawn
     * @param target
     * @param shotType
     * @param damage 
     */
    void newProjectile(Vector2f spawn, Vector2f target, ShotType shotType, float damage);
}
