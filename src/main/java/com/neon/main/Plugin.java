package com.neon.main;

/**
 *
 */
public interface Plugin {
    /**
     * @param gameData game data
     */
    void start(GameData gameData, World world);

    /**
     * @param gameData game data
     */
    void stop(GameData gameData, World world);
}
