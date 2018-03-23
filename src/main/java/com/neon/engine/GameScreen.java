package com.neon.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.neon.collision.CollisionPlugin;
import com.neon.enemy.EnemyPlugin;
import com.neon.libary.GameData;
import com.neon.libary.MoveController;
import com.neon.libary.Sprite;
import com.neon.libary.World;
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

public class GameScreen implements Screen {

    private final Neon game;
    private final World world = new World();
    private final OrthographicCamera camera = new OrthographicCamera();
    private final Viewport viewport = new ExtendViewport(World.WIDTH, World.HEIGHT, camera);
    private Texture bg;
    private HUD hud;
    private GameData gameData;
    private boolean speedUp;

    GameScreen(final Neon game) {
        this.game = game;
        gameData = new GameData(game.skin, viewport, game);
        Gdx.input.setInputProcessor(gameData.getMultiplexer());

        hud = new HUD(world, gameData, game.batch);
        List<Plugin> plugins = Arrays.asList(
                new PathfindingPlugin(gameData, world),
                new NeonCoinPlugin(world, gameData),
                new TowerPlugin(world, gameData),
                new CollisionPlugin(world, gameData),
                new LifePlugin(world, gameData),
                new WavePlugin(world, gameData),
                new EnemyPlugin(world, gameData),
                hud,
                new PlayerPlugin(world, gameData),
                new ProjectilePlugin(world, gameData),
                new WeaponPlugin(world, gameData),
                new TargetingPlugin(world, gameData),
                new MapPlugin(world, gameData)
        );
        gameData.addController(new MoveController(world));

        bg = new Texture(Gdx.files.internal("images/background2048.png"));

        /* Start plugins */
        plugins.forEach(Plugin::start);

        gameData.addInputProcessor(new InputProcessor() {
            @Override
            public boolean keyDown(int keycode) {
                switch (keycode) {
                    case Input.Keys.SPACE:
                        speedUp = !speedUp;
                        return true;
                }
                return false;
            }

            @Override
            public boolean keyUp(int keycode) {
                return false;
            }

            @Override
            public boolean keyTyped(char character) {
                return false;
            }

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                return false;
            }

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                return false;
            }

            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) {
                return false;
            }

            @Override
            public boolean mouseMoved(int screenX, int screenY) {
                return false;
            }

            @Override
            public boolean scrolled(int amount) {
                return false;
            }
        });

    }

    private void drawEntity(Drawable drawable) {
        Sprite sprite = drawable.getSprite();
        Texture texture = sprite.getTexture();
        game.batch.draw(
                texture, //The texture to use
                sprite.getPosition().getX() - texture.getWidth() / 2, //Position x to draw
                sprite.getPosition().getY() - texture.getHeight() / 2, //Position y to draw
                texture.getWidth() / 2,
                texture.getHeight() / 2,
                texture.getWidth(),
                texture.getHeight(),
                sprite.getWidth() / texture.getWidth(),
                sprite.getHeight() / texture.getHeight(),
                angle(sprite.getVelocity()) * radDeg,
                0, 0,
                texture.getWidth(),
                texture.getHeight(),
                false, false
        );
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        gameData.getControllers().forEach(controller -> controller.update(speedUp ? delta * 2 : delta));

        /* Clear screen*/
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        viewport.apply();

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();

        /* Draw background */
        game.batch.draw(bg, 0, 0, World.WIDTH, World.HEIGHT);

        /* Draw all entities to screen*/
        world.getEntities(Drawable.class).forEach(this::drawEntity);
        game.batch.end();

        /* Draw HUD*/
        hud.getStage().getViewport().apply();
        hud.getStage().draw();

    }

    @Override
    public void resize(int width, int height) {

        viewport.update(width, height);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);

        hud.getStage().getViewport().update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
