package com.neon.neon_coin;

import com.neon.libary.interfaces.INeonWallet;

/**
 * Created by sam on 15-03-2018.
 */
public class NeonCoin implements INeonWallet {

    private int neonCoins = 0;

    @Override
    public boolean subtractCoins(int i) {
        if (neonCoins - i >= 0) {
            neonCoins -= i;
            return true;
        }
        return false;
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
