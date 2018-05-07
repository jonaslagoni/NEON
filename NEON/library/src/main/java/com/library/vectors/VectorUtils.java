package com.library.vectors;

import static java.lang.Math.*;

@SuppressWarnings("WeakerAccess")
public final class VectorUtils {

    private VectorUtils() {
    }
    
    /**
     * NO USAGE
     * Calculates the angle for vector a on vector b in radians between 0 and PI
     * @param a first vector
     * @param b second vector
     * @return angle in decimal
     */
    
    public static float angle(Vector2f a, Vector2f b) {
        return (float) atan2(a.y - b.y, a.x - b.x);
    }

    /**
     * Calculates the angle for a vector in radians between 0 and PI
     * @param v vector
     * @return angle in decimal
     */
    public static float angle(Vector2f v) {
        return (float) atan2(v.y, v.x);
    }

    /**
     * Calculates distance between two vectors
     * @param a first vector
     * @param b second vetor
     * @return distance in decimal
     */
    public static float distanceSquare(Vector2f a, Vector2f b) {
        return (a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y);
    }
    
    /**
     * NO USAGE
     * Hvad er forskellen på denne og ovenstående????????????????????????????????
     * @param a
     * @param b
     * @return 
     */
    public static float distance(Vector2f a, Vector2f b) {
        return (float) sqrt((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y));
    }

    /**
     * Calculates the difference between two vectors x and y condtinates
     * @param a first vector
     * @param b second vector
     * @return a new vector object with x and y coordinates as the difference the two vectors
     */
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
        float x = (float) (v.x * cos(r) - v.x * sin(r));
        float y = (float) (v.y * sin(r) + v.y * cos(r));
        return new Vector2f(x, y);
    }

    public static Vector2f translateVelocity(Vector2f a, Vector2f b, Vector2f v) {
        float m = magnitude(v);
        Vector2f d = difference(a, b);
        Vector2f u = unit(d);
        Vector2f s = scale(u, m);
        return rotate(s, (float) PI);
    }
}
