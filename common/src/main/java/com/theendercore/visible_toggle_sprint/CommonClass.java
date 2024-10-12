package com.theendercore.visible_toggle_sprint;

import com.mojang.blaze3d.platform.InputConstants;
import com.theendercore.visible_toggle_sprint.common.HudRender;
import com.theendercore.visible_toggle_sprint.config.VTSConfig;
import com.theendercore.visible_toggle_sprint.platform.Services;
import me.fzzyhmstrs.fzzy_config.api.ConfigApiJava;
import me.fzzyhmstrs.fzzy_config.api.RegisterType;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;

import static com.theendercore.visible_toggle_sprint.Constants.LOG;

public class CommonClass {
    public static final KeyMapping configButton = new KeyMapping("key.visible_toggle_sprint.desc", InputConstants.UNKNOWN.getValue(), "key.visible_toggle_sprint.category");
    public static final VTSConfig CONFIG = ConfigApiJava.registerAndLoadConfig(VTSConfig::new, RegisterType.CLIENT);

    public static void init() {
//        VisibleToggleSprintConfigOld.INSTANCE.load();
        if (Services.PLATFORM.isModLoaded("visible_toggle_sprint")) LOG.info("I am my planting roots.");
        Services.PLATFORM.renderHud(HudRender::renderHud);
    }

    public static void handleKeyBinds(Minecraft client) {
        if (configButton.isDown() && client.screen == null) return;
//            client.setScreen(VisibleToggleSprintConfigOld.makeScreen(null));
    }

/*    public static VisibleToggleSprintConfigOld getConfig() {
        return VisibleToggleSprintConfigOld.INSTANCE.getConfig();
    }*/
}