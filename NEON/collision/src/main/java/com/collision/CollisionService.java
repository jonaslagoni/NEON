/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.collision;

import com.library.Sprite;
import com.library.interfaces.Drawable;
import com.library.interfaces.ICollisionService;
import com.library.interfaces.IWorldService;
import com.library.vectors.Vector2f;

import java.util.ArrayList;
import java.util.List;

import static com.library.vectors.VectorUtils.distanceSquare;

/**
 * @author Daniel
 */
public class CollisionService implements ICollisionService {

    private IWorldService world;

    public void setWorld(IWorldService world) {
        this.world = world;
    }

    public void removeWorld(IWorldService world) {
        this.world = null;
    }

    @Override
    public List<Drawable> getCollisions(Sprite sprite0) {
        return getCollisions(sprite0.getPosition(), sprite0.getHeight() / 2);
    }

    @Override
    public List<Drawable> getCollisions(Vector2f position0, float radius0) {
        List<Drawable> collisions = new ArrayList<>();
        if(world != null){
            for (Drawable drawable : world.getEntities(Drawable.class)) {

                Vector2f position1 = drawable.getSprite().getPosition();
                float radius1 = drawable.getSprite().getWidth() / 2;

                if (position0 != position1) {
                    float dist = distanceSquare(position0, position1);
                    float distRad = (radius0 + radius1) * (radius0 + radius1);
                    if(distRad >= dist){
                        collisions.add(drawable);
                    }
                }
            }
        }
        return collisions;
    }

}
