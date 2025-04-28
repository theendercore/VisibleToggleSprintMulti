package com.theendercore.visible_toggle_sprint.config;

import me.fzzyhmstrs.fzzy_config.config.Config;
import me.fzzyhmstrs.fzzy_config.config.ConfigGroup;
import me.fzzyhmstrs.fzzy_config.config.ConfigSection;
import me.fzzyhmstrs.fzzy_config.validation.misc.ValidatedColor;
import me.fzzyhmstrs.fzzy_config.validation.misc.ValidatedEnum;

import java.awt.*;

import static com.theendercore.visible_toggle_sprint.VTSConst.MODID;
import static com.theendercore.visible_toggle_sprint.VTSConst.id;


public class VisibleToggleSprintConfig extends Config {
    public VisibleToggleSprintConfig() {
        super(id(MODID));
    }

    public PlayerState sprint = new PlayerState();
    public PlayerState sneak = new PlayerState(true, 1, 1, CrosshairIcons.STYLISED, 147, 18, 10, 30, Color.WHITE);

    public static class PlayerState extends ConfigSection {
        public PlayerState() {
            this(true, -6, -6, CrosshairIcons.STYLISED, 125, 18, 10, 10, Color.WHITE);
        }

        public PlayerState(boolean cross, int crossX, int crossY, CrosshairIcons icon, int barX, int barY, int txtX, int txtY, Color color) {
            super();
            crosshairEnable = cross;
            crosshairX = crossX;
            crosshairY = crossY;
            crosshairIcon = new ValidatedEnum<>(icon, ValidatedEnum.WidgetType.CYCLING);
            hotbarX = barX;
            hotbarY = barY;
            textX = txtX;
            textY = txtY;
            textColor = new ValidatedColor(color, false);
        }


        @SuppressWarnings("unused")
        public ConfigGroup crosshair = new ConfigGroup("crosshair");
        public boolean crosshairEnable;
        public int crosshairX;
        public int crosshairY;
        @ConfigGroup.Pop
        public ValidatedEnum<CrosshairIcons> crosshairIcon;

        @SuppressWarnings("unused")
        public ConfigGroup hotbar = new ConfigGroup("hotbar", true);
        public boolean hotbarEnable = false;
        public int hotbarX;
        @ConfigGroup.Pop
        public int hotbarY;

        @SuppressWarnings("unused")
        public ConfigGroup text = new ConfigGroup("text", true);
        public boolean textEnable = false;
        public int textX;
        public int textY;
        @ConfigGroup.Pop
        public ValidatedColor textColor;

        public IndicatorType indicator = IndicatorType.STATE_ONLY;
    }

    public enum IndicatorType {KEY_ONLY, STATE_ONLY, COMBINED}

    public enum CrosshairIcons {
        STYLISED(0), MINIMAL_ONE(4), MINIMAL_TWO(8), MINIMAL_THREE(12);
        public final int x;

        CrosshairIcons(int x) {
            this.x = x;
        }
    }
}
