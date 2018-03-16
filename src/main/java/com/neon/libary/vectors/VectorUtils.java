package com.neon.libary.vectors;

import com.badlogic.gdx.Gdx;

import static com.badlogic.gdx.math.MathUtils.*;
import static java.lang.Math.sqrt;

@SuppressWarnings({"unused", "WeakerAccess"})
public final class VectorUtils {

    private VectorUtils() {
    }

    public static float angle(Vector2f a, Vector2f b) {
        return atan2(a.getY() - b.getY(), a.getX() - b.getX());
    }

    public static float deltaX(float angle, float velocity) {
        return cos(angle) * velocity * Gdx.graphics.getDeltaTime();
    }

    public static float deltaY(float angle, float velocity) {
        return sin(angle) * velocity * Gdx.graphics.getDeltaTime();
    }

    public static float distanceSquare(Vector2f a, Vector2f b) {
        return ((a.getX() - b.getX()) * (a.getX() - b.getX())) + (a.getY() - b.getY()) * (a.getY() - b.getY());
    }

    public static float distance(Vector2f a, Vector2f b) {
        return (float) sqrt((a.getX() - b.getX()) * (a.getX() - b.getX()) + (a.getY() - b.getY()) * (a.getY() - b.getY()));
    }
}
