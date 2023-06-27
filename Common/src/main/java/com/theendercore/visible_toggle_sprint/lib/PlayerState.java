package com.theendercore.visible_toggle_sprint.lib;

public class PlayerState {
    public Crosshair crosshair;
    public Hotbar hotbar;
    public TextState text;

    public PlayerState(boolean enableCross, Vec2i locationCross, CrosshairIcons icon, boolean enableHotbar, Vec2i locationHotbar, boolean enableText, Vec2i locationText, int colorText) {
        this.crosshair = new Crosshair(enableCross, locationCross, icon);
        this.hotbar = new Hotbar(enableHotbar, locationHotbar);
        this.text = new TextState(enableText, locationText, colorText);
    }

    @Override
    public String toString() {
        return "PlayerState{" + crosshair.toString() + "," + hotbar.toString() + "," + text.toString() + "}";
    }

    public static class Crosshair extends IState {
        public CrosshairIcons icon;

        public Crosshair(boolean e, Vec2i l, CrosshairIcons i) {
            super(e, l);
            this.icon = i;
        }
        public String toString() {
            return "Crosshair{" + enable + "," + location.toString() + "," + icon.toString() + "}";
        }
    }

    public static class Hotbar extends IState {
        public Hotbar(boolean e, Vec2i l) {
            super(e, l);
        }
        public String toString() {
            return "Hotbar{" + enable + "," + location.toString() + "}";
        }
    }

    public static class TextState extends IState {
        public int color;

        TextState(boolean e, Vec2i l, int c) {
            super(e, l);
            this.color = c;
        }
        public String toString() {
            return "TextState{" + enable + "," + location.toString() + "," + color + "}";
        }
    }
}

