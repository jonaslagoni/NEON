package com.neon.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.neon.tower.Tower;


public class TowerPlacementInputProcessor implements InputProcessor, ITowerPlacementProcessor {

    private Tower tower;
    private IUiController uiController;

    @Override
    public boolean keyDown(int keycode) {
        if (Keys.ESCAPE == keycode) {
            uiController.deselectTowerPlacement(tower);
            tower = null;
        }
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
        if (Buttons.LEFT == button) {
            if (tower != null) {
                TowerController.getInstance().addTower(tower);
                uiController.deselectTowerPlacement(tower);
                tower = null;
            }
        } else if (Buttons.RIGHT == button) {
            uiController.deselectTowerPlacement(tower);
            tower = null;
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
        if (tower != null) {
            int posx = screenX - (int) (tower.getWidth() / 2);
            int posy = screenY - Gdx.graphics.getHeight() + (int) (tower.getHeight() / 2);
            posy *= -1;
            tower.setPosition(posx, posy);
        }
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }


    /**
     * @param uiController the uiController to set
     */
    public void setUiController(IUiController uiController) {
        this.uiController = uiController;
    }

    @Override
    public void setSelectedTower(Tower tower) {
        this.tower = tower;
    }

}








