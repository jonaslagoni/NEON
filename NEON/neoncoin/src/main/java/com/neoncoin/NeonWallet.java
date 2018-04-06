package com.neoncoin;

import com.library.interfaces.INeonService;

/**
 * Created by sam on 15-03-2018.
 */
public class NeonWallet implements INeonService {

    private int neoncoins = 100;

    @Override
    public boolean subtractCoins(int i) {
        if (neoncoins - i >= 0) {
            neoncoins -= i;
            return true;
        }
        return false;
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
}
