package map;

import com.neon.libary.Sprite;
import com.neon.libary.interfaces.Drawable;

public class Obstacle implements Drawable {

    private Sprite sprite;

    Obstacle(Sprite sprite) {
        this.sprite = sprite;
    }

    @Override
    public Sprite getSprite() {
        return sprite;
    }
}
