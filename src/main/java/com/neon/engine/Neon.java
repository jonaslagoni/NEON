package com.neon.engine;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.neon.collision.CollisionPlugin;
import com.neon.enemy.EnemyPlugin;
import com.neon.libary.GameData;
import com.neon.libary.MoveController;
import com.neon.libary.Sprite;
import com.neon.libary.World;
import com.neon.libary.interfaces.Controller;
import com.neon.libary.interfaces.Drawable;
import com.neon.libary.interfaces.Plugin;
import com.neon.life.LifePlugin;
import com.neon.neon_coin.NeonCoinPlugin;
import com.neon.pathfinding.PathfindingPlugin;
import com.neon.player.PlayerPlugin;
import com.neon.projectile.ProjectilePlugin;
import com.neon.targeting.TargetingPlugin;
import com.neon.tower.TowerPlugin;
import com.neon.ui.HUD;
import com.neon.wave.WavePlugin;
import com.neon.weapon.WeaponPlugin;
import map.MapPlugin;

import java.util.Arrays;
import java.util.List;

import static com.badlogic.gdx.math.MathUtils.radDeg;
import static com.neon.libary.vectors.VectorUtils.angle;

public class Neon extends Game {



    SpriteBatch batch;
    Skin skin;


    @Override
    public void create() {
        skin = new Skin(Gdx.files.internal("skin.json"), new TextureAtlas(Gdx.files.internal("assets/assets.atlas")));

        batch = new SpriteBatch();

        this.setScreen(new MainMenuScreen(this));
    }

    @Override
    public void resize(int width, int height) {

    }

    /**
     * Main Loop that runs before each frame
     */
    @Override
    public void render() {
        super.render();
    }


    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    public void endGame() {
        this.setScreen(new EndScreen(this));
    }
}

