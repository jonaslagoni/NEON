package com.library.interfaces;

import com.library.ShotType;
import com.library.vectors.Vector2f;

public interface IProjectileService {

    /**
     * Creates a new projectile at spawn moves to target with a given shottype and damage
     * @param spawn vector
     * @param target vector
     * @param shotType type of shot
     * @param damage float
     */
    void newProjectile(Vector2f spawn, Vector2f target, ShotType shotType, float damage);
}
