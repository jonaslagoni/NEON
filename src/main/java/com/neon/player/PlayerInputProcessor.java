package com.neon.player;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.neon.main.World;
import com.neon.main.entities.MoveAbility;

import static com.badlogic.gdx.Input.Buttons;

public class PlayerInputProcessor implements InputProcessor {

    private Player player;
    private OrthographicCamera camera;
    private Viewport viewport;

    @SuppressWarnings("WeakerAccess")
    public PlayerInputProcessor(Player player,
                                OrthographicCamera camera,
                                Viewport viewport) {
        this.player = player;
        this.camera = camera;
        this.viewport = viewport;
    }

    private static boolean isOutOfBounds(Vector2 v) {
        return v.x < 0 || v.x > World.WIDTH || v.y < 0 || v.y > World.HEIGHT;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        /* Touch and mouse is the same */
        switch (button) {
            case Buttons.LEFT:
                Vector2 target = viewport.unproject(new Vector2(screenX, screenY));
                if (isOutOfBounds(target)) break;
                MoveAbility moveAbility = player.getMoveAbility();
                moveAbility.setTargetVector(target);
                moveAbility.setTarget(true);
                break;
        }
        System.out.println(camera.viewportHeight);
        System.out.println(camera.viewportWidth);
        System.out.println(screenY);
        System.out.println(screenX);
        System.out.println(viewport.unproject(new Vector2(screenX, screenY)));
        System.out.println();

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
