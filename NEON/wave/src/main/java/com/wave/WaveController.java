/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wave;

import com.library.interfaces.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Spawns enemies at an interval
 *
 * @author emil
 */
public class WaveController implements Controller {

    private static final float WAVE_COOLDOWN = 20;
    private float waveTimer = 0;
    private int waveNumber = 0;
    private float entityTimer = 0;
    private int waveDifficulty = 512;
    private IWorldService world;
    private Queue<Targetable> wave;
    private List<IEntityFactory> factories = new CopyOnWriteArrayList<>();
    private IGameData gameData;
    private IStatusText countStatus = () -> "Wave Nr.: " + waveNumber;
    private IStatusText cooldownStatus = () -> "Wave Countdown: " + Math.ceil(WAVE_COOLDOWN - waveTimer);

    @Override
    public void update(float dt) {
        entityTimer += dt;
        waveTimer += dt;
        /* Generate new Wave */
        if (waveTimer > WAVE_COOLDOWN) {
            wave = createWave();
            waveTimer = 0;
        }

        /* Spawn new enemy */
        if (entityTimer > 1 && wave != null && !wave.isEmpty()) {
            world.addEntity((Entity) wave.remove());
            entityTimer = 0;
            waveTimer = 0;
        }
    }

    private Queue<Targetable> createWave() {

        if (factories.isEmpty()) {
            return new LinkedList<>();
        }

        LinkedList<Targetable> enemyList = new LinkedList<>();

        while (enemyList.stream().mapToInt(Targetable::getHp).sum() < waveDifficulty) {
            Targetable targetable = factories.get(0).createEntity();
            enemyList.add(targetable);
        }

        waveDifficulty *= 2;
        return enemyList;
    }

    /**
     * Apply needed resources to other components
     */
    public void start() {
        gameData.addStatusText(countStatus);
        gameData.addStatusText(cooldownStatus);
    }

    /**
     * Clean up before stopping component
     */
    public void stop() {
        gameData.removeStatusText(countStatus);
        gameData.removeStatusText(cooldownStatus);
    }

    public void setWorld(IWorldService world) {
        this.world = world;
    }

    public void removeWorld(IWorldService world) {
        this.world = null;
    }

    public void addFactory(IEntityFactory factory) {
        factories.add(factory);
    }

    public void removeFactory(IEntityFactory factory) {
        factories.remove(factory);
    }

    public void setGameData(IGameData gameData) {
        this.gameData = gameData;
    }

    public void removeGameData(IGameData gameData) {
        this.gameData = null;
    }

}
