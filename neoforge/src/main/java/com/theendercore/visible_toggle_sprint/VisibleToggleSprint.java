package com.theendercore.visible_toggle_sprint;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(value = VTSConst.MODID, dist = Dist.CLIENT)
public class VisibleToggleSprint {
    public static IEventBus modBus;

    public VisibleToggleSprint(IEventBus modBus) {
        VisibleToggleSprint.modBus = modBus;
        VTSCommon.init();
    }
}