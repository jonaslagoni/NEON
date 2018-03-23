/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neon.life;

import com.neon.libary.GameData;
import com.neon.libary.interfaces.ILifeService;

/**
 *
 * @author Ejer
 */
public class Life implements ILifeService {
    
    private int life = 20;
    private GameData gameData;

    public Life(int life, GameData gameData) {
        this.life = life;
        this.gameData = gameData;
    }

    @Override
    public int subtracLife(int i) {
        life -= i;
        if (life <= 0) {
            gameData.endGame();
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
