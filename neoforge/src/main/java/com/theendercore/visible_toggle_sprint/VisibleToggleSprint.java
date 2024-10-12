package com.theendercore.visible_toggle_sprint;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(Constants.MODID)
public class VisibleToggleSprint {
    public static IEventBus modBus;

    public VisibleToggleSprint(IEventBus modBus) {
        VisibleToggleSprint.modBus = modBus;
        CommonClass.init();
    }
}