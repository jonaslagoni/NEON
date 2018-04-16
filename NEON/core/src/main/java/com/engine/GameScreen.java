package com.engine;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.library.GameData;
import com.library.interfaces.Drawable;
import static com.badlogic.gdx.math.MathUtils.radDeg;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.library.Sprite;
import com.library.interfaces.Controller;
import com.library.interfaces.Entity;
import com.library.interfaces.IAssetManager;
import com.library.interfaces.IWorldService;
import com.library.interfaces.Moveable;
import com.library.interfaces.Plugin;
import static com.library.vectors.VectorUtils.angle;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
public class GameScreen extends Game {
    private final static OrthographicCamera CAMERA = new OrthographicCamera();
    public final static Viewport VIEWPORT = new ExtendViewport(IWorldService.WIDTH, IWorldService.HEIGHT, CAMERA);   
    private final List<Controller> entityProcessorList = new CopyOnWriteArrayList<>();
    private final List<Plugin> gamePluginList = new CopyOnWriteArrayList<>();
    private IAssetManager assetManager;
    private IWorldService world;
    private boolean speedUp;
    private Texture bg;
    private GameData gameData;
    private HUD hud;
    SpriteBatch batch;
    Skin skin;
    
    public GameScreen() {
        
        init();
    }
    
    private void init() {

        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
//        config.setFromDisplayMode(LwjglApplicationConfiguration.getDesktopDisplayMode());
        config.height = 768;
        config.width = 1024;
        config.resizable = false;
        new LwjglApplication(this, config);
    }
    
    private void drawEntity(Drawable drawable) {
        if(assetManager == null){
            Sprite sprite = drawable.getSprite();
            Texture texture = ((AssetManager)assetManager).getTexture(sprite.getTexture());
            batch.draw(
                    texture, // The texture to use
                    sprite.getPosition().getX() - texture.getWidth() / 2, // Position x to draw
                    sprite.getPosition().getY() - texture.getHeight() / 2, // Position y to draw
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
    }
    
    @Override
    public void render() {
        super.render();
        float delta = Gdx.graphics.getDeltaTime();
        entityProcessorList.forEach(controller -> controller.update(speedUp ? delta * 2 : delta));
        
        
        /* Clear screen*/
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        VIEWPORT.apply();
        
        CAMERA.update();
        batch.setProjectionMatrix(CAMERA.combined);
        
        batch.begin();

        /* Draw background */
        if(assetManager != null){
            batch.draw(((AssetManager)assetManager).getTexture("bg"), 0, 0, IWorldService.WIDTH, IWorldService.HEIGHT);
        }

        /* Draw all entities to screen*/
        if(world != null){
            world.getEntities(Drawable.class).forEach(this::drawEntity);
            for(Moveable e: world.getEntities(Moveable.class)){
                batch.draw(((AssetManager)assetManager).getTexture("bg"), e.getSprite().getPosition().getX(), e.getSprite().getPosition().getY(), 100, 100);
            }
        }
        batch.end();
    }
    
    
    @Override
    public void resize(int width, int height) {
        VIEWPORT.update(width, height, true);
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

    @Override
    public void create() {
        
        skin = new Skin(Gdx.files.internal("skin.json"),
                new TextureAtlas(Gdx.files.internal("assets/assets.atlas")));
        batch = new SpriteBatch();
        gameData = new GameData();
        
        hud = new HUD(batch, skin, gameData);
        hud.start();
        
        Gdx.input.setInputProcessor(new InputProcessor() {
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
    public void setWorld(IWorldService world){
        this.world = world;
    }
    public void removeWorld() {
        this.world = null;
    }
    public void setAssetManager(IAssetManager assetManager) {
        this.assetManager = assetManager;
        try (InputStream stream = getClass().getClassLoader().getResourceAsStream("background2048.png")) {
            byte[] bs = new byte[stream.available()];
            stream.read(bs);
            this.assetManager.loadAsset("bg", bs);
        } catch (IOException ex) {
            System.err.println("Error loading texture");
            ex.printStackTrace();
        }
    }
    public void removeAssetManager() {
        this.assetManager = null;
    }
    
    public void addEntityProcessingService(Controller eps) {
        this.entityProcessorList.add(eps);
    }

    public void removeEntityProcessingService(Controller eps) {
        this.entityProcessorList.remove(eps);
    }

    public void addGamePluginService(Plugin plugin) {
        this.gamePluginList.add(plugin);
        plugin.start();

    }

    public void removeGamePluginService(Plugin plugin) {
        this.gamePluginList.remove(plugin);
        plugin.stop();
    }
}
