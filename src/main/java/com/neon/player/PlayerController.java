package com.neon.player;

import com.neon.libary.World;
import com.neon.libary.interfaces.Controller;
import com.neon.libary.vectors.Vector2f;

import static com.neon.libary.vectors.VectorUtils.distanceSquare;
import static com.neon.libary.vectors.VectorUtils.translateVelocity;

public class PlayerController implements Controller {
    private World world;

    PlayerController(World world) {
        this.world = world;
    }

    @Override
    public void update() {

        for (Player player : world.getEntities(Player.class)) {

            Vector2f position = player.sprite.getPosition();
            Vector2f velocity = player.sprite.getVelocity();
            Vector2f target = player.moveAbility.getTarget();


            /* Don't Move if player is on target.
             * It is not necessary to calculate the actual distance, just the square of it. */

            player.getMoveAbility().setMove(distanceSquare(position, target) >= 4);


            /* Calculate angle

             * https://stackoverflow.com/questions/21483999/using-atan2-to-find-angle-between-two-vectors */

            Vector2f newVelocity = translateVelocity(position, target, velocity);
            player.sprite.setVelocity(newVelocity);
        }
    }
}
