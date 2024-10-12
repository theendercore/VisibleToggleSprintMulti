package com.theendercore.visible_toggle_sprint;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;

import static com.theendercore.visible_toggle_sprint.CommonClass.configButton;

public class VisibleToggleSprint implements ModInitializer {
    
    @Override
    public void onInitialize() {
        CommonClass.init();
        ClientTickEvents.END_CLIENT_TICK.register(CommonClass::handleKeyBinds);
        KeyBindingHelper.registerKeyBinding(configButton);
    }
}
