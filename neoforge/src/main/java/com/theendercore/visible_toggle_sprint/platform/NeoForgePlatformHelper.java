package com.theendercore.visible_toggle_sprint.platform;

import com.theendercore.visible_toggle_sprint.VisibleToggleSprint;
import com.theendercore.visible_toggle_sprint.platform.services.IPlatformHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.event.RenderGuiEvent;
import net.neoforged.neoforge.common.NeoForge;

import java.util.function.Consumer;

public class NeoForgePlatformHelper implements IPlatformHelper {
    @Override
    public boolean isDevelopmentEnvironment() {
        return !FMLLoader.getCurrent().isProduction();
    }

    @Override
    public void renderHud(Consumer<GuiGraphics> renderer) {
        NeoForge.EVENT_BUS.addListener((RenderGuiEvent.Post e) -> renderer.accept(e.getGuiGraphics()));
    }

    @Override
    public void registerKeyBinding(KeyMapping keyBinding, Consumer<Minecraft> action) {
        VisibleToggleSprint.modBus.addListener((RegisterKeyMappingsEvent event) -> event.register(keyBinding));
        NeoForge.EVENT_BUS.addListener((ClientTickEvent.Post event) -> action.accept(Minecraft.getInstance()));
    }
}