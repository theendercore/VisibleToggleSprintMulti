package com.theendercore.visible_toggle_sprint.config;

import me.fzzyhmstrs.fzzy_config.config.Config;
import me.fzzyhmstrs.fzzy_config.config.ConfigSection;
import me.fzzyhmstrs.fzzy_config.util.Walkable;
import me.fzzyhmstrs.fzzy_config.validation.misc.ValidatedColor;

import java.awt.*;

import static com.theendercore.visible_toggle_sprint.Constants.MODID;
import static com.theendercore.visible_toggle_sprint.Constants.id;
import static com.theendercore.visible_toggle_sprint.config.VisibleToggleSprintConfig.PlayerState.CrosshairData.CrosshairIcons.STYLISED;
import static com.theendercore.visible_toggle_sprint.config.VisibleToggleSprintConfig.PlayerState.IndicatorType;


public class VisibleToggleSprintConfig extends Config {
    public PlayerState sprint = new PlayerState(
            new PlayerState.CrosshairData(true, -6, -6, STYLISED),
            new PlayerState.DisplayData(false, 125, 18),
            new PlayerState.TextData(false, 10, 10, new ValidatedColor(Color.WHITE, false)),
            IndicatorType.STATE_ONLY
    );

    public PlayerState sneak = new PlayerState(
            new PlayerState.CrosshairData(true, 1, 1, STYLISED),
            new PlayerState.DisplayData(false, 147, 18),
            new PlayerState.TextData(false, 10, 30, new ValidatedColor(Color.WHITE, false)),
            IndicatorType.STATE_ONLY
    );

    public VisibleToggleSprintConfig() {
        super(id(MODID));
    }

    public static class PlayerState extends ConfigSection {
        public CrosshairData crosshair;
        public DisplayData hotbar;
        public TextData text;
        public IndicatorType indicator;

        public PlayerState(CrosshairData crosshair, DisplayData hotbar, TextData text, IndicatorType indicator) {
            this.crosshair = crosshair;
            this.hotbar = hotbar;
            this.text = text;
            this.indicator = indicator;
        }

        public static class CrosshairData extends DisplayData {
            public CrosshairIcons icon;

            public CrosshairData(boolean e, int x, int y, CrosshairIcons i) {
                super(e, x, y);
                this.icon = i;
            }

            public enum CrosshairIcons {
                STYLISED(0), MINIMAL_ONE(4), MINIMAL_TWO(8), MINIMAL_THREE(12);
                public final int x;

                CrosshairIcons(int x) {
                    this.x = x;
                }
            }
        }

        public static class TextData extends DisplayData {
            public ValidatedColor color;

            public TextData(boolean e, int x, int y, ValidatedColor c) {
                super(e, x, y);
                this.color = c;
            }
        }

        public static class DisplayData implements Walkable {
            public boolean enable;
            public int x;
            public int y;

            public DisplayData(boolean e, int x, int y) {
                this.enable = e;
                this.x = x;
                this.y = y;
            }
        }

        public enum IndicatorType {KEY_PRESSED_ONLY, STATE_ONLY, BOTH}
    }
}
