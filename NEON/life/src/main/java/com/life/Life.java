/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.life;

import com.library.interfaces.ILifeService;

/**
 * @author Ejer
 */
public class Life implements ILifeService {

    private int life;

    Life(int life) {
        this.life = life;
    }

    @Override
    public int subtractLife(int i) {
        life -= i;
        if (life <= 0) {
//            gameData.endGame();
        }
        return life;
    }

    @Override
    public int getLife() {
        return life;
    }

    @Override
    public void setLife() {
    }
}