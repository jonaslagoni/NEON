package com.library.interfaces;

import com.library.TowerType;
import com.library.vectors.Vector2f;

public interface ITowerService extends Service {

    void upgrade(Entity entity);

    void placeTower(Vector2f pos, TowerType key);
}
