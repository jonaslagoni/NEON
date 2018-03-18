package com.neon.engine;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
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
import com.neon.neon_coin.NeonCoinPlugin;
import com.neon.player.PlayerPlugin;
import com.neon.projectile.ProjectilePlugin;
import com.neon.tower.TowerPlugin;
import com.neon.ui.HUD;
import com.neon.wave.WavePlugin;
import com.neon.weapon.WeaponPlugin;
import pathfinding.PathfindingPlugin;

import java.util.Arrays;
import java.util.List;

import static com.badlogic.gdx.math.MathUtils.radDeg;
import static com.neon.libary.vectors.VectorUtils.angle;

public class Game implements ApplicationListener {

    private final OrthographicCamera camera = new OrthographicCamera();
    private final Viewport viewport = new ExtendViewport(World.WIDTH, World.HEIGHT, camera);
    private final World world = new World();
    private GameData gameData;
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private HUD hud;
    private Texture bg;

    private void drawEntity(Drawable drawable) {
        Sprite sprite = drawable.getSprite();
        Texture texture = sprite.getTexture();
        batch.draw(
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

    private void drawGrid() {
        int gap = Gdx.graphics.getHeight() / World.GRID_SPACES;
        shapeRenderer.setColor(Color.GREEN);
        for (int i = 1; i < World.GRID_SPACES; i++) {
            /* Vertical */
            shapeRenderer.line(i * gap, 0, i * gap, World.GRID_SPACES * gap);
            /* Horizontal */
            shapeRenderer.line(0, i * gap, World.GRID_SPACES * gap, i * gap);
        }
    }

    @Override
    public void create() {
        Skin skin = new Skin(Gdx.files.internal("skin.json"), new TextureAtlas(Gdx.files.internal("assets/assets.atlas")));
        gameData = new GameData(skin, viewport);

        Gdx.input.setInputProcessor(gameData.getMultiplexer());

        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();

        hud = new HUD(world, gameData, batch);
        List<Plugin> plugins = Arrays.asList(
                new PathfindingPlugin(gameData, world),
                new NeonCoinPlugin(world, gameData),
                new TowerPlugin(world, gameData),
                new CollisionPlugin(world, gameData),
                new WavePlugin(world, gameData),
                new EnemyPlugin(world, gameData),
                hud,
                new PlayerPlugin(world, gameData),
                new ProjectilePlugin(world, gameData),
                new WeaponPlugin(world, gameData)
        );
        gameData.addController(new MoveController(world));

        bg = new Texture(Gdx.files.internal("images/up-button.png"));

        /* Start plugins */
        plugins.forEach(Plugin::start);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        hud.getStage().getViewport().update(width, height);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
    }

    /**
     * Main Loop that runs before each frame
     */
    @Override
    public void render() {
        gameData.getControllers().forEach(Controller::update);
        draw();
    }

    private void draw() {
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        /* Clear screen*/
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        /* Draw grid */
        // shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        // drawGrid();
        // shapeRenderer.END();

        viewport.apply();
        batch.begin();

        /* Draw background */
        batch.draw(bg, 0, 0, World.WIDTH, World.HEIGHT);

        /* Draw all entities to screen*/
        world.getEntities(Drawable.class).forEach(this::drawEntity);
        batch.end();

        /* Draw HUD*/
        hud.getStage().getViewport().apply();
        hud.getStage().draw();
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
}
