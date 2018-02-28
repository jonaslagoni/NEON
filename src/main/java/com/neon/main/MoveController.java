package com.neon.main;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.neon.main.entities.MoveAbility;
import com.neon.main.entities.Moveable;
import com.neon.main.entities.Position;

import static com.badlogic.gdx.Gdx.graphics;
import static com.badlogic.gdx.math.MathUtils.cos;
import static com.badlogic.gdx.math.MathUtils.sin;

public class MoveController implements Controller {

    @Override
    public void update(World world) {
        for (Moveable moveable : world.getEntities(Moveable.class)) {
            move(moveable.getMoveAbility(), moveable.getPosition());
        }
    }

    private void move(MoveAbility moveAbility, Position position) {

        Vector2 targetVector = moveAbility.getTargetVector();
        Vector2 positionVector = position.getVector();

        /* Dont Move if player is on target, dst is the distance */
        if (positionVector.dst(targetVector) <= 3) return;

        /* Calculate angle
         * https://stackoverflow.com/questions/21483999/using-atan2-to-find-angle-between-two-vectors */
        position.setRotation(MathUtils.atan2(positionVector.y - targetVector.y, positionVector.x - targetVector.x) + MathUtils.PI);


        /* Move */
        positionVector.x += cos(position.getRotation()) * moveAbility.getVelocity() * graphics.getDeltaTime();
        positionVector.y += sin(position.getRotation()) * moveAbility.getVelocity() * graphics.getDeltaTime();

    }

}
