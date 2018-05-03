/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.life;

import com.library.interfaces.IGameData;
import com.library.interfaces.ILifeService;
import com.library.interfaces.IStatusText;

/**
 * @author Ejer
 */
public class Life implements ILifeService {

    private int life = 100;
    private IGameData gameData;
    private IStatusText status = () -> "Life: " + life;

    @Override
    public int subtractLife(int i) {
        life -= i;
//        if (life <= 0) {
//            gameData.endGame();
//        }
        return life;
    }

    @Override
    public int getLife() {
        return life;
    }


    public void start() {
        gameData.addStatusText(status);
    }

    public void stop() {
        gameData.removeStatusText(status);
    }

    public void setGameData(IGameData gameData) {
        this.gameData = gameData;
    }

    public void removeGameData(IGameData gameData) {
        this.gameData = null;
    }
}
