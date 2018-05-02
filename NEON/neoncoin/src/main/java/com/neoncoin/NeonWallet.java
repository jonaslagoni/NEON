package com.neoncoin;

import com.library.interfaces.IGameData;
import com.library.interfaces.INeonService;
import com.library.interfaces.IStatusText;

/**
 * Created by sam on 15-03-2018.
 */
public class NeonWallet implements INeonService {

    private int neoncoins = 100;
    private IStatusText status = () -> "Coins: " + getCoins();
    private IGameData gameData;


    @Override
    public boolean subtractCoins(int i) {
        if (neoncoins - i >= 0) {
            neoncoins -= i;
            return true;
        }
        return false;
    }

    public void start() {
        gameData.addStatusText(status);
    }

    public void stop() {
        gameData.removeStatusText(status);
    }

    @Override
    public void addCoins(int i) {
        neoncoins += i;
    }

    @Override
    public int getCoins() {
        return neoncoins;
    }

    @Override
    public void setCoins(int i) {
        neoncoins = i;
    }

    public void setGameData(IGameData gameData) {
        this.gameData = gameData;
    }

    public void removeGameData(IGameData gameData) {
        this.gameData = null;
    }
}
