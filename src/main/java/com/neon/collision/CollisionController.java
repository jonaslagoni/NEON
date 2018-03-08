/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neon.collision;


import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.neon.libary.interfaces.Entity;
import java.util.ArrayList;
import com.neon.libary.Sprite;
import com.neon.libary.World;
import com.neon.libary.interfaces.Drawable;
import static java.lang.Math.sqrt;



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
    public ArrayList<Entity> getCollisions(Sprite sprite) {

        ArrayList<Drawable> allEntities = (ArrayList<Drawable>) world.getEntities(Drawable.class);
        ArrayList<Entity> returnList = new ArrayList<>();

        float CollidingObjectRadius = sprite.getWidth() / 2;
        Vector2 collidingObject = sprite.getPosition();

        for (Drawable drawable : allEntities) {
            Vector2 otherObjects = drawable.getSprite().getPosition();
            float otherObjectRadius = drawable.getSprite().getWidth() / 2;

            float xValueDifference = otherObjects.x - collidingObject.x;
            float yValueDifference = otherObjects.y - collidingObject.y;

            double distance = sqrt((xValueDifference * xValueDifference) + (yValueDifference * yValueDifference));

            if (CollidingObjectRadius + otherObjectRadius > distance && !(sprite == drawable.getSprite())) {
                returnList.add(drawable);
            }
        }

        return returnList;
    
    }

  
}
