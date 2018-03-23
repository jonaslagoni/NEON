package com.neon.libary.vectors;

import com.badlogic.gdx.math.MathUtils;

import static com.badlogic.gdx.math.MathUtils.atan2;
import static java.lang.Math.sqrt;

@SuppressWarnings("WeakerAccess")
public final class VectorUtils {

    private VectorUtils() {
    }

    public static float angle(Vector2f a, Vector2f b) {
        return atan2(a.y - b.y, a.x - b.x);
    }

    public static float angle(Vector2f v) {
        return atan2(v.y, v.x);
    }

    public static float distanceSquare(Vector2f a, Vector2f b) {
        return (a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y);
    }

    public static float distance(Vector2f a, Vector2f b) {
        return (float) sqrt((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y));
    }

    public static Vector2f difference(Vector2f a, Vector2f b) {
        return new Vector2f(a.x - b.x, a.y - b.y);
    }

    public static float magnitude(Vector2f v) {
        return (float) sqrt(v.x * v.x + v.y * v.y);
    }

    public static Vector2f unit(Vector2f v) {
        float m = magnitude(v);
        return new Vector2f(v.x / m, v.y / m);
    }

    public static Vector2f scale(Vector2f v, float f) {
        return new Vector2f(v.x * f, v.y * f);
    }

    public static Vector2f translate(Vector2f p, Vector2f v, float dt) {

        float dx = p.x + v.x * dt;
        float dy = p.y + v.y * dt;

        return new Vector2f(dx, dy);
    }

    public static Vector2f rotate(Vector2f v, float r) {
        float x = v.x * MathUtils.cos(r) - v.x * MathUtils.sin(r);
        float y = v.y * MathUtils.sin(r) + v.y * MathUtils.cos(r);
        return new Vector2f(x, y);
    }

    public static Vector2f translateVelocity(Vector2f a, Vector2f b, Vector2f v) {
        float m = magnitude(v);
        Vector2f d = difference(a, b);
        Vector2f u = unit(d);
        Vector2f s = scale(u, m);
        return rotate(s, MathUtils.PI);
    }
}
