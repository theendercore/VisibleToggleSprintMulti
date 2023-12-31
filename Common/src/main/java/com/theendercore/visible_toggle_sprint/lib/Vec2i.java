package com.theendercore.visible_toggle_sprint.lib;

public class Vec2i {
    public int x;
    public int y;

    public Vec2i(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vec2i(int size) {
        this.x = size;
        this.y = size;
    }

    public String toString() {
        return "Vec2i{" + x + "," + y + "}";
    }
}