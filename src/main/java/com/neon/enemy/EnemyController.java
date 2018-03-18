/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neon.enemy;

import com.badlogic.gdx.Gdx;
import com.neon.libary.World;
import com.neon.libary.interfaces.*;
import com.neon.libary.vectors.Vector2i;
import com.neon.player.Player;
import com.neon.projectile.Projectile;

import java.util.Queue;

import static com.neon.libary.vectors.VectorUtils.distance;
import static com.neon.libary.vectors.VectorUtils.translateVelocity;

public class EnemyController implements Controller {

    private final World world;
    private final ICollisionService collisionService;
    private final IWaveService iWaveService;
    private final INeonWallet wallet;
    private final IPathFindingService pathFindingService;
    private Queue<Entity> wave;
    private float enemyCooldown;
    private float waveCooldown;
    private int enemyDeathCount;

    EnemyController(World world,
                    ICollisionService collisionService,
                    INeonWallet wallet,
                    IWaveService waveService,
                    IPathFindingService pathFindingService) {
        this.collisionService = collisionService;
        this.world = world;
        this.iWaveService = waveService;
        this.pathFindingService = pathFindingService;
        this.wave = iWaveService.createWave();
        this.wallet = wallet;
    }

    private void updateEnemy(final Enemy enemy) {

        /* Don't Move if player is on target.
         * It is not necessary to calculate the actual distance, just the square of it. */
        // if ( distanceSquare(enemy.sprite.getPosition(), enemy.moveAbility.getTarget()) < 2)

        /* Calculate angle
         * https://stackoverflow.com/questions/21483999/using-atan2-to-find-angle-between-two-vectors */

        enemy.damageTimer += Gdx.graphics.getDeltaTime();

        Vector2i start = World.gridProject(enemy.getSprite().getPosition()); // Find current grid position
        Vector2i end = new Vector2i(8, 0); // Find goal grid position

        if (start.equals(end)) {
            enemyDeathCount--;
            world.removeEntity(enemy);
            return;
        }

        if (enemy.path == null) {
            enemy.path = pathFindingService.findPath(start, end);
        }

        if (!enemy.path.isEmpty() && distance(enemy.sprite.getPosition(), enemy.path.peek()) < 2) {
            enemy.path.remove();
        }

        // Set target vector
        if (!enemy.path.isEmpty()) {
            enemy.moveAbility.setTarget(enemy.path.element());
        }
        enemy.moveAbility.setMove(true);
        enemy.sprite.setVelocity(translateVelocity(enemy.sprite.getPosition(), enemy.moveAbility.getTarget(), enemy.sprite.getVelocity()));


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

        // If enemy is killed
        if (enemy.hp <= 0) {
            enemyDeathCount--;
            world.removeEntity(enemy);
            wallet.addCoins(enemy.coinValue);
            return;
        }

        // If enemy survives and leaves
        if (enemy.getSprite().getPosition().getY() < 0) {
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

        if (enemyCooldown > 1 && !wave.isEmpty()) {
            world.addEntity(wave.remove());
            enemyCooldown = 0;
        }

        if (enemyDeathCount <= 0) {

            waveCooldown += Gdx.graphics.getDeltaTime();

            if (waveCooldown > 20) {
                wave = iWaveService.createWave();
                enemyDeathCount = wave.size();
                waveCooldown = 0;
            }
        }
        world.getEntities(Enemy.class).forEach(this::updateEnemy);
    }
}
