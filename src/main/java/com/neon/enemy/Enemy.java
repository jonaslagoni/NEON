package com.neon.enemy;

import com.badlogic.gdx.graphics.Texture;
import com.neon.main.entities.Drawable;
import com.neon.main.entities.MoveAbility;
import com.neon.main.entities.Moveable;
import com.neon.main.entities.Position;

/**
 * Created by sam on 02-03-2018.
 */
public class Enemy implements Moveable, Drawable {

    private Position position;
    private MoveAbility moveAbility;
    private Texture texture;
    private float width;
    private float height;

    @SuppressWarnings("WeakerAccess")
    public Enemy(
            Texture texture,
            Position position,
            MoveAbility moveAbility,
            float width,
            float height
    ) {
        this.position = position;
        this.moveAbility = moveAbility;
        this.texture = texture;
        this.width = width;
        this.height = height;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public MoveAbility getMoveAbility() {
        return moveAbility;
    }

    @Override
    public Texture getTexture() {
        return texture;
    }

    @Override
    public float getWidth() {
        return width;
    }

    @Override
    public float getHeight() {
        return height;
    }
}
