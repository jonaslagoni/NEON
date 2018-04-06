/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.library.interfaces.IInputService;
import com.library.vectors.Vector2f;

/**
 *
 * @author emil
 */
public class InputService implements IInputService {

    @Override
    public boolean isClicked() {
        return Gdx.input.justTouched();
    }

    @Override
    public Vector2f getCords() {
        int x = Gdx.input.getX();
        int y = Gdx.input.getY();
        Vector2 w = new Vector2(x, y);
        Vector2 v = GameScreen.VIEWPORT.unproject(w);
        return new Vector2f(v.x, v.y);
    }
}
