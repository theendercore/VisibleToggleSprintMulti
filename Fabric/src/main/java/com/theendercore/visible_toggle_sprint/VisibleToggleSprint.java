package com.theendercore.visible_toggle_sprint;

import com.theendercore.visible_toggle_sprint.common.ConfigKeyMapping;
import com.theendercore.visible_toggle_sprint.common.HudRender;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

import static com.theendercore.visible_toggle_sprint.CommonClass.configButton;

public class VisibleToggleSprint implements ModInitializer {
    
    @Override
    public void onInitialize() {
        Constants.LOG.info("Hello Fabric world!");
        CommonClass.init();
        HudRenderCallback.EVENT.register((guiGraphics, delta) -> HudRender.renderHud(guiGraphics));
        ClientTickEvents.END_CLIENT_TICK.register(ConfigKeyMapping::keyMappingAction);
        KeyBindingHelper.registerKeyBinding(configButton);
    }
}
