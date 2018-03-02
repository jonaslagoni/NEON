package com.neon.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.neon.tower.Tower1;


public class TowerPlacementInputProcessor implements InputProcessor {
    
    private Tower1 tower;
    private Test test;
    
    @Override
    public boolean keyDown(int keycode) {
        if(Keys.ESCAPE == keycode){
            tower = null;
            test.reset();
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
        if(Buttons.LEFT == button){
            if(tower != null){
                TowerController.getInstance().addTower(tower);
                tower = null;
                test.reset();
            }
        }else if(Buttons.RIGHT == button){
            tower = null;
            test.reset();
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
        if(tower != null){
            int posx = screenX - (int)(tower.getWidth()/2);
            int posy = screenY-Gdx.graphics.getHeight() + (int)(tower.getHeight()/2);
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
     * @param tower the tower to set
     */
    public void setTower(Tower1 tower) {
        this.tower = tower;
    }

    /**
     * @param test the test to set
     */
    public void setTest(Test test) {
        this.test = test;
    }

}








