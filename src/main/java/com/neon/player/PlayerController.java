package com.neon.player;

import com.neon.libary.World;
import com.neon.libary.interfaces.Controller;

import static com.badlogic.gdx.math.MathUtils.PI;
import static com.neon.libary.vectors.VectorUtils.angle;
import static com.neon.libary.vectors.VectorUtils.distanceSquare;

public class PlayerController implements Controller {
    private World world;

    PlayerController(World world) {
        this.world = world;
    }

    @Override
    public void update() {

        for (Player player : world.getEntities(Player.class)) {
            /* Don't Move if player is on target.
             * It is not necessary to calculate the actual distance, just the square of it. */

            if (distanceSquare(player.sprite.getPosition(), player.moveAbility.getTargetVector()) < 4){
                player.getMoveAbility().setMove(false);
            }else{
                player.getMoveAbility().setMove(true);
            }
            /* Calculate angle
             * https://stackoverflow.com/questions/21483999/using-atan2-to-find-angle-between-two-vectors */
            player.sprite.setRotation(angle(player.sprite.getPosition(), player.moveAbility.getTargetVector()) + PI);
        }
    }
}
