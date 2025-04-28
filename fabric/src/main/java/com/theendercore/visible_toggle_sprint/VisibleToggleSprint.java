package com.theendercore.visible_toggle_sprint;

import net.fabricmc.api.ClientModInitializer;

public class VisibleToggleSprint implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        VTSCommon.init();
    }
}
