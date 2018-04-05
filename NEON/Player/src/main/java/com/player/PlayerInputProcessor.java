package com.player;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.neon.libary.GameData;
import com.neon.libary.World;
import com.neon.libary.vectors.Vector2f;

import static com.badlogic.gdx.Input.Buttons;

public class PlayerInputProcessor implements InputProcessor {

    private final Player player;
    private final GameData gameData;

    PlayerInputProcessor(Player player, GameData gameData) {
        this.player = player;
        this.gameData = gameData;
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
                Vector2 vector = gameData.getViewport().unproject(new Vector2(screenX, screenY));
                Vector2f target = new Vector2f(vector.x, vector.y);
                if (World.isOutOfBounds(target)) break;
                player.moveAbility.setTarget(target);
                return true;
        }
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
