package com.move;

import com.library.MoveAbility;
import com.library.Sprite;
import com.library.interfaces.Controller;
import com.library.interfaces.IWorldService;
import com.library.interfaces.Moveable;
import com.library.vectors.Vector2f;
import static com.library.vectors.VectorUtils.translate;

public class MoveController implements Controller {

    private IWorldService world;

    public void setWorld(IWorldService world) {
        this.world = world;
    }

    public void removeWorld(IWorldService world) {
        this.world = null;
    }

    /**
     * Updates the vector2f of an entity, changing the speed and direction, and
     * sets the new position.
     * @param moveable Moveable entity to be moved
     * @param dt time since last frame
     */
    private void moveEntity(Moveable moveable, float dt) {
        MoveAbility moveAbility = moveable.getMoveAbility();
        Sprite sprite = moveable.getSprite();

        if (!moveAbility.isMove()) {
            return;
        }

        Vector2f velocity = sprite.getVelocity();
        Vector2f position = sprite.getPosition();

        Vector2f newPosition = translate(position, velocity, dt);
        sprite.setPosition(newPosition);
    }

    @Override
    public void update(float dt) {
        world.getEntities(Moveable.class).forEach(m -> moveEntity(m, dt));
    }
}
