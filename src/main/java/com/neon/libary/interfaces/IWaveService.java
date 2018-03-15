package com.neon.libary.interfaces;

import java.util.List;

/**
 * Created by sam on 12-03-2018.
 */
public interface IWaveService extends Service{

    List<Entity> createWave();
    int getWaveScore();
    int getWaveCount();


}

