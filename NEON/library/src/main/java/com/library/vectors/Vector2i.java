package com.library.vectors;

import java.util.Objects;

public class Vector2i {

    public final int x;
    public final int y;

    public Vector2i(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * NO USAGE
     * @param other
     * @return 
     */
    
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        Vector2i vector = (Vector2i) other;
        return this.x == vector.x && this.y == vector.y;
    }

    /**
     * NO USAGE
     * @return 
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
