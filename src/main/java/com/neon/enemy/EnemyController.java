/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neon.enemy;

import com.badlogic.gdx.Gdx;
import com.neon.libary.GameData;
import com.neon.libary.World;
import com.neon.libary.interfaces.*;
import com.neon.player.Player;
import com.neon.wave.Wave;
import com.neon.projectile.Projectile;

import static com.badlogic.gdx.math.MathUtils.PI;
import com.badlogic.gdx.math.Vector2;
import com.neon.common.search.Path;
import com.neon.common.search.Step;
import static com.neon.libary.VectorUtils.angle;

import java.util.List;


public class EnemyController implements Controller {

    private final World world;
    private final ICollisionService collisionService;
    private float enemyCooldown;
    private float waveCooldown;
    private IWaveService iWaveService;
    private int enemyListPos;
    List<Entity> enemyList;
    private int enemyDeathCount;

    EnemyController(World world, GameData gameData) {
        this.collisionService = gameData.getService(ICollisionService.class);
        this.world = world;
        this.iWaveService = new Wave(world, gameData);
        this.enemyList = iWaveService.createWave();
    }

    private void updateEnemy(final Enemy enemy) {

        /* Don't Move if player is on target.
         * It is not necessary to calculate the actual distance, just the square of it. */
        // if ( distanceSquare(enemy.sprite.getPosition(), enemy.moveAbility.getTargetVector()) < 2)

        /* Calculate angle
         * https://stackoverflow.com/questions/21483999/using-atan2-to-find-angle-between-two-vectors */

        enemy.damageTimer += Gdx.graphics.getDeltaTime();
        
        if(enemy.path == null){
            Vector2 gridVector2Start = world.getPositionGridCell(enemy.getSprite().getPosition());
            Vector2 gridVector2End = world.getPositionGridCell(World.END);
            Path path = world.getFinder().findPath(null, gridVector2Start, gridVector2End);
            if(path == null){
                world.removeEntity(enemy);
            }else{
                enemy.path = path;
                enemy.counter = 0;
            }
        }
        float difx = Math.abs(enemy.moveAbility.getTargetVector().x-Math.abs(enemy.getSprite().getPosition().x));
        float dify = Math.abs(enemy.moveAbility.getTargetVector().y-Math.abs(enemy.getSprite().getPosition().y));
        //check if we should find a new position
        if(difx < 10 && dify < 10){
            Step next = enemy.path.getStep(enemy.counter++);
            if(next != null){
                float distX = next.getX()*World.GRID_CELL_SIZE+(World.GRID_CELL_SIZE/2);
                float distY = next.getY()*World.GRID_CELL_SIZE+(World.GRID_CELL_SIZE/2);
                enemy.moveAbility.setTargetVector(new Vector2(distX, distY));
                float angleToLocation = angle(enemy.sprite.getPosition(), enemy.moveAbility.getTargetVector())+PI;
                enemy.sprite.setRotation(angleToLocation);
            }
        }
        
        /* Remove enemy if it collides with player */
        for (Entity entity : collisionService.getCollisions(enemy.sprite)) {
            if (enemy.damageTimer >= 1 && entity.getClass() == Player.class) {
                enemy.hp -= 10;
                enemy.damageTimer = 0;
            } else if (entity.getClass() == Projectile.class) {
                enemy.hp -= 25;
                world.removeEntity(entity);
            }
        }

        if (enemy.hp <= 0) {
            enemyDeathCount--;
            world.removeEntity(enemy);
            return;
        }
        
        if (enemy.getSprite().getPosition().y < 0){
            enemyDeathCount--;
            world.removeEntity(enemy);
            return;
        }

        enemy.sprite.setTexture(enemy.textures[enemy.hp * (enemy.textures.length - 1) / enemy.maxHp]);
    }

    @Override
    public void update() {
        // Add enemies iterator here.


        enemyCooldown += Gdx.graphics.getDeltaTime();


        if (enemyCooldown > 1 && enemyListPos < enemyList.size()) {

            world.addEntity(enemyList.get(enemyListPos++));
            enemyCooldown = 0;
        }

        if (enemyDeathCount <= 0) {

            waveCooldown += Gdx.graphics.getDeltaTime();

            if (waveCooldown > 20) {

                enemyList = iWaveService.createWave();
                enemyDeathCount = enemyList.size();
                enemyListPos = 0;
                waveCooldown = 0;
            }
        }
        world.getEntities(Enemy.class).forEach(this::updateEnemy);
    }
}
