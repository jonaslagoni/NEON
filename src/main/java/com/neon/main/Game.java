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
import com.neon.ui.TowerController;
import com.neon.ui.UiController;

import static com.badlogic.gdx.math.MathUtils.radDeg;

public class Game implements ApplicationListener {

    private static SpriteBatch batch;
    private static ShapeRenderer shapeRenderer;
    private GameData gameData;
    private UiController ui;
    private World world;
    private OrthographicCamera camera;
    private Viewport viewport;

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

        camera = new OrthographicCamera();
        viewport = new FitViewport(World.WIDTH, World.HEIGHT, camera);
        viewport.apply();
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);

        gameData = new GameData();
        gameData.setCamera(camera);
        gameData.setViewport(viewport);

        ui = new UiController(gameData);
        world = new World();

        /* Sprite batch is used to render sprites on the gpu */
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();

        Plugin playerPlugin = new PlayerPlugin();
        playerPlugin.start(gameData, world);

        Plugin enemyPlugin = new EnemyPlugin();
        enemyPlugin.start(gameData, world);

        gameData.addController(new MoveController());
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
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        drawGrid();
        shapeRenderer.end();

        TowerController.getInstance().getStage().draw();

        /* Render all entities to screen*/
        batch.begin();
        world.getEntities(Drawable.class).forEach(Game::drawEntity);
        batch.end();
        ui.draw();
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
