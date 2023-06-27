package com.theendercore.visible_toggle_sprint;

import com.mojang.blaze3d.platform.InputConstants;
import com.theendercore.visible_toggle_sprint.config.VisibleToggleSprintConfig;
import com.theendercore.visible_toggle_sprint.platform.Services;
import net.minecraft.client.KeyMapping;

import static com.theendercore.visible_toggle_sprint.Constants.LOG;

public class CommonClass {
 public static final KeyMapping configButton = new KeyMapping("key.visible_toggle_sprint.desc",  InputConstants.UNKNOWN.getValue(), "key.visible_toggle_sprint.category");

    public static void init() {
        VisibleToggleSprintConfig.INSTANCE.load();
        if (Services.PLATFORM.isModLoaded("visible_toggle_sprint")) {
            LOG.info("I am my planting roots.");
        }

    }
    public static VisibleToggleSprintConfig getConfig() {
        return VisibleToggleSprintConfig.INSTANCE.getConfig();
    }
}