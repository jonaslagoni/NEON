/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wave;

import com.library.interfaces.Controller;
import com.library.interfaces.Entity;
import com.library.interfaces.IWorldService;
import com.library.interfaces.Targetable;
import java.util.Queue;

/**
 *
 * @author emil
 */
public class WaveController implements Controller {

    private Queue<Targetable> wave;
    private final float WAVE_COOLDOWN = 20;

    private float waveCounter = 0;
    private float entityCooldown = 0;
    private IWaveService waveService;
    private IWorldService world;

    @Override
    public void update(float dt) {
        entityCooldown += dt;
        waveCounter += dt;
        /* Generate new Wave */
        if (waveCounter > WAVE_COOLDOWN) {
            wave = waveService.createWave();
            waveCounter = 0;
        }

        /* Spawn new enemy */
        if (entityCooldown > 1 && wave != null && !wave.isEmpty()) {
            world.addEntity((Entity) wave.remove());
            entityCooldown = 0;
            waveCounter = 0;
        }
    }

    public void addWaveService(IWaveService waveService) {
        this.waveService = waveService;
    }

    public void removeWaveService(IWaveService waveService) {
        this.waveService = null;
    }

    public void addWorld(IWorldService world) {
        this.world = world;
    }

    public void removeWorld(IWorldService world) {
        this.world = null;
    }
}
