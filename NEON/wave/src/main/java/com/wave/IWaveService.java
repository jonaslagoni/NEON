package com.wave;

import com.library.interfaces.Targetable;

import java.util.Queue;

/**
 * Created by sam on 12-03-2018.
 */
public interface IWaveService {

    Queue<Targetable> createWave();

}
