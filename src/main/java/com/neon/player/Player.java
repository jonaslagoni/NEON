package com.neon.player;

import com.badlogic.gdx.graphics.Texture;
import com.neon.main.entities.*;

public class Player implements Moveable, Drawable, Entity {

    private Texture texture;
    private Position position;
    private MoveAbility moveAbility;

    public Player(Texture texture, Position position, MoveAbility moveAbility) {
        this.texture = texture;
        this.position = position;
        this.moveAbility = moveAbility;
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
}
