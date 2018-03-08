package com.neon.weapon;

import com.badlogic.gdx.math.Vector2;
import com.neon.libary.interfaces.Entity;

public class Weapon implements Entity {

    private Entity projectile;
    private String projectileType;
    private Vector2 position;

    public Weapon() {
    }

    Weapon(String projectileType, Vector2 position) {
        this.projectileType = projectileType;
        this.position = position;
    }

    public String getProjectileType() {
        return projectileType;
    }

    public void setProjectileType(String projectileType) {
        this.projectileType = projectileType;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }
}
