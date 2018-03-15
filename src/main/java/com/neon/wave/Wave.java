package com.neon.wave;

import com.neon.enemy.EnemyFactory;
import com.neon.libary.GameData;
import com.neon.libary.World;
import com.neon.libary.interfaces.Entity;
import com.neon.libary.interfaces.IWaveService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by sam on 12-03-2018.
 */
public class Wave implements IWaveService {

    private World world;
    private GameData gameData;
    private int waveDifficulty = 1;
    private int waveScore = 0;
    private int waveCount = 0;
    private int enemyScore;

    private final int[] tierScore;

    public Wave(World world, GameData gameData) {
        this.world = world;
        this.gameData = gameData;

        this.tierScore = new int[4];
        tierScore[0] = 1;
        tierScore[1] = 20;
        tierScore[2] = 100;
        tierScore[3] = 1000;

    }

    public int getWaveScore() {
        return enemyScore;
    }

    public int getWaveCount() {
        return waveCount;
    }

    public ArrayList<Entity> createWave() {

        ArrayList<Entity> enemyList = new ArrayList<>();

        waveDifficulty = waveDifficulty*3;
        waveCount++;

        waveScore += (int) (Math.ceil(waveDifficulty + waveDifficulty * waveCount));

        enemyScore = waveScore;

        System.out.println(waveCount);

        // Adds tier 2 every 10th wave
        if (waveCount% 10 == 0) {
            waveScore += tierScore[1]*2;
            // Adds tier 3 at 50th wave
            if (waveCount == 50) {
                waveScore += tierScore[2]*2;
            }
            // Adds bosses at 100th wave
            if (waveCount == 100) {
                waveScore += tierScore[3]*5;
            }
        }
        
        System.out.println("wave score = " + waveScore + " waveCount = " + waveCount + " waveDiff = " + waveDifficulty);

        while (waveScore > 0) {


            // Randomizes what enemy to spawn within tier
            int randomType = (int)(Math.random()*3+1);
            int randomTypeTier2 = (int)(Math.random()*2+1);

            // Spawns bosses if waveScore is high enough
            if (waveScore >= tierScore[3]) {

                waveScore = waveScore - tierScore[3];
                enemyList.add(EnemyFactory.createEnemy("boss", randomType));

            // Spawns tier 3 enemies if there is enough waveScore
            } else if (waveScore >= tierScore[2]) {

                waveScore = waveScore - tierScore[2];
                enemyList.add(EnemyFactory.createEnemy("tier3", randomType));

            // Spawns tier 2 enemies if there is enough waveScore
            } else if (waveScore >= tierScore[1]) {

                waveScore = waveScore - tierScore[1];
                enemyList.add(EnemyFactory.createEnemy("tier2", randomTypeTier2));

            // Spawns tier 1 enemies if there is any waveScore left
            } else if (waveScore >= tierScore[0]) {

                waveScore = waveScore - tierScore[0];
                enemyList.add(EnemyFactory.createEnemy("tier1", randomType));
            }
        }

        // Randomizes the arrayList of enemies
        Collections.shuffle(enemyList);
        
           


        return enemyList;
    }
}