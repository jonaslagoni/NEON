package com.player;

import com.library.interfaces.IInputService;
import com.library.World;
import com.library.interfaces.Controller;
import com.library.vectors.Vector2f;
import static com.library.vectors.VectorUtils.distanceSquare;
import static com.library.vectors.VectorUtils.translateVelocity;

public class PlayerController implements Controller {

    private World world;
    private IInputService inputService;

    PlayerController(World world) {
        this.world = world;
    }

    @Override
    public void update(float dt) {

        world.getEntities(Player.class).forEach((player) -> {

            if (inputService.isClicked() && !World.isOutOfBounds(inputService.getCords())) {
                player.moveAbility.setTarget(inputService.getCords());
            }

            Vector2f position = player.sprite.getPosition();
            Vector2f velocity = player.sprite.getVelocity();
            Vector2f target = player.moveAbility.getTarget();

            /* Don't Move if player is on target.
            * It is not necessary to calculate the actual distance, just the square of it. */
            player.getMoveAbility().setMove(distanceSquare(position, target) >= 4);

            /* Calculate angle */
            Vector2f newVelocity = translateVelocity(position, target, velocity);
            player.sprite.setVelocity(newVelocity);
        }
        );
    }
}
