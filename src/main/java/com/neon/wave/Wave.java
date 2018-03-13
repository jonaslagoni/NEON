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
    private int waveDifficulty = 0;
    private int waveScore = 0;
    private int waveCount = 0;

    private final int[] tierScore;

    public Wave(World world, GameData gameData) {
        this.world = world;
        this.gameData = gameData;

        this.tierScore = new int[4];
        tierScore[0] = 1;
        tierScore[1] = 20;
        tierScore[2] = 75;
        tierScore[3] = 200;

    }

    public ArrayList<Entity> createWave() {

        ArrayList<Entity> enemyList = new ArrayList<>();

        waveDifficulty++;
        waveCount++;

        waveScore = (int) (Math.ceil(waveDifficulty + waveDifficulty * 2.0));

        System.out.println(waveCount);

        while (waveScore > 0) {
            int randomType = (int)(Math.random()*3+1);
            int randomTypeTier2 = (int)(Math.random()*2+1);
            if (waveScore >= tierScore[3]) {

                waveScore = waveScore - tierScore[3];
                enemyList.add(EnemyFactory.createEnemy("boss", randomType));


            } else if (waveScore >= tierScore[2]) {

                waveScore = waveScore - tierScore[2];
                enemyList.add(EnemyFactory.createEnemy("tier3", randomType));

            } else if (waveScore >= tierScore[1]) {

                waveScore = waveScore - tierScore[1];
                enemyList.add(EnemyFactory.createEnemy("tier2", randomTypeTier2));

            } else if (waveScore >= tierScore[0]) {

                waveScore = waveScore - tierScore[0];
                enemyList.add(EnemyFactory.createEnemy("tier1", randomType));
            }
        }


        return enemyList;
    }
}