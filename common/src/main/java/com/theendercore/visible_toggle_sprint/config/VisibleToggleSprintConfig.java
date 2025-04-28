package com.theendercore.visible_toggle_sprint.config;

import me.fzzyhmstrs.fzzy_config.config.Config;
import me.fzzyhmstrs.fzzy_config.config.ConfigGroup;
import me.fzzyhmstrs.fzzy_config.config.ConfigSection;
import me.fzzyhmstrs.fzzy_config.validation.misc.ValidatedColor;
import me.fzzyhmstrs.fzzy_config.validation.misc.ValidatedString;
import me.fzzyhmstrs.fzzy_config.validation.number.ValidatedFloat;
import me.fzzyhmstrs.fzzy_config.validation.number.ValidatedNumber;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

import static com.theendercore.visible_toggle_sprint.VTSConst.MODID;
import static com.theendercore.visible_toggle_sprint.VTSConst.id;


public class VisibleToggleSprintConfig extends Config {
    public VisibleToggleSprintConfig() {
        super(id(MODID));
    }

    public PlayerState sprint = new PlayerState();
    public PlayerState sneak = new PlayerState(true, 7, 7, ICONS.get(1), 147, 18, 10, 30, Color.WHITE);

    public static class PlayerState extends ConfigSection {
        public PlayerState() {
            this(true, -7, -7, ICONS.getFirst(), 125, 18, 10, 10, Color.WHITE);
        }

        public PlayerState(boolean cross, int crossX, int crossY, String icon, int barX, int barY, int txtX, int txtY, Color color) {
            super();
            crosshairEnable = cross;
            crosshairX = crossX;
            crosshairY = crossY;
            crosshairIcon = ValidatedString.fromList(icon, ICONS);
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
        public ValidatedString crosshairIcon;
        public ValidatedFloat rotation = ValidatedNumber.Companion.withIncrement(new ValidatedFloat(0f, 360f, -360f, ValidatedNumber.WidgetType.TEXTBOX_WITH_BUTTONS), 90f);

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



    public static List<String> ICONS = Arrays.asList(
            "sprint",
            "sneak",
            "sprint_alt",
            "sneak_alt",
            "c",
            "corner",
            "3",
            "symbol",
            "stair",
            "cut_stair",
            "thin_stair",
            "small_stair",
            "bone",
            "wand",
            "hunger",
            "feather",
            "big_circle",
            "block_sign",
            "big_hollow_circle",
            "oval",
            "circle",
            "plus",
            "brick",
            "hollow_brick",
            "big_square",
            "big_hollow_square",
            "rectangle",
            "hollow_rectangle",
            "square",
            "hollow_square",
            "small_square",
            "small_rectangle",
            "dot",
            "line",
            "think_line",
            "handle",
            "round_handle",
            "nub",
            "saw_blade",
            "zig_zag",
            "t_tetromino",
            "c_tetromino",
            "j_tetromino",
            "z_tetromino",
            "notch",
            "zig",
            "checkerboard",
            "checkerboard_alt",
            "big_diagonal",
            "diagonal",
            "small_diagonal",
            "big_gem",
            "gem",
            "small_gem",
            "x",
            "big_x",
            "cross",
            "stick",
            "t",
            "small_t",
            "hoe",
            "pickaxe",
            "axe",
            "diagonal_handle"
    );
}
