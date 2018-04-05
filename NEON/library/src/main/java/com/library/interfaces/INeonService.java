package com.library.interfaces;

/**
 * Created by sam on 15-03-2018.
 */
public interface INeonService extends Service {

    boolean subtractCoins(int i);

    void addCoins(int i);

    int getCoins();

    void setCoins(int i);
}
