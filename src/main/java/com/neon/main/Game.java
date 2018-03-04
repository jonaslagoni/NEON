package com.neon.main;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.neon.enemy.EnemyPlugin;
import com.neon.main.entities.Drawable;
import com.neon.main.entities.Sprite;
import com.neon.player.PlayerPlugin;
import com.neon.player.Tower.TowerPlugin;
import com.neon.ui.HUD;

import static com.badlogic.gdx.math.MathUtils.radDeg;

public class Game implements ApplicationListener {

    private static final OrthographicCamera camera = new OrthographicCamera();
    public static final Viewport viewport = new FitViewport(World.WIDTH, World.HEIGHT, camera);

    private static SpriteBatch batch;
    private static ShapeRenderer shapeRenderer;
    private GameData gameData;
    private HUD hud;
    private World world;
    private Texture bg;

    private static void drawEntity(Drawable drawable) {
        Sprite sprite = drawable.getSprite();
        Texture texture = sprite.getTexture();
        batch.draw(
                texture,
                sprite.getPosition().x - texture.getWidth() / 2,
                sprite.getPosition().y - texture.getHeight() / 2,
                texture.getWidth() / 2,
                texture.getHeight() / 2,
                texture.getWidth(),
                texture.getHeight(),
                sprite.getWidth() / texture.getWidth(),
                sprite.getHeight() / texture.getHeight(),
                sprite.getRotation() * radDeg,
                0, 0,
                texture.getWidth(),
                texture.getHeight(),
                false, false
        );
    }

    private static void drawGrid() {
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

        /* Set camera such that 0,0 is bottom left */
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);

        gameData = new GameData();
        world = new World();
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();

        Plugin enemyPlugin = new EnemyPlugin();
        enemyPlugin.start(gameData, world);

        Plugin towerPlugin = new TowerPlugin();
        towerPlugin.start(gameData, world);

        gameData.addController(new MoveController());

        hud = new HUD(gameData, world, batch);

        Plugin playerPlugin = new PlayerPlugin();
        playerPlugin.start(gameData, world);

        camera.zoom += 0.5;

        bg = new Texture(Gdx.files.internal("images/up-button.png"));
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
    }

    /**
     * Main Loop that runs before each frame
     */
    @Override
    public void render() {
        gameData.getControllers().forEach(controller -> controller.update(world));
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
        // shapeRenderer.end();

        viewport.apply();
        batch.begin();

        /* Draw background */
        batch.draw(bg, 0, 0, World.WIDTH, World.HEIGHT);

        /* Draw all entities to screen*/
        world.getEntities(Drawable.class).forEach(Game::drawEntity);
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
