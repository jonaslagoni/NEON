package com.library.interfaces;

/**
 * Created by sam on 15-03-2018.
 */
public interface INeonService {

    /**
     * Subtracts coins from player if a tower has been set or upgraded
     * @param i as integer
     * @return true if coin is successfully subtracted
     */
    boolean subtractCoins(int i);

    /**
     * Adds coins to wallet
     * @param i integer amount of coins
     */
    void addCoins(int i);

    /**
     * Returns amount of coins in wallet
     * @return amount of coins as an integer
     */
    int getCoins();

    /**
     * Sets coins to neonwallet
     * @param i amount
     */
    void setCoins(int i);
}
