package com.theendercore.visible_toggle_sprint;

import com.mojang.blaze3d.platform.InputConstants;
import com.theendercore.visible_toggle_sprint.common.HudRender;
import com.theendercore.visible_toggle_sprint.config.VisibleToggleSprintConfig;
import com.theendercore.visible_toggle_sprint.platform.Services;
import me.fzzyhmstrs.fzzy_config.api.ConfigApiJava;
import me.fzzyhmstrs.fzzy_config.api.RegisterType;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;

import static com.theendercore.visible_toggle_sprint.VTSConst.log;
import static com.theendercore.visible_toggle_sprint.VTSConst.MODID;

public class VTSCommon {
    public static final KeyMapping configButton = new KeyMapping("key.visible_toggle_sprint.desc", InputConstants.UNKNOWN.getValue(), "key.visible_toggle_sprint.category");
    public static final VisibleToggleSprintConfig CONFIG = ConfigApiJava.registerAndLoadConfig(VisibleToggleSprintConfig::new, RegisterType.CLIENT);

    public static void init() {
        log.info("Making every sprint visible.");
        Services.PLATFORM.renderHud(HudRender::renderHud);
        Services.PLATFORM.registerKeyBinding(configButton, VTSCommon::handleKeyBinds);
    }

    public static void handleKeyBinds(Minecraft client) {
        if (configButton.isDown() && client.screen == null) ConfigApiJava.INSTANCE.openScreen(MODID + "." + MODID);
    }
}