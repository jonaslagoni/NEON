package com.neon.libary;

import com.neon.libary.interfaces.Controller;
import com.neon.libary.interfaces.Moveable;

import static com.neon.libary.vectors.VectorUtils.deltaX;
import static com.neon.libary.vectors.VectorUtils.deltaY;

public class MoveController implements Controller {

    private World world;

    public MoveController(World world) {
        this.world = world;
    }

    private void moveEntity(Moveable moveable) {
        /* Move */

        MoveAbility moveAbility = moveable.getMoveAbility();
        Sprite sprite = moveable.getSprite();

        if (!moveAbility.isMove()) return;

        float rotation = sprite.getRotation();
        float velocity = moveAbility.getVelocity();

        float deltaX = deltaX(rotation, velocity);
        float deltaY = deltaY(rotation, velocity);

        sprite.translateX(deltaX);
        sprite.translateY(deltaY);
    }

    @Override
    public void update() {
        world.getEntities(Moveable.class).forEach(this::moveEntity);
    }
}
