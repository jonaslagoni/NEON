package com.neon.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.neon.main.entities.MoveAbility;
import com.neon.main.entities.Moveable;
import com.neon.main.entities.Sprite;

import static com.badlogic.gdx.math.MathUtils.*;

public class MoveController implements Controller {

    private static float angle(Vector2 a, Vector2 b) {
        return atan2(a.y - b.y, a.x - b.x);
    }

    private static float deltaX(float angle, float velocity) {
        return cos(angle) * velocity * Gdx.graphics.getDeltaTime();
    }

    private static float deltaY(float angle, float velocity) {
        return sin(angle) * velocity * Gdx.graphics.getDeltaTime();
    }

    private static float distanceSquare(Vector2 a, Vector2 b) {
        return (a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y);
    }

    private static void move(Moveable moveable) {

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
    public void update(World world) {
        world.getEntities(Moveable.class).forEach(MoveController::move);
    }
}
