package com.neon.libary.interfaces;

import com.neon.libary.vectors.Vector2f;
import com.neon.libary.vectors.Vector2i;

import java.util.Queue;

public interface IPathFindingService extends Service {
    Queue<Vector2f> findPath(Vector2i start, Vector2i goal);
}
