/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wave;

import com.library.interfaces.*;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author emil
 */
public class WaveController implements Controller {

    private final float WAVE_COOLDOWN = 20;
    private Queue<Targetable> wave;
    private float waveCounter = 0;
    private float entityCooldown = 0;
    private IWorldService world;

    private int waveDifficulty = 512;

    private IEntityFactory factory;

    public void setFactory(IEntityFactory factory) {
        this.factory = factory;
    }

    public void removeFactory(IEntityFactory factory) {
        this.factory = null;
    }

    @Override
    public void update(float dt) {
        entityCooldown += dt;
        waveCounter += dt;
        /* Generate new Wave */
        if (waveCounter > WAVE_COOLDOWN) {
            wave = createWave();
            waveCounter = 0;
        }

        /* Spawn new enemy */
        if (entityCooldown > 1 && wave != null && !wave.isEmpty()) {
            world.addEntity((Entity) wave.remove());
            entityCooldown = 0;
            waveCounter = 0;
        }
    }


    public void addWorld(IWorldService world) {
        this.world = world;
    }

    public void removeWorld(IWorldService world) {
        this.world = null;
    }

    private Queue<Targetable> createWave() {

        if (factory == null) {
            return new LinkedList<>();
        }

        LinkedList<Targetable> enemyList = new LinkedList<>();

        while (enemyList.stream().mapToInt(Targetable::getHp).sum() < waveDifficulty) {
            Targetable targetable = factory.createEntity();
            enemyList.add(targetable);
        }

        waveDifficulty *= 2;
        return enemyList;
    }
}
