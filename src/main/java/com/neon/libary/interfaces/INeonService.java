package com.neon.libary.interfaces;

/**
 * Created by sam on 15-03-2018.
 */
public interface INeonService extends Service {

    public boolean subtractCoins(int i);
    public void addCoins(int i);
    public int getCoins();
    public void setCoins(int i);
}
