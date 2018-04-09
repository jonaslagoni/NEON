package com.library;

import com.library.interfaces.Controller;
import com.library.interfaces.Moveable;
import com.library.vectors.Vector2f;
import static com.library.vectors.VectorUtils.translate;

public class MoveController implements Controller {

    private World world;

    public void setWorld(World world) {
        this.world = world;
    }

    public void removeWorld() {
        this.world = null;
    }

    private void moveEntity(Moveable moveable, float dt) {
        /* Move */
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
