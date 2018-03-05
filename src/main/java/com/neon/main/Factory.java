package com.neon.main;

import com.neon.main.entities.Drawable;

public interface Factory {
    Drawable create(String key);
}
