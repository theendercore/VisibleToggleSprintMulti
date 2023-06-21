package com.theendercore.visible_toggle_sprint.lib;

public abstract class IState {
    public boolean enable;
    public Vec2i location;

    IState(boolean e, Vec2i l) {
        this.enable = e;
        this.location = l;
    }
}
