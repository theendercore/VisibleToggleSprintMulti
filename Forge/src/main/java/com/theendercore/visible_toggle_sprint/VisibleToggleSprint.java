package com.theendercore.visible_toggle_sprint;

import com.theendercore.visible_toggle_sprint.common.HudRender;
import net.minecraftforge.client.event.RenderGuiEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

@Mod(Constants.MODID)
public class VisibleToggleSprint {
    public VisibleToggleSprint() {
        Constants.LOG.info("Hello Forge world!");
        CommonClass.init();
        MinecraftForge.EVENT_BUS.addListener((RenderGuiEvent.Post event) -> HudRender.renderHud(event.getGuiGraphics()));
    }
}