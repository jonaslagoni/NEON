package com.library.interfaces;

import java.util.Queue;

/**
 * Created by sam on 12-03-2018.
 */
public interface IWaveService {

    Queue<Entity> createWave();

    int getWaveScore();

    int getWaveCount();
}