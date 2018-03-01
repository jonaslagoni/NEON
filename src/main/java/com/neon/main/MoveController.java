package com.neon.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.neon.main.entities.MoveAbility;
import com.neon.main.entities.Moveable;
import com.neon.main.entities.Position;

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
        Position position = moveable.getPosition();
        Vector2 targetVector = moveAbility.getTargetVector();
        Vector2 positionVector = position.getVector();

        /* Don't Move if player is on target.
         * It is not necessary to calculate the actual distance, just the square of it. */
        if (distanceSquare(positionVector, targetVector) < 2) return;

        /* Calculate angle
         * https://stackoverflow.com/questions/21483999/using-atan2-to-find-angle-between-two-vectors */
        position.setRotation(angle(positionVector, targetVector) + PI);

        /* Move */
        positionVector.x += deltaX(position.getRotation(), moveAbility.getVelocity());
        positionVector.y += deltaY(position.getRotation(), moveAbility.getVelocity());
    }

    @Override
    public void update(World world) {
        world.getEntities(Moveable.class).forEach(MoveController::move);
    }
}
