package com.neon.libary.interfaces;

import com.neon.libary.TowerType;
import com.neon.libary.vectors.Vector2f;

public interface ITowerService extends Service {

    void upgrade(Entity entity);

    void placeTower(Vector2f pos, TowerType key);
}
