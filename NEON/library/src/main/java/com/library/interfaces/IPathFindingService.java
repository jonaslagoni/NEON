package com.library.interfaces;

import com.library.vectors.Vector2f;
import com.library.vectors.Vector2i;
import java.util.Queue;

public interface IPathFindingService  {

    Queue<Vector2f> findPath(Vector2i start, Vector2i goal);
}
