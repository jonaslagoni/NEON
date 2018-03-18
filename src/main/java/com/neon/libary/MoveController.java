package com.neon.libary;

import com.neon.libary.interfaces.Controller;
import com.neon.libary.interfaces.Moveable;
import com.neon.libary.vectors.Vector2f;

import static com.neon.libary.vectors.VectorUtils.translate;

public class MoveController implements Controller {

    private final World world;

    public MoveController(World world) {
        this.world = world;
    }

    private void moveEntity(Moveable moveable) {
        /* Move */

        MoveAbility moveAbility = moveable.getMoveAbility();
        Sprite sprite = moveable.getSprite();

        if (!moveAbility.isMove()) return;

        Vector2f velocity = sprite.getVelocity();
        Vector2f position = sprite.getPosition();

        Vector2f newPosition = translate(position, velocity);
        sprite.setPosition(newPosition);
    }

    @Override
    public void update() {
        world.getEntities(Moveable.class).forEach(this::moveEntity);
    }
}
