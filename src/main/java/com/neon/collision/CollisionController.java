/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neon.collision;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.neon.main.World;
import com.neon.main.entities.Drawable;
import com.neon.main.entities.Entity;
import com.neon.main.entities.Sprite;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Daniel
 */
public class CollisionController implements Collision {

    private World world;

    public CollisionController(World world) {
        this.world = world;
    }

    public CollisionController() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Entity> getCollisions(Sprite sprite) {

        Array<Drawable> allEntities = world.getEntities(Drawable.class);
        ArrayList<Entity> returnList = new ArrayList<>();

        float CollidingObjectRadius = sprite.getWidth() / 2;
        Vector2 collidingObject = sprite.getPosition();

        for (Drawable drawable : allEntities) {
            Vector2 otherObjects = drawable.getSprite().getPosition();
            float otherObjectRadius = drawable.getSprite().getWidth() / 2;

            float xValueDifference = otherObjects.x - collidingObject.x;
            float yValueDifference = otherObjects.y - collidingObject.y;

            float distance = (xValueDifference * xValueDifference) + (yValueDifference * yValueDifference);

            if (CollidingObjectRadius + otherObjectRadius < distance && !(sprite == drawable.getSprite())) {
                returnList.add(drawable);
            }
        }

        return returnList;
    
    }
}
