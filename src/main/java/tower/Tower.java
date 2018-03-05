package tower;

import com.badlogic.gdx.graphics.Texture;
import com.neon.main.entities.Drawable;
import com.neon.main.entities.Sprite;

public class Tower implements Drawable {

    private Sprite sprite;
    private int level;
    private Texture[] texture;
    
    @SuppressWarnings("WeakerAccess")
    public Tower(Sprite sprite, Texture[] texture) {
        this.sprite = sprite;
        this.level = 1;
        this.texture = texture;
    }

    @Override
    public Sprite getSprite() {
        return sprite;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
    
    public void upgrade() {
        if(level < 4){
            level++;
            sprite.setTexture(texture[level-2]);
        }
    }
}
