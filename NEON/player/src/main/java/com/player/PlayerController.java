package com.player;

import com.library.interfaces.Controller;
import com.library.interfaces.IInputService;
import com.library.interfaces.IWorldService;
import com.library.vectors.Vector2f;

import static com.library.vectors.VectorUtils.distanceSquare;
import static com.library.vectors.VectorUtils.translateVelocity;

public class PlayerController implements Controller {

    private IWorldService world;
    private IInputService inputService;

    public void setWorld(IWorldService world) {
        this.world = world;
    }

    public void removeWorld(IWorldService world) {
        this.world = null;
    }

    public void setInputService(IInputService inputService) {
        this.inputService = inputService;
    }

    public void removeInputService(IInputService iInputService) {
        this.inputService = null;
    }

    @Override
    public void update(float dt) {

        world.getEntities(Player.class).forEach((player) -> {

                    if (inputService.isClicked() && !IWorldService.isOutOfBounds(inputService.getCords())) {
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
