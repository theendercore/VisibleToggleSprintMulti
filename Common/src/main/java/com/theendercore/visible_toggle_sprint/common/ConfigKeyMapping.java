package com.theendercore.visible_toggle_sprint.common;

import com.theendercore.visible_toggle_sprint.config.VisibleToggleSprintConfig;
import net.minecraft.client.Minecraft;

import static com.theendercore.visible_toggle_sprint.CommonClass.configButton;

public class ConfigKeyMapping {
    public static void keyMappingAction(Minecraft client) {
        if (configButton.isDown() && client.screen == null)
            client.setScreen(VisibleToggleSprintConfig.makeScreen(null));
    }
}
