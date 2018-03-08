/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neon.collision;

import com.badlogic.gdx.math.Vector2;
import com.neon.libary.Sprite;
import com.neon.libary.World;
import com.neon.libary.interfaces.Drawable;
import com.neon.libary.interfaces.Entity;
import com.neon.libary.interfaces.ICollisionService;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.sqrt;

/**
 * @author Daniel
 */
public class CollisionService implements ICollisionService {

    private World world;

    CollisionService(World world) {
        this.world = world;
    }

    private static float distance(Vector2 a, Vector2 b) {
        return (float) sqrt((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y));
    }

    @Override
    public List<Entity> getCollisions(Sprite sprite0) {

        List<Entity> collisions = new ArrayList<>();

        float radius0 = sprite0.getWidth() / 2;
        Vector2 position0 = sprite0.getPosition();

        for (Drawable drawable : world.getEntities(Drawable.class)) {

            Sprite sprite1 = drawable.getSprite();
            Vector2 position1 = drawable.getSprite().getPosition();
            float radius1 = drawable.getSprite().getWidth() / 2;

            if (sprite0 != sprite1 && radius0 + radius1 > distance(position0, position1)) {
                collisions.add(drawable);
            }
        }
        return collisions;
    }
}
