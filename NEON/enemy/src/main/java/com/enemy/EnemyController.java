/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.enemy;

import com.library.interfaces.Controller;
import com.library.interfaces.ILifeService;
import com.library.interfaces.INeonService;
import com.library.interfaces.IPathFindingService;
import com.library.vectors.Vector2f;
import com.library.vectors.Vector2i;
import static com.library.vectors.VectorUtils.distance;
import static com.library.vectors.VectorUtils.translateVelocity;
import com.library.interfaces.IWorldService;

public class EnemyController implements Controller {

    private INeonService wallet;
    private ILifeService lifeService;
    private IPathFindingService pathFindingService;
    private IWorldService world;

    public void setWallet(INeonService wallet) {
        this.wallet = wallet;
    }

    public void removeWallet(INeonService wallet) {
        this.wallet = null;
    }

    public void setLifeService(ILifeService lifeService) {
        this.lifeService = lifeService;
    }

    public void removeLifeService(ILifeService lifeService) {
        this.lifeService = null;
    }

    public void setPathFindingService(IPathFindingService pathFindingService) {
        this.pathFindingService = pathFindingService;
    }

    public void removePathFindingService(IPathFindingService pathFindingService) {
        this.pathFindingService = null;
    }

    public void setWorld(IWorldService world) {
        this.world = world;
    }

    public void removeWorld(IWorldService world) {
        this.world = null;
    }

    private void updateEnemy(final Enemy enemy, float dt) {

        enemy.damageTimer += dt;

        Vector2f position = enemy.sprite.getPosition();
        Vector2f velocity = enemy.sprite.getVelocity();
        Vector2f target = enemy.moveAbility.getTarget();

        Vector2i start = IWorldService.gridProject(enemy.getSprite().getPosition()); // Find current grid position
        Vector2i end = new Vector2i(8, 0); // Find goal grid position
        /* Remove enemy if it is at goal */
        if (start.equals(end)) {
            world.removeEntity(enemy);
            lifeService.subtractLife(enemy.damage);
            return;
        }
        /* Generate path if it doesn't have one */
        if (enemy.path == null) {
            enemy.path = pathFindingService.findPath(start, end);
        }
        /* Move to next step in the path if it has reached the step */
        if (!enemy.path.isEmpty() && distance(position, enemy.path.element()) < 6) {
            enemy.path.remove();
        }
        /* Set target vector */
        if (!enemy.path.isEmpty()) {
            enemy.moveAbility.setTarget(enemy.path.element());
        }
        enemy.moveAbility.setMove(true);
        enemy.sprite.setVelocity(translateVelocity(position, target, velocity));

        /* If enemy is killed */
        if (enemy.hp <= 0) {
            world.removeEntity(enemy);
            wallet.addCoins(enemy.coinValue);
            return;
        }
        /* Set texture based on hp */
        enemy.sprite.setTexture(enemy.textures[enemy.hp * (enemy.textures.length - 1) / enemy.maxHp]);
    }

    @Override
    public void update(float dt) {
        world.getEntities(Enemy.class).forEach(enemy -> updateEnemy(enemy, dt));
    }
}
