package com.neon.player;

import com.badlogic.gdx.graphics.Texture;
import com.neon.main.entities.*;

public class Player extends Entity implements Moveable, Drawable {

    private Position position;
    private MoveAbility moveAbility;

    public Player(Texture texture, Position position, MoveAbility moveAbility) {
        super(texture);
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

}
