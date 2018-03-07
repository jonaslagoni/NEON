/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neon.enemy;

import com.badlogic.gdx.math.Vector2;
import com.neon.libary.MoveAbility;
import com.neon.libary.World;
import com.neon.libary.interfaces.Controller;
import com.neon.player.Player;

/**
 * @author Daniel
 */
public class EnemyController implements Controller {

    private World world;

    EnemyController(World world) {
        this.world = world;
    }

    private void updateEnemy(Enemy enemy) {
        Player player = world.getEntities(Player.class).stream().findFirst().orElse(null);
        if (player == null) return;

        Vector2 playerPosition = player.getSprite().getPosition();
        MoveAbility ability = enemy.getMoveAbility();
        ability.setTargetVector(playerPosition);
        ability.setTarget(true);
    }

    @Override
    public void update() {
        world.getEntities(Enemy.class).forEach(this::updateEnemy);
    }
}
