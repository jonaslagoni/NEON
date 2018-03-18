package com.neon.libary.interfaces;

import com.neon.libary.vectors.Vector2f;

public interface IProjectileService extends Service {

    void newProjectile(Vector2f spawn, Vector2f target);
}