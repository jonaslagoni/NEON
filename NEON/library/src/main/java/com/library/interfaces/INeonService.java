package com.library.interfaces;

/**
 * Created by sam on 15-03-2018.
 */
public interface INeonService  {

    /**
     * Subtracts coins from player if a tower has been set or upgraded
     * @param i as integer
     * @return true if coin is subtracted coins
     */
    boolean subtractCoins(int i);

    /**
     * NO USAGE?
     * @param i 
     */
    void addCoins(int i);

    /**
     * Gets the coins each time update() is called
     * hvor tit bliver den kaldt???? På tid eller når der sker et event??
     * @return coins as an integer
     */
    int getCoins();

    /**
     * NO USAGE?
     * @param i 
     */
    void setCoins(int i);
}
