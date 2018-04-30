package com.wave;

import com.library.interfaces.IEntityFactory;
import com.library.interfaces.Targetable;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by sam on 12-03-2018.
 */
public class WaveService implements IWaveService {

    private int waveDifficulty = 512;

    private IEntityFactory factory;

    public void setFactory(IEntityFactory factory) {
        this.factory = factory;
    }

    public void removeFactory(IEntityFactory factory) {
        this.factory = null;
    }

    @Override
    public Queue<Targetable> createWave() {

        System.out.println(factory);
        LinkedList<Targetable> enemyList = new LinkedList<>();

        while (enemyList.stream().mapToInt(Targetable::getHp).sum() < waveDifficulty) {
            Targetable targetable = factory.createEntity();
            enemyList.add(targetable);
        }

        waveDifficulty *= 2;
        return enemyList;
    }
}
