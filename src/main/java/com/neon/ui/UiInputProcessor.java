package com.neon.ui;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;


public class UiInputProcessor implements InputProcessor {

    /* How input works: https://github.com/libgdx/libgdx/wiki/Event-handling */

    private UI ui;

    public UiInputProcessor(UI ui) {
        this.ui = ui;
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Keys.SPACE:
                ui.toggleUi();
                break;
        }
        return true;
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








