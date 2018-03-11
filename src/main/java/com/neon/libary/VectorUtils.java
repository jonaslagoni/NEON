package com.neon.libary;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import static com.badlogic.gdx.math.MathUtils.*;
import static java.lang.Math.sqrt;

@SuppressWarnings({"unused", "WeakerAccess"})
public final class VectorUtils {

    private VectorUtils() {
    }

    public static float angle(Vector2 a, Vector2 b) {
        return atan2(a.y - b.y, a.x - b.x);
    }

    public static float deltaX(float angle, float velocity) {
        return cos(angle) * velocity * Gdx.graphics.getDeltaTime();
    }

    public static float deltaY(float angle, float velocity) {
        return sin(angle) * velocity * Gdx.graphics.getDeltaTime();
    }

    public static float distanceSquare(Vector2 a, Vector2 b) {
        return (a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y);
    }

    public static float distance(Vector2 a, Vector2 b) {
        return (float) sqrt((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y));
    }
}