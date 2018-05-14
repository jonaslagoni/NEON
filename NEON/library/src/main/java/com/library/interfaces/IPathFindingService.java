package com.library.interfaces;

import com.library.vectors.Vector2f;
import com.library.vectors.Vector2i;

import java.util.Queue;

public interface IPathFindingService {

    /**
     * Returns a queue of vectors with a valid path
     *
     * @param start vector
     * @param goal  vector
     * @return queue
     */
    Queue<Vector2f> findPath(Vector2i start, Vector2i goal);
}
