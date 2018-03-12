package com.neon.wave;

import com.neon.enemy.EnemyFactory;
import com.neon.libary.GameData;
import com.neon.libary.World;
import com.neon.libary.interfaces.Entity;
import com.neon.libary.interfaces.IWaveService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sam on 12-03-2018.
 */
public class Wave implements IWaveService {

    private World world;
    private GameData gameData;
    private int waveCount = 30;
    private int waveScore = 0;
    private int currentTier;

    private final int[] tierScore;

    public Wave(World world, GameData gameData) {
        this.world = world;
        this.gameData = gameData;

        this.tierScore = new int[4];
        tierScore[0] = 1;
        tierScore[1] = 20;
        tierScore[2] = 75;
        tierScore[3] = 150;

    }

    public ArrayList<Entity> createWave() {

        ArrayList<Entity> enemyList = new ArrayList<>();

        waveCount++;

        waveScore = (int) (Math.ceil(waveCount + waveCount * 2.0));

        System.out.println(waveScore);

        while (waveScore > 0) {
            if (waveScore >= tierScore[3]) {

                waveScore = waveScore - tierScore[3];
                enemyList.add(EnemyFactory.createEnemy("tier3", "star"));


            } else if (waveScore >= tierScore[2]) {

                waveScore = waveScore - tierScore[2];
                enemyList.add(EnemyFactory.createEnemy("tier3", "star"));

            } else if (waveScore >= tierScore[1]) {

                waveScore = waveScore - tierScore[1];
                enemyList.add(EnemyFactory.createEnemy("tier2", "hexagon"));

            } else if (waveScore >= tierScore[0]) {

                waveScore = waveScore - tierScore[0];
                enemyList.add(EnemyFactory.createEnemy("tier1", "circle"));
            }
        }


        return enemyList;
    }
/*
    private int getFibonacci(int n) {
        if (n == 0 || n == 1) {
            return n;
        } else {
            return getFibonacci(n - 1) + getFibonacci(n - 2);
        }
    }

    private int getNumberOfEnemies() {
        return (3 + getFibonacci(waveCount / currentTier));
    }*/

}