package com.neon.libary;

import com.neon.libary.interfaces.Controller;
import com.neon.libary.interfaces.Moveable;

import static com.neon.libary.VectorUtils.deltaX;
import static com.neon.libary.VectorUtils.deltaY;

public class MoveController implements Controller {

    private World world;

    public MoveController(World world) {
        this.world = world;
    }

    private void moveEntity(Moveable moveable) {
        /* Move */
        moveable.getSprite().translateX(deltaX(moveable.getSprite().getRotation(), moveable.getMoveAbility().getVelocity()));
        moveable.getSprite().translateY(deltaY(moveable.getSprite().getRotation(), moveable.getMoveAbility().getVelocity()));
    }

    @Override
    public void update() {
        world.getEntities(Moveable.class).forEach(this::moveEntity);
    }
}
