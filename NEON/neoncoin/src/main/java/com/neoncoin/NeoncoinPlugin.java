/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neoncoin;

import com.library.interfaces.IGameData;
import com.library.interfaces.INeonService;
import com.library.interfaces.Plugin;

/**
 *
 * @author emil
 */
public class NeoncoinPlugin implements Plugin {

    private IGameData gameData;
    private INeonService neonWallet;
    private NeonStatusText statusText;

    public void setGameData(IGameData gameData) {
        this.gameData = gameData;
    }

    public void removeGameData(IGameData gameData) {
        this.gameData = null;
    }

    public void setNeonWallet(INeonService iNeonService) {
        this.neonWallet = iNeonService;
    }

    public void removeNeonWallet(INeonService iNeonService) {
        this.neonWallet = null;
    }

    @Override
    public void start() {
        statusText = new NeonStatusText("Coins", neonWallet);
        gameData.addStatusText(statusText);
    }

    @Override
    public void stop() {
        gameData.removeStatusText(statusText);
    }
}
