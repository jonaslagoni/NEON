package com.neon.player;

import com.badlogic.gdx.graphics.Texture;
import com.neon.main.entities.Drawable;
import com.neon.main.entities.MoveAbility;
import com.neon.main.entities.Moveable;
import com.neon.main.entities.Position;

public class Player implements Moveable, Drawable {

    private Texture texture;
    private Position position;
    private MoveAbility moveAbility;
    private float width;
    private float height;

    public Player(
            Texture texture,
            Position position,
            MoveAbility moveAbility,
            float width,
            float height
    ) {
        this.texture = texture;
        this.position = position;
        this.moveAbility = moveAbility;
        this.width = width;
        this.height = height;
    }

    @Override
    public MoveAbility getMoveAbility() {
        return moveAbility;
    }

    @Override
    public Position getPosition() {
        return position;
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
