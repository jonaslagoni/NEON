package com.neon.libary;

import com.neon.libary.interfaces.Controller;
import com.neon.libary.interfaces.Moveable;

import static com.badlogic.gdx.math.MathUtils.PI;
import static com.neon.libary.VectorUtils.*;

public class MoveController implements Controller {

    private World world;

    public MoveController(World world) {
        this.world = world;
    }


    private void moveEntity(Moveable moveable) {

        MoveAbility moveAbility = moveable.getMoveAbility();
        Sprite sprite = moveable.getSprite();

        /* Don't Move if player is on target.
         * It is not necessary to calculate the actual distance, just the square of it. */
        if (!moveAbility.hasTarget()) return;

        if (distanceSquare(sprite.getPosition(), moveAbility.getTargetVector()) < 2) {
            moveAbility.setTarget(false);
            return;
        }

        /* Calculate angle
         * https://stackoverflow.com/questions/21483999/using-atan2-to-find-angle-between-two-vectors */
        sprite.setRotation(angle(sprite.getPosition(), moveAbility.getTargetVector()) + PI);

        /* Move */
        sprite.translateX(deltaX(sprite.getRotation(), moveAbility.getVelocity()));
        sprite.translateY(deltaY(sprite.getRotation(), moveAbility.getVelocity()));
    }

    @Override
    public void update() {
        world.getEntities(Moveable.class).forEach(this::moveEntity);
    }
}
