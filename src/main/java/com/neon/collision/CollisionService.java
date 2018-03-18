/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neon.collision;

import com.neon.libary.Sprite;
import com.neon.libary.World;
import com.neon.libary.interfaces.Drawable;
import com.neon.libary.interfaces.ICollisionService;
import com.neon.libary.vectors.Vector2f;

import java.util.ArrayList;
import java.util.List;

import static com.neon.libary.vectors.VectorUtils.distanceSquare;

/**
 * @author Daniel
 */
public class CollisionService implements ICollisionService {

    private final World world;

    CollisionService(World world) {
        this.world = world;
    }

    @Override
    public List<Drawable> getCollisions(Sprite sprite0) {
        return getCollisions(sprite0.getPosition(), sprite0.getHeight() / 2);
    }

    @Override
    public List<Drawable> getCollisions(Vector2f position0, float radius0) {
        List<Drawable> collisions = new ArrayList<>();
        for (Drawable drawable : world.getEntities(Drawable.class)) {

            Vector2f position1 = drawable.getSprite().getPosition();
            float radius1 = drawable.getSprite().getWidth() / 2;

            if (position0 != position1
                    && (radius0 + radius1) * (radius0 + radius1) > distanceSquare(position0, position1)) {
                collisions.add(drawable);
            }
        }
        return collisions;
    }
}
