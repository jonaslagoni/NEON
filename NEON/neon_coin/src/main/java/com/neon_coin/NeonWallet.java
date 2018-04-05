package com.neon_coin;

import com.neon.libary.interfaces.INeonService;

/**
 * Created by sam on 15-03-2018.
 */
public class NeonWallet implements INeonService {

    private int neonCoins = 100;

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
