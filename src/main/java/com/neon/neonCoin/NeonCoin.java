package com.neon.neonCoin;

import com.neon.libary.interfaces.INeonWallet;

/**
 * Created by sam on 15-03-2018.
 */
public class NeonCoin implements INeonWallet {

    private int neonCoins = 0;

    @Override
    public boolean subtractCoins(int i) {
        int a = neonCoins - i;
        if (a < 0) {
            return false;
        } else {
            neonCoins -= i;
            return true;
        }
    }

    @Override
    public void addCoins(int i) {
        neonCoins += i;
    }

    @Override
    public int getCoins() {
        return neonCoins;
    }

    @Override
    public void setCoins(int i) {
        neonCoins = i;
    }
}
