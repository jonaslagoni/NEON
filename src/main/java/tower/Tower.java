package tower;

import com.neon.main.entities.Drawable;
import com.neon.main.entities.Sprite;

public class Tower implements Drawable {

    private Sprite sprite;
    private int level;

    @SuppressWarnings("WeakerAccess")
    public Tower(Sprite sprite) {
        this.sprite = sprite;
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
}
