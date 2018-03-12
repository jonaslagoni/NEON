package com.neon.weapon;

import com.badlogic.gdx.math.Vector2;
import com.neon.libary.interfaces.Entity;

public class Weapon implements Entity {

    Vector2 position;
    float fireCooldown;
    float range;

    public Weapon(Vector2 position,
                  float range) {
        this.position = position;
        this.range = range;
    }
}
